import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import ServerClient.ChatRoom;
import ServerClient.MyRunnable;

public class Main {
    public static void main(String[] args) throws IOException {

        // Create an ArrayList to store MyRunnable objects
        ArrayList<MyRunnable> runnArr = new ArrayList<>();

        // Create the ServerSocket that waits for a client request at a certain port (8080)
        ServerSocket server = new ServerSocket(8080);

        // Continuously wait for client connections
        while (true) {
            Socket client = server.accept();

            Thread thread = new Thread(new MyRunnable(client));

            thread.start();
//            // Wait for a client to connect
//            // Accept the client connection and create a socket for communication
//            Socket client = server.accept();
//
//            // Create a new MyRunnable instance, passing the client socket
//            MyRunnable myRunnable=new MyRunnable(client);
//
//            // Add the MyRunnable instance to the ArrayList for later reference
//            runnArr.add(myRunnable);
//
//            // Create a new thread, passing the MyRunnable instance
//            Thread thread = new Thread(myRunnable);
//
//            // Start the thread to handle the client request concurrently
//            thread.start();

        }
    }
}