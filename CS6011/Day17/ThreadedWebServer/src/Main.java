import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import ServerClient.HTTPRequest;
import ServerClient.HTTPResponse;
import ServerClient.MyRunnable;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        //initializes a ServerSocket
        ServerSocket server = new ServerSocket(8080);

        //Run through this while loop forever, use accept() to wait for and accept incoming client connections
        while (true) {
            Socket client = server.accept();
            //Create a response object
            Thread thread = new Thread(new MyRunnable(client));

            thread.start();

        }
    }
}