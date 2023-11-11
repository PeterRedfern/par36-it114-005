package Project;

import java.util.ArrayList; 
import java.util.Iterator;
import java.util.List;
import java.util.Random; // par36 - 11/3/23 Implemented in coinflip

public class Room implements AutoCloseable {
	protected static Server server; // used to refer to accessible server functions
	private String name;
	private List<ServerThread> clients = new ArrayList<ServerThread>();
	private boolean isRunning = false;
	// Commands
	private final static String COMMAND_TRIGGER = "/";
	private final static String CREATE_ROOM = "createroom";
	private final static String JOIN_ROOM = "joinroom";
	private final static String DISCONNECT = "disconnect";
	private final static String LOGOUT = "logout";
	private final static String LOGOFF = "logoff";

	public Room(String name) {
		this.name = name;
		isRunning = true;
	}

	private void info(String message) {
		System.out.println(String.format("Room[%s]: %s", name, message));
	}

	public String getName() {
		return name;
	}

	protected synchronized void addClient(ServerThread client) {
		if (!isRunning) {
			return;
		}
		client.setCurrentRoom(this);
		if (clients.indexOf(client) > -1) {
			info("Attempting to add a client that already exists");
		} else {
			clients.add(client);
			new Thread() {
				@Override
				public void run() {
					// slight delay to let potentially new client to finish
					// binding input/output streams
					// comment out the Thread.sleep to see what happens
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// sendMessage(client, "joined the room " + getName());
					sendConnectionStatus(client, true);
				}
			}.start();

		}
	}

	protected synchronized void removeClient(ServerThread client) {
		if (!isRunning) {
			return;
		}
		clients.remove(client);
		// we don't need to broadcast it to the server
		// only to our own Room
		if (clients.size() > 0) {
			// sendMessage(client, "left the room");
			sendConnectionStatus(client, false);
		}
		checkClients();
	}

	/***
	 * Checks the number of clients.
	 * If zero, begins the cleanup process to dispose of the room
	 */
	private void checkClients() {
		// Cleanup if room is empty and not lobby
		if (!name.equalsIgnoreCase("lobby") && clients.size() == 0) {
			close();
		}
	}

