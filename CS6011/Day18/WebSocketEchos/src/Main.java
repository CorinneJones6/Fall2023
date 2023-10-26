import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import ServerClient.HTTPRequest;
import ServerClient.HTTPResponse;
import ServerClient.MyRunnable;

//Corinne Jones
//HTTP Refactoring Assignment

public class Main {
    public static void main(String[] args) throws IOException {
    //initializes a ServerSocket
        ServerSocket server = new ServerSocket(8080);

    //Run through this while loop forever, use accept() to wait for and accept incoming client connections
        while (true) {
            Socket client = server.accept();

            Thread thread = new Thread(new MyRunnable(client));

            thread.start();

            }
        }
    }