package ServerClient;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MyRunnable implements Runnable {
    Socket client_;

    public MyRunnable(Socket client) {
        client_ = client;
    }

    private void handleWebSocketMessages() throws Exception {

        Boolean fin_;
//        int opcode_;
        Boolean mask_;
        long payloadLen_;
        byte[] maskArr = new byte[0]; 


        DataInputStream dataStream = new DataInputStream( client_.getInputStream() );

        while(true){

            byte[] input = dataStream.readNBytes(2); //read 2 bytes...

            fin_=(input[0]&128)>0;

            mask_=(input[0]&0x80)>0;

            payloadLen_=(input[1]&0x7f);

            if (payloadLen_==126){
                payloadLen_ = dataStream.readShort();
            }
            else if (payloadLen_==127) {
               payloadLen_=dataStream.readLong();
            }

            System.out.println("Masked: "+ mask_+" Length: "+payloadLen_);

            if(mask_){
                maskArr=dataStream.readNBytes(4);
            }

            byte[] payloadArr=dataStream.readNBytes((int) payloadLen_);

            if (mask_) {
                for (int i = 0; i < payloadArr.length; i++) {
                    payloadArr[i] = (byte) (payloadArr[i] ^ maskArr[i % 4]);
                }
            }

            String message = new String(payloadArr, StandardCharsets.UTF_8);
            System.out.println(message);

        }
    }

    @Override
    public void run() {
        //Create a response object
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
            if (!request.parse()) {
                System.out.println("Server received a bad http request... ignoring... ");
                return;
                //response.sendWebSocketResponse(client_, request.getWebSocketKey());
//                continue;
            }

            if (request.isWebSocket()) {
                response.sendWebSocketResponse(client_, request.getWebSocketKey());
                handleWebSocketMessages();
            } else {

                //creates the file that is required to send into the response.sendResponse
                File file = response.createFile(request.getParameter());

                //sends the response back to the client, which requires a socket, a file, and a filetype
                response.sendHTTPResponse(client_, file, request.getFileType());
                client_.close();
            }

        } catch (Exception e) {
            //if there are any errors above, the error page is sent to site to prevent crashing
            try {
                response.sendFailResponse(client_);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