	/***
	 * Helper function to process messages to trigger different functionality.
	 * 
	 * @param message The original message being sent
	 * @param client  The sender of the message (since they'll be the ones
	 *                triggering the actions)
	 */
	private boolean processCommands(String message, ServerThread client) {
		boolean wasCommand = false;
		try {
			if (message.startsWith(COMMAND_TRIGGER)) {
				String[] comm = message.split(COMMAND_TRIGGER);
				String part1 = comm[1];
				String[] comm2 = part1.split(" ");
				String command = comm2[0];
				System.out.println(TextFX.colorize(command, TextFX.Color.RED)); // par36 11/6/23 - Prints out commands in red
				String roomName;
				wasCommand = true;
				switch (command) {
					case CREATE_ROOM:
						roomName = comm2[1];
						Room.createRoom(roomName, client);
						break;
					case JOIN_ROOM:
						roomName = comm2[1];
						Room.joinRoom(roomName, client);
						break;
					case DISCONNECT:
					case LOGOUT:
					case LOGOFF:
						Room.disconnectClient(client, this);
						break;
					case "flip": // par36 11/6/23 - Flip code
						Random random = new Random(); // Creates a random object to generate heads or tails
						int randomValue = random.nextInt(2); // random number which is generated and used in the if else loop
						String result;
						if (randomValue == 0) { // If/else loop which checks if the int randomValue is equal to
							result = "heads"; // 0 and sets the String result to heads or tails
						} else {
							result = "tails";
						}
						String coinFlipMessage = "Flipped a coin and got " + result; // toString for the result to be printed
						sendMessage(client, coinFlipMessage);
						break;
					case "roll": // par36 11/8/23 - Roll code
						int total = 0;
						String roll = comm2[1]; // checks for characters entered after "/roll"
						if (roll.contains("d")) { // checks if the roll command has a "d" for extra dice
							if (roll.split("d").length == 2) {
								String[] parts = roll.split("d");
								int diceNum = Integer.parseInt(parts[0]); // gets the number of dice
								int diceSides = Integer.parseInt(parts[1]); // gets the number of sides on the dice
								// math for total variable
								int max = diceNum * diceSides;
								int min = diceNum;
								total = (int) ((Math.random() * (max - min + 1)) + min);
							}
							String rollMessage = "Rolled " + roll + " and got " + total;
							sendMessage(client, rollMessage);
						} else { // if there is no "d" for multiple dice
							int intRoll = Integer.parseInt(roll);
							total = (int) (Math.random() * intRoll);
							String rollMessage = "Rolled " + roll + " and got " + total;
							sendMessage(client, rollMessage);
						}
						break;

					default:
						wasCommand = false;
						break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wasCommand;
	}

	// Command helper methods
	protected static void createRoom(String roomName, ServerThread client) {
		if (server.createNewRoom(roomName)) {
			// server.joinRoom(roomName, client);
			Room.joinRoom(roomName, client);
		} else {
			client.sendMessage("Server", String.format("Room %s already exists", roomName));
		}
	}

	protected static void joinRoom(String roomName, ServerThread client) {
		if (!server.joinRoom(roomName, client)) {
			client.sendMessage("Server", String.format("Room %s doesn't exist", roomName));
		}
	}

	protected static void disconnectClient(ServerThread client, Room room) {
		client.setCurrentRoom(null);
		client.disconnect();
		room.removeClient(client);
	}
	// end command helper methods

	/***
	 * Takes a sender and a message and broadcasts the message to all clients in
	 * this room. Client is mostly passed for command purposes but we can also use
	 * it to extract other client info.
	 * 
	 * @param sender  The client sending the message
	 * @param message The message to broadcast inside the room
	 */
	protected synchronized void sendMessage(ServerThread sender, String message) {
		if (!isRunning) {
			return;
		}
		info("Sending message to " + clients.size() + " clients");
		if (sender != null && processCommands(message, sender)) {
			// it was a command, don't broadcast
			return;
		}
		message = messageFormat(message); // par36 11/3/23 - added for message with different characteristics (bold, color, etc.) to be processed
		String from = (sender == null ? "Room" : sender.getClientName());
		Iterator<ServerThread> iter = clients.iterator();
		while (iter.hasNext()) {
			ServerThread client = iter.next();
			boolean messageSent = client.sendMessage(from, message);
			if (!messageSent) {
				handleDisconnect(iter, client);
			}
		}
	}

	protected synchronized void sendConnectionStatus(ServerThread sender, boolean isConnected) {
		Iterator<ServerThread> iter = clients.iterator();
		while (iter.hasNext()) {
			ServerThread client = iter.next();
			boolean messageSent = client.sendConnectionStatus(sender.getClientName(), isConnected);
			if (!messageSent) {
				handleDisconnect(iter, client);
			}
		}
	}

	private void handleDisconnect(Iterator<ServerThread> iter, ServerThread client) {
		iter.remove();
		info("Removed client " + client.getClientName());
		checkClients();
		sendMessage(null, client.getClientName() + " disconnected");
	}

	public void close() {
		server.removeRoom(this);
		server = null;
		isRunning = false;
		clients = null;
	}

	public String messageFormat(String message) { // par36 11/3/23 - made so that the message will change to the new message
		String newMessage = message; 

		// par36 11/9/23 - Different font options and colors

		// bold
		newMessage = newMessage.replaceAll("\\$\\*", "<b>");
		newMessage = newMessage.replaceAll("\\*\\$", "</b>");
		
		// italic
		newMessage = newMessage.replaceAll("\\$/", "<i>");
		newMessage = newMessage.replaceAll("/\\$", "</i>");
		
		// underline
		newMessage = newMessage.replaceAll("\\$_", "<u>");
		newMessage = newMessage.replaceAll("_\\$", "</u>");

		// red
		newMessage = newMessage.replaceAll("\\$r", "<font color= red>");
		newMessage = newMessage.replaceAll("r\\$", "</font>");

		// green
		newMessage = newMessage.replaceAll("\\$g", "<font color= green>");
		newMessage = newMessage.replaceAll("g\\$", "</font>");

		// blue
		newMessage = newMessage.replaceAll("\\$b", "<font color= blue>");
		newMessage = newMessage.replaceAll("b\\$", "</font>");
		
		return (newMessage);
	}

}
