package Project.server;

import java.util.List; // par36 11/21/23 - added for mutelist function
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList; // par36 11/21/23 - added for mutelist function
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter; // par36 12/7/23 - Used to write MuteList to file
import java.io.File; // par36 12/7/23 - Used to work with files
import java.util.Scanner; // par36 12/7/23 - Used to take information from the files
import Project.common.Constants;
import Project.common.Payload;
import Project.common.PayloadType;
import Project.common.RoomResultPayload;

/**
 * A server-side representation of a single client
 */
public class ServerThread extends Thread {
    protected Socket client;
    private String clientName;
    private boolean isRunning = false;
    private ObjectOutputStream out;// exposed here for send()
    // private Server server;// ref to our server so we can call methods on it
    // more easily
    protected Room currentRoom;
    private static Logger logger = Logger.getLogger(ServerThread.class.getName());
    private long myClientId;
    private List<String> muteList = new ArrayList<String>(); // par36 11/21/23 - created list for mutelist function

    public void setClientId(long id) {
        myClientId = id;
    }

    public long getClientId() {
        return myClientId;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public ServerThread(Socket myClient, Room room) {
        logger.info("ServerThread created");
        // get communication channels to single client
        this.client = myClient;
        // this.currentRoom = room;
        setCurrentRoom(room);

    }

    public void muteListText() { // par36 12/10/23 - Writes the muteList to a file
        try {
            Scanner scanner = new Scanner(new File(fileName())); // gets the name for the file from the fileName method, uses the user's name
            String muteNameList = scanner.nextLine(); // scans in the names entered to the stringArray
            muteNameList = muteNameList.replace("[","").replace("]","");
            String[] nameListData = muteNameList.split(","); // puts a comma at the end of each name
            System.out.println(muteNameList);
            for(String people: nameListData) { // for each person in the StringArray
                //mute(people.trim()); 
                muteList.add(people.trim()); // add them to the muteList (.trim to remove any spaces)
                System.out.println(people);
            }
            scanner.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String fileName() { // par36 12/10/23 - fileName for muteList
        String client = getClientName(); // gets the client's name
        String fileName = client + ".txt"; // uses their name to create and identify their list
        return fileName;
    }

    private void newMuteList() { // par36 12/10/23 - updates the muteList 
        FileWriter editedNameList; // creates a new fileWriter for the updates to the muteList
        try {
            editedNameList = new FileWriter(fileName()); 
            editedNameList.write(muteList.toString()); // writes new changes to the muteList
            editedNameList.close(); // closes the fileWriter
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void mute(String name) { // par36 11/21/23 - mute method
        if (!muteList.contains(name)) { // if the mutelist doesn't have the name of the muted person
            muteList.add(name); // it then adds the name to the list
            ServerThread target = currentRoom.getClientByName(name); 
            if(target != null) {
                sendMute(target.getClientId()); 
            }
            newMuteList(); 
        }
    }

    protected void unmute(String name) { // par36 12/10/23 - unmute method (updated)
        if (muteList.contains(name)) { // if the mutelist has the name of the muted person
            muteList.remove(name); // it removes the name from the list (unmuting them)
            ServerThread target = currentRoom.getClientByName(name); 
            if(target != null) {
                sendUnmute(target.getClientId()); 
            }
            newMuteList(); 
        }
    }

    protected boolean isMuted(String name) { // par36 11/21/23 - isMuted method
        return muteList.contains(name); // returns true or false if someone's name is in the mutelist or not
    }

    protected void setClientName(String name) {
        if (name == null || name.isBlank()) {
            logger.warning("Invalid name being set");
            return;
        }
        clientName = name;
    }

    public String getClientName() {
        return clientName;
    }

    protected synchronized Room getCurrentRoom() {
        return currentRoom;
    }

    protected synchronized void setCurrentRoom(Room room) {
        if (room != null) {
            currentRoom = room;
            sendRoomName(room.getName());
        } else {
            logger.info("Passed in room was null, this shouldn't happen");
        }
    }

    public void disconnect() {
        sendConnectionStatus(myClientId, getClientName(), false);
        logger.info("Thread being disconnected by server");
        isRunning = false;
        cleanup();
    }

    // send methods
    public boolean sendReadyStatus(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.READY);
        p.setClientId(clientId);
        return send(p);
    }

    public boolean sendRoomName(String name) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.JOIN_ROOM);
        p.setMessage(name);
        return send(p);
    }

    public boolean sendRoomsList(String[] rooms, String message) {
        RoomResultPayload payload = new RoomResultPayload();
        payload.setRooms(rooms);
        if (message != null) {
            payload.setMessage(message);
        }
        return send(payload);
    }

    public boolean sendExistingClient(long clientId, String clientName) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.SYNC_CLIENT);
        p.setClientId(clientId);
        p.setClientName(clientName);
        return send(p);
    }

