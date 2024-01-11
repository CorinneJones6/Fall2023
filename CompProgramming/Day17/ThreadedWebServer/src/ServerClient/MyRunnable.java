package ServerClient;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class MyRunnable implements Runnable{

    Socket client_;
    public MyRunnable(Socket client){
        client_=client;
    }
    @Override
    public void run() {
        HTTPResponse response = new HTTPResponse();
        //Create a request object which requires a ServerSocket to initialize
        HTTPRequest request = null;
        try {
            request = new HTTPRequest(client_);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //calls the parse method with the request object
            request.parse();
            //creates the file that is required to send into the response.sendResponse
            File file = response.createFile(request.getParameter());
            //sends the response back to the client, which requires a socket, a file, and a filetype
            response.sendResponse(client_, file, request.getFileType());
        }
        catch(Exception e){
            //if there are any errors above, the error page is sent to site to prevent crashing
            try {
                response.sendFailResponse(client_);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
