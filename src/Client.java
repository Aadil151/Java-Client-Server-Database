import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) throws IOException {
        String artistName;
        String serverMessage;

        //Open a connection to the server, create the client socket

        Socket clientSocket = new Socket(Credentials.HOST, Credentials.PORT);
        System.out.println("Client is running");

        //   Create I/O streams to read/write data, PrintWriter and BufferedReader
        PrintWriter outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));

        // Read messages continuously until the user types "stop"
        while (true) {
            System.out.println("Enter the artist name:");
            artistName = inputFromUser.readLine();
            System.out.println("You entered " + artistName);

            //    Send message to the server
            outputToServer.println(artistName);
            //    Receive response from the server
            serverMessage = inputFromServer.readLine();
            System.out.println("FROM SERVER: " + serverMessage);
            if (artistName.equals("stop"))
                break;
        }

        //  Close I/O streams and socket
        inputFromServer.close();
        inputFromUser.close();
        outputToServer.close();
        clientSocket.close();
    }
}
