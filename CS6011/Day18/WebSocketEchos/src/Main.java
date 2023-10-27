import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import ServerClient.ChatRoom;
import ServerClient.MyRunnable;

public class Main {
    public static void main(String[] args) throws IOException {

        //create the serversocket that waits for a client request at a certain port
        ServerSocket server = new ServerSocket(8080);
        //create a chatroom
        ChatRoom chatRoom = new ChatRoom();

        while (true) {
            //wait "forever" for the client to accept
            Socket client = server.accept();
            //create a new thread for the client request, pass it the client and chatroom
            Thread thread = new Thread(new MyRunnable(client, chatRoom));
            //start the thread
            thread.start();

        }
    }
}