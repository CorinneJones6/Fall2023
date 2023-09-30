import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import ServerClient.HTTPRequest;
import ServerClient.HTTPResponse;

public class Main {
    public static void main(String[] args) throws IOException {
    //initializes a ServerSocket
        ServerSocket server = new ServerSocket(8080);

    //Run through this while loop forever, use accept() to wait for and accept incoming client connections
        while (true) {
            Socket client = server.accept();
            //Create a response object
            HTTPResponse response = new HTTPResponse();
            //Create a request object which requires a ServerSocket to initialize
            HTTPRequest request = new HTTPRequest(client);
            try {
                //calls the parse method with the request object
                request.parse();
                //creates the file that is required to send into the response.sendResponse
                File file = response.createFile(request.getParameter());
                //sends the response back to the client, which requires a socket, a file, and a filetype
                response.sendResponse(client, file, request.getFileType());
            }
                catch(Exception e){
                //if there are any errors above, the error page is sent to site to prevent crashing
                    response.sendFailResponse(client);
                }
            }
        }
    }