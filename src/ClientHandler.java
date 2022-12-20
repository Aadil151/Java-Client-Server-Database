import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    //declare variables
    Socket clientSocket;
    int clientId;
    Database db;

    //Constructor
    public ClientHandler(Socket clientSocket, int clientId, Database db) {
        this.clientSocket = clientSocket;
        this.clientId = clientId;
        this.db = db;
    }

    public void run() {
        try {
            System.out.println("ClientHandler started...");

            //Create I/O streams to read/write data, PrintWriter and BufferedReader

            BufferedReader inputClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter outputClient = new PrintWriter(clientSocket.getOutputStream(), true);
            String clientMessage;
            // Receive messages from the client and send replies, until the user types "stop"

            int titlesNum;

            while (!(clientMessage = inputClient.readLine()).equals("stop")) {
                System.out.println("Client sent the artist name " + clientMessage);
                titlesNum = db.getTitles(clientMessage);
                //   Request the number of titles from the db
                //   Send reply to Client:
                outputClient.println("Number of titles: " + titlesNum + " records found");
            }
            System.out.println("Client " + clientId + " has disconnected");
            outputClient.println("Connection closed, Goodbye!");
            // Close I/O streams and socket

            inputClient.close();
            outputClient.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
