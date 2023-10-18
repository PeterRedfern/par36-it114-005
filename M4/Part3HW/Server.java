package M4.Part3HW;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Collections; 

public class Server {
    int port = 3001;
    // connected clients
    private List<ServerThread> clients = new ArrayList<ServerThread>();
    boolean isShuffled = false; 
    private void start(int port) {
        this.port = port;
        // server listening
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            Socket incoming_client = null;
            System.out.println("Server is listening on port " + port);
            do {
                System.out.println("waiting for next client");
                if (incoming_client != null) {
                    System.out.println("Client connected");
                    ServerThread sClient = new ServerThread(incoming_client, this);

                    clients.add(sClient);
                    sClient.start();
                    incoming_client = null;

                }
            } while ((incoming_client = serverSocket.accept()) != null);
        } catch (IOException e) {
            System.err.println("Error accepting connection");
            e.printStackTrace();
        } finally {
            System.out.println("closing server socket");
        }
    }

    protected synchronized void disconnect(ServerThread client) {
        long id = client.getId();
        client.disconnect();
        broadcast("Disconnected", id);
    }

    protected synchronized void broadcast(String message, long id) {
        if (processCommand(message, id)) {

            return;
        }
        // let's temporarily use the thread id as the client identifier to
        // show in all client's chat. This isn't good practice since it's subject to
        // change as clients connect/disconnect
        message = String.format("User[%d]: %s", id, message);
        // end temp identifier

        // loop over clients and send out the message
        Iterator<ServerThread> it = clients.iterator();
        while (it.hasNext()) {
            ServerThread client = it.next();
            boolean wasSuccessful = client.send(message);
            if (!wasSuccessful) {
                System.out.println(String.format("Removing disconnected client[%s] from list", client.getId()));
                it.remove();
                broadcast("Disconnected", id);
            }
        }
    }

    private boolean processCommand(String message, long clientId) {
        System.out.println("Checking command: " + message);

        if (message.equalsIgnoreCase("flipcoin")) { // par36 10/13/23 - Flipcoin
            Random random = new Random(); // Creates a random object to generate heads or tails
            int randomValue = random.nextInt(2); // random number which is generated and used in the if else loop
            String result;
            if (randomValue == 0) { // If/else loop which checks if the int randomValue is equal to
                result = "heads"; // 0 and sets the String result to heads or tails
            } else {
                result = "tails";
            }
            String coinFlipMessage = clientId + " flipped a coin and got " + result; // toString for the result to be
                                                                                     // printed
            Iterator<ServerThread> it = clients.iterator(); // iterator object which is used to look through clients
            while (it.hasNext()) { // goes through the clients in the server with a while loop
                ServerThread client = it.next();
                boolean wasSuccessful = client.send(coinFlipMessage); // sends the message if the client exists
                if (!wasSuccessful) { // if the message doesn't send/has an error, it removes the client
                    it.remove();
                    disconnect(client);

                    break;
                }
            }
        }

        if (message.equalsIgnoreCase("shuffle")) { // par36 10/16/23-10/17/23 Message Shuffle
            this.isShuffled = !this.isShuffled;    
        }

        if(this.isShuffled) { // Shuffled command makes isShuffled true to being the process
            List<Character> shuffleList = new ArrayList<>(); // Creates an arrayList to hold the characters from the String
            for (char c : message.toCharArray()) // iterates through the letters in the string and puts them in a charArray
                shuffleList.add(c); // adds the characters to the charArray
            Collections.shuffle(shuffleList); // uses Collections.shuffle to shuffle the letters in the charArray

            StringBuilder builder = new StringBuilder(); // creates a new string builder
            for (char c : shuffleList) // iterates through each char in shuffleList
                builder.append(c); // uses the string builder with the chars in shufflelist to make a new string

            message = builder.toString(); // assigns the new value of the shuffled string to the original string variable
            String shuffleMessage = clientId + ":(shuffled) " + message; // toString for the result to be printed
            Iterator<ServerThread> it = clients.iterator(); // iterator object which is used to look through clients
            while (it.hasNext()) { // goes through the clients in the server with a while loop
                ServerThread client = it.next();
                boolean wasSuccessful = client.send(shuffleMessage); // sends the message if the client exists
                if (!wasSuccessful) { // if the message doesn't send/has an error, it removes the client
                    it.remove();
                    disconnect(client);

                    break;
                }
            } 
        }

        if (message.equalsIgnoreCase("disconnect")) {
            Iterator<ServerThread> it = clients.iterator();
            while (it.hasNext()) {
                ServerThread client = it.next();
                if (client.getId() == clientId) {
                    it.remove();
                    disconnect(client);

                    break;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Starting Server");
        Server server = new Server();
        int port = 3000;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Exception e) {
            // can ignore, will either be index out of bounds or type mismatch
            // will default to the defined value prior to the try/catch
        }
        server.start(port);
        System.out.println("Server Stopped");
    }
}