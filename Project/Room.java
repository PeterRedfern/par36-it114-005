package Project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.Random; // par36 - 11/3/23 Implemented in coinflip

public class Room implements AutoCloseable {
    // server is a singleton now so we don't need this
    // protected static Server server;// used to refer to accessible server
    // functions
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
    private static Logger logger = Logger.getLogger(Room.class.getName());

    public Room(String name) {
        this.name = name;
        isRunning = true;
    }

    public String getName() {
        return name;
    }

    public boolean isRunning() {
        return isRunning;
    }

    protected synchronized void addClient(ServerThread client) {
        if (!isRunning) {
            return;
        }
        client.setCurrentRoom(this);
        if (clients.indexOf(client) > -1) {
            logger.warning("Attempting to add client that already exists in room");
        } else {
            clients.add(client);
            client.sendResetUserList();
            syncCurrentUsers(client);
            sendConnectionStatus(client, true);
        }
    }

    protected synchronized void removeClient(ServerThread client) {
        if (!isRunning) {
            return;
        }
        // attempt to remove client from room
        try {
            clients.remove(client);
        } catch (Exception e) {
            logger.severe(String.format("Error removing client from room %s", e.getMessage()));
            e.printStackTrace();
        }
        // if there are still clients tell them this person left
        if (clients.size() > 0) {
            sendConnectionStatus(client, false);
        }
        checkClients();
    }

    private void syncCurrentUsers(ServerThread client) {
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread existingClient = iter.next();
            if (existingClient.getClientId() == client.getClientId()) {
                continue;// don't sync ourselves
            }
            boolean messageSent = client.sendExistingClient(existingClient.getClientId(),
                    existingClient.getClientName());
            if (!messageSent) {
                handleDisconnect(iter, existingClient);
                break;// since it's only 1 client receiving all the data, break if any 1 send fails
            }
        }
    }

    /***
     * Checks the number of clients.
     * If zero, begins the cleanup process to dispose of the room
     */
    private void checkClients() {
        // Cleanup if room is empty and not lobby
        if (!name.equalsIgnoreCase(Constants.LOBBY) && (clients == null || clients.size() == 0)) {
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
    @Deprecated // not used in my project as of this lesson, keeping it here in case things
                // change
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
    protected static void getRooms(String query, ServerThread client) {
        String[] rooms = Server.INSTANCE.getRooms(query).toArray(new String[0]);
        client.sendRoomsList(rooms,
                (rooms != null && rooms.length == 0) ? "No rooms found containing your query string" : null);
    }

    protected static void createRoom(String roomName, ServerThread client) {
        if (Server.INSTANCE.createNewRoom(roomName)) {
            Room.joinRoom(roomName, client);
        } else {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, String.format("Room %s already exists", roomName));
        }
    }

    /**
     * Will cause the client to leave the current room and be moved to the new room
     * if applicable
     * 
     * @param roomName
     * @param client
     */
    protected static void joinRoom(String roomName, ServerThread client) {
        if (!Server.INSTANCE.joinRoom(roomName, client)) {
            client.sendMessage(Constants.DEFAULT_CLIENT_ID, String.format("Room %s doesn't exist", roomName));
        }
    }

    protected static void disconnectClient(ServerThread client, Room room) {
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
        logger.info(String.format("Sending message to %s clients", clients.size()));
        if (sender != null && processCommands(message, sender)) {
            // it was a command, don't broadcast
            return;
        }
        message = messageFormat(message); // par36 11/3/23 - added for message with different characteristics (bold, color, etc.) to be processed
        long from = sender == null ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
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
            ServerThread receivingClient = iter.next();
            boolean messageSent = receivingClient.sendConnectionStatus(
                    sender.getClientId(),
                    sender.getClientName(),
                    isConnected);
            if (!messageSent) {
                handleDisconnect(iter, receivingClient);
            }
        }
    }

    private void handleDisconnect(Iterator<ServerThread> iter, ServerThread client) {
        iter.remove();
        logger.info(String.format("Removed client %s", client.getClientName()));
        sendMessage(null, client.getClientName() + " disconnected");
        checkClients();
    }

    public void close() {
        Server.INSTANCE.removeRoom(this);
        isRunning = false;
        clients.clear();
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