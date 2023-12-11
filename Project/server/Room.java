package Project.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import Project.common.Constants;
import Project.common.TextFX;

import java.util.Random; // par36 - 11/3/23 Implemented in coinflip

public class Room implements AutoCloseable {
    // server is a singleton now so we don't need this
    // protected static Server server;
    // used to refer to accessible server functions
    private String name;
    protected List<ServerThread> clients = new ArrayList<ServerThread>();
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
        logger.info("Room addClient called");
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

    /*private ServerThread getclientById(long id) { // par36 11/29/23 - Created to get a user's serverthread information by ID (not used)
        return clients.stream().filter(c->c.getClientId() == id).toList().get(0); 
    }
    */

    protected ServerThread getClientByName(String name) { // par36 11/27/23 - Created to get a user's serverthread information by name
        return clients.stream().filter(c -> c.getClientName().equalsIgnoreCase(name.trim())).toList().get(0); 
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
                System.out.println(TextFX.colorize(command, TextFX.Color.RED)); // par36 11/6/23 - Prints out commands in red (in the terminal)
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
                        String coinFlipMessage = "*$" + "Flipped a coin and got " + result + "$*"; // toString for the result to be printed
                        sendMessage(client, coinFlipMessage); // Sends the message
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
                                total = (int) ((Math.random() * (max - min + 1)) + min); // Equation for multiple dice
                            }
                            String rollMessage = "*$" + "Rolled " + roll + " and got " + total + "$*"; // Message to be sent to all users
                            sendMessage(client, rollMessage); // Sends the message
                        } else { // if there is no "d" for multiple dice
                            int intRoll = Integer.parseInt(roll); // Passes in the user input for one integer/die
                            total = (int) (Math.random() * intRoll); // Recalculates total for just one die
                            String rollMessage = "*$" + "Rolled " + roll + " and got " + total + "$*"; // Message to be sent to all users
                            sendMessage(client, rollMessage); // Sends the message
                        }
                        break;
                    case "mute": // par36 11/21/23 - Mute command
                        String muteTarget = ""; // holds the name of the muted person
                        muteTarget = comm2[1]; // defines the name based on what comes after the command
                        client.mute(muteTarget); // adds the target's name to the mutelist
                        // par36 11/27/23 - Sends the person a message they were muted
                        ServerThread target = getClientByName(muteTarget); // par36 12/4/23 - Gets the name of the target
                        target.sendMessage(Constants.DEFAULT_CLIENT_ID, client.getClientName() + " muted you"); // tells the target they were muted
                        client.sendMessage(Constants.DEFAULT_CLIENT_ID, "You muted " + muteTarget); // tells the user who they muted
                        break;
                    case "unmute": // par36 11/21/23 - Unmute command
                        String unmuteTarget = ""; // holds the name of the unmuted person
                        unmuteTarget = comm2[1]; // defines the name based on what comes after the command
                        client.unmute(unmuteTarget); // removes the target's name from the mutelist
                        // par36 11/27/23 - Sends the person a message they were unmuted
                        ServerThread target2 = getClientByName(unmuteTarget); // par36 12/4/23 - Gets the name of the target
                        target2.sendMessage(Constants.DEFAULT_CLIENT_ID, client.getClientName() + " unmuted you"); // tells the target they were unmuted
                        client.sendMessage(Constants.DEFAULT_CLIENT_ID, "You unmuted " + unmuteTarget); // tells the user who they unmuted
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
        if (sendPrivateMessage(sender, message)) { // par36 11/20/23 - Stops the process if the message is a private message and goes to sendPrivateMessage
            return;
        }
        message = messageFormat(message); // par36 11/3/23 - added for message with different characteristics (bold, color, etc.) to be processed
        long from = sender == null ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
        Iterator<ServerThread> iter = clients.iterator();
        while (iter.hasNext()) {
            ServerThread client = iter.next();
            if (sender != null && client.isMuted(sender.getClientName())) { // par36 11/21/23 - mutecheck: checks if the client exists and if they are muted
                continue;                                                   // if the condition is met (they are muted), the private message isn't sent
            }
            boolean messageSent = client.sendMessage(from, message);
            if (!messageSent) {
                handleDisconnect(iter, client);
            }
        }
    }

    protected synchronized boolean sendPrivateMessage(ServerThread sender, String message) {
        if (!isRunning) {
            return false;
        }
        long from = sender == null ? Constants.DEFAULT_CLIENT_ID : sender.getClientId();
        Iterator<ServerThread> iter = clients.iterator(); // par36 11/20/23 - Iterates through the clients list
        String target = ""; // String to hold the target value
        if (message.startsWith("@")) { // checks if the message starts with @
            String[] comm = message.split("@"); // splits the result after @ into a string array
            target = comm[1].split(" ")[0]; // gets the result (target user) from the string array
            while (iter.hasNext()) {
                ServerThread client = iter.next();
                if (sender != null && client.isMuted(sender.getClientName())) { // par36 11/21/23 - mutecheck: checks if the client exists and if they are muted
                    continue;                                                   // if the condition is met (they are muted), the private message isn't sent
                }
                if (client.getClientName().equalsIgnoreCase(target)) { // if the client is equal to the target
                    boolean messageSent = client.sendMessage(from, message); // checking if the message is sent
                    if (!messageSent) { // if the message is not sent
                        handleDisconnect(iter, client); // disconnect the client
                    }
                    if(sender != null) { // par36 11/21/23 - checks if the sender is null as a safety case (should never happen)
                        sender.sendMessage(from, message); // sends the message
                    }
                    return true; // if the second if condition isn't triggered, will return true with the boolean
                }
            }
        }
        return false;
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

    protected void handleDisconnect(Iterator<ServerThread> iter, ServerThread client) {
        if (iter != null) {
            iter.remove();
            iter.remove();
        } else {
            Iterator<ServerThread> iter2 = clients.iterator();
            while (iter2.hasNext()) {
                ServerThread th = iter2.next();
                if (th.getClientId() == client.getClientId()) {
                    iter2.remove();
                    break;
                }
            }
        }
        logger.info(String.format("Removed client %s", client.getClientName()));
        sendMessage(null, client.getClientName() + " disconnected");
        checkClients();
    }

    protected void mutedMessage(ServerThread muter, String user) {
        ServerThread target = getClientByName(user);
        target.sendMessage(muter.getClientId(), " muted you"); 
    }

    public void close() {
        Server.INSTANCE.removeRoom(this);
        isRunning = false;
        clients.clear();
    }

    public String messageFormat(String message) { // par36 11/3/23 - made so that the original message will change to the new message with the new font/color
        String newMessage = message;

        // par36 11/9/23 - Different font options and colors

        // bold
        newMessage = newMessage.replaceAll("\\*\\$", "<b>"); //replaces each commandtrigger with the wrapper for the font type
        newMessage = newMessage.replaceAll("\\$\\*", "</b>"); //asymetric so start and end trigger are different

        // italic
        newMessage = newMessage.replaceAll("/\\$", "<i>"); //replaces each commandtrigger with the wrapper for the font type
        newMessage = newMessage.replaceAll("\\$/", "</i>"); //asymetric so start and end trigger are different

        // underline
        newMessage = newMessage.replaceAll("_\\$", "<u>"); //replaces each commandtrigger with the wrapper for the font type
        newMessage = newMessage.replaceAll("\\$_", "</u>"); //asymetric so start and end trigger are different

        // red
        newMessage = newMessage.replaceAll("r\\$", "<font color= red>"); //replaces each commandtrigger with the wrapper for the color
        newMessage = newMessage.replaceAll("\\$r", "</font>"); //asymetric so start and end trigger are different

        // green
        newMessage = newMessage.replaceAll("g\\$", "<font color= green>"); //replaces each commandtrigger with the wrapper for the color
        newMessage = newMessage.replaceAll("\\$g", "</font>"); //asymetric so start and end trigger are different

        // blue
        newMessage = newMessage.replaceAll("b\\$", "<font color= blue>"); //replaces each commandtrigger with the wrapper for the color
        newMessage = newMessage.replaceAll("\\$b", "</font>"); //asymetric so start and end trigger are different

        return (newMessage);
    }
}