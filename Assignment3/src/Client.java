import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) throws IOException {
        Socket clientSocket = new Socket(Credentials.HOST, Credentials.PORT);
        System.out.println("Client is running");
        PrintWriter outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter the artist name:");
            String artistName = inputFromUser.readLine();
            System.out.println("You entered " + artistName);
            outputToServer.println(artistName);
            String serverMessage = inputFromServer.readLine();
            if (artistName.equals("stop")){
                System.out.println("FROM SERVER: " + serverMessage);
                break;}
            System.out.println("FROM SERVER: " + serverMessage);
        }
        inputFromServer.close();
        inputFromUser.close();
        outputToServer.close();
        clientSocket.close();
        /*Open a connection to the server, create the client socket
          System.out.println("Client is running");
          Create I/O streams to read/write data, PrintWriter and BufferedReader
          Read messages continuously until the user types "stop"
             System.out.println("Enter the artist name:");
             System.out.println("You entered " + artistName);
             Send message to the server
             Receive response from the server
             System.out.println("FROM SERVER: " + serverMessage);
          Close I/O streams and socket*/
    }
}