    public boolean sendResetUserList() {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.RESET_USER_LIST);
        return send(p);
    }

    public boolean sendClientId(long id) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.CLIENT_ID);
        p.setClientId(id);
        return send(p);
    }

    public boolean sendMessage(long clientId, String message) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MESSAGE);
        p.setClientId(clientId);
        p.setMessage(message);
        return send(p);
    }

    public boolean sendConnectionStatus(long clientId, String who, boolean isConnected) {
        Payload p = new Payload();
        p.setPayloadType(isConnected ? PayloadType.CONNECT : PayloadType.DISCONNECT);
        p.setClientId(clientId);
        p.setClientName(who);
        p.setMessage(String.format("%s the room %s", (isConnected ? "Joined" : "Left"), currentRoom.getName()));
        return send(p);
    }

    public boolean sendMute(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.MUTED);
        p.setClientId(clientId);
        p.setMessage(clientName + " muted someone");
        return send(p); 
    }

    public boolean sendUnmute(long clientId) {
        Payload p = new Payload();
        p.setPayloadType(PayloadType.UNMUTED);
        p.setClientId(clientId);
        p.setMessage(clientName + " unmuted someone");
        return send(p); 
    }

    private boolean send(Payload payload) {
        try {
            logger.log(Level.FINE, "Outgoing payload: " + payload);
            out.writeObject(payload);
            logger.log(Level.INFO, "Sent payload: " + payload);
            return true;
        } catch (IOException e) {
            logger.info("Error sending message to client (most likely disconnected)");
            // uncomment this to inspect the stack trace
            // e.printStackTrace();
            cleanup();
            return false;
        } catch (NullPointerException ne) {
            logger.info("Message was attempted to be sent before outbound stream was opened: " + payload);
            // uncomment this to inspect the stack trace
            // e.printStackTrace();
            return true;// true since it's likely pending being opened
        }
    }

    // end send methods
    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());) {
            this.out = out;
            logger.info("Listening for Client Payloads");
            isRunning = true;
            Payload fromClient;
            while (isRunning && // flag to let us easily control the loop
                    (fromClient = (Payload) in.readObject()) != null // reads an object from inputStream (null would likely mean a disconnect)
            ) {

                logger.info("Received from client: " + fromClient);
                processPayload(fromClient);

            } // close while loop
        } catch (Exception e) {
            // happens when client disconnects
            System.out.println(Constants.ANSI_BRIGHT_RED);
            e.printStackTrace();
            System.out.println(Constants.ANSI_RESET);
            logger.info("Client disconnected");
        } finally {
            isRunning = false;
            logger.info("Exited thread loop. Cleaning up connection");
            cleanup();
        }
    }

    void processPayload(Payload p) {
        switch (p.getPayloadType()) {
            case CONNECT:
                setClientName(p.getClientName());
                muteListText(); // par36 12/12/23 - added to ensure muteLists load at the beggining of a session
                break;
            case DISCONNECT:
                Room.disconnectClient(this, getCurrentRoom());
                break;
            case MESSAGE:
                if (currentRoom != null) {
                    currentRoom.sendMessage(this, p.getMessage());
                } else {
                    // TODO migrate to lobby
                    logger.log(Level.INFO, "Migrating to lobby on message with null room");
                    Room.joinRoom(Constants.LOBBY, this);
                }
                break;
            case GET_ROOMS:
                Room.getRooms(p.getMessage().trim(), this);
                break;
            case CREATE_ROOM:
                Room.createRoom(p.getMessage().trim(), this);
                break;
            case JOIN_ROOM:
                Room.joinRoom(p.getMessage().trim(), this);
                break;
            /*
            case MUTED:
                currentRoom.sendMute(p.getMessage().trim(), this); 
                break; 
            case UNMUTED:
                sendUnmute(p.getMessage().trim(), this); 
                break;
            */
            default:
                break;

        }

    }

    private void cleanup() {
        logger.info("Thread cleanup() start");
        try {
            client.close();
        } catch (IOException e) {
            logger.info("Client already closed");
        }
        logger.info("Thread cleanup() complete");
    }
}