import java.io.*;
import java.net.*;

public class Server {

    public static void main(String args[]) throws IOException {
        //Open the server socket

        ServerSocket serverSocket = new ServerSocket(Credentials.PORT);
        System.out.println("Server is running");

        int clientId = 1;

        //Create a Database object and check the connection with establishDBConnection():
        //If the db connection fails, print:
        // System.out.println("DB connection fail, stopping.");
        //else, print:
        //  System.out.println("Server is now connected to DB");

        Database db = new Database();

        if (db.establishDBConnection()) {
            System.out.println("Server is now connected to DB");
        } else {
            System.out.println("DB connection fail, stopping.");
            System.exit(0);
        }


        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client " + clientId + " connected with IP " + clientSocket.getInetAddress().getHostAddress());

            ClientHandler clientHandler = new ClientHandler(clientSocket, clientId, db);
            new Thread(clientHandler).start();

            clientId++;
        }
    }
    // Continuously listen for client requests
    //    clientId. The clientId is not reassigned once used.
    //   Display clientId and IP address:
    //  System.out.println("Client " + clientId + " connected with IP " + clientSocket.getInetAddress().getHostAddress());
    // Create a new client handler object and start the thread
}


