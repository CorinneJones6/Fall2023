package ServerClient;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MyRunnable implements Runnable{

    Socket client_;
    ChatRoom chatRoom_;
    public MyRunnable(Socket client, ChatRoom chatRoom){
        client_=client;
        chatRoom_ = chatRoom;
        chatRoom.addClient(this);
    }


    private String handleWebSocketMessage() throws IOException {

        Boolean fin_;
        Boolean mask_;
        long payloadLen_;
        byte[] maskBytes = new byte[0];
        String message;

        DataInputStream in = new DataInputStream(client_.getInputStream());

        while (true) {

            System.out.println("handling incoming message for: " + client_ );

            byte[] input = in.readNBytes(2);

            fin_=(input[0]&128)>0;

            mask_=(input[0]&0x80)>0;

            payloadLen_=(input[1]&0x7f);

            if (payloadLen_==126){
                payloadLen_ = in.readShort();
            }
            else if (payloadLen_==127) {
                payloadLen_=in.readLong();
            }

            System.out.println("Masked: "+ mask_+" Length: "+payloadLen_);

            if(mask_){
                maskBytes=in.readNBytes(4);
            }

            byte[] payloadArr=in.readNBytes((int) payloadLen_);

            if (mask_) {
                for (int i = 0; i < payloadArr.length; i++) {
                    payloadArr[i] = (byte) (payloadArr[i] ^ maskBytes[i % 4]);
                }
                message=new String (payloadArr, StandardCharsets.UTF_8);
                System.out.println("MESSAGE: " + message + "---------------------------------------------");
            }
            else{
                message=new String(payloadArr, StandardCharsets.UTF_8);
            }

           return message;
//            System.out.println(message);

//            sendMessage(message);

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
            if( !request.parse() ) {
                System.out.println("bad req");
            }

            if(request.isWebSocket()){
                response.sendWebSocketResponse(client_,request.getWebSocketKey());
                while(true){
                    String msg=handleWebSocketMessage();
                    handleOutgoingWebSocketMessages(msg);
                }
            }
            else {

                //creates the file that is required to send into the response.sendResponse
                File file = response.createFile(request.getParameter());

                //sends the response back to the client
                response.sendHTTPResponse(client_, file, request.getFileType());
                client_.close();

            }
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

    void handleOutgoingWebSocketMessages(String message) throws IOException {
        OutputStream outputStream = client_.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        // Convert the message to bytes
        String responseMessage = message;

        byte[] responseBytes = responseMessage.getBytes(StandardCharsets.UTF_8);

        // Write the message length as a 2-byte value
        dataOutputStream.writeByte(0x81); // FIN bit set, opcode for text frame
        if (responseBytes.length < 126) {
            //Put actual length B1
            dataOutputStream.writeByte(responseBytes.length);
        }
        //If larger than 125, then needs to send the data len over B2-B3 (2 bytes, power 16 bits)
        else if(responseBytes.length< Math.pow(2,16)){
            //Put 126 in B1 to let it know to use the next two bytes
            dataOutputStream.write(126);
            //Write to next two bytes
            dataOutputStream.writeShort(responseBytes.length);
        }
        else{
            //Else largest size, send message size as 127 in B1
            dataOutputStream.write(127);
            //Write to next B2-B9 btyes
            dataOutputStream.writeLong(responseBytes.length);
        }

        // Write the message bytes
        dataOutputStream.write(responseBytes);

        // Flush the output stream to ensure the message is sent
        dataOutputStream.flush();

    }

    public void sendMessage(String message) {
        try {
            OutputStream outStream = client_.getOutputStream();
            DataOutputStream dataOut =new DataOutputStream(outStream);
            byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

            // Construct a WebSocket text frame
            byte[] frame = new byte[messageBytes.length + 2];
            frame[0] = (byte) 0x81;  // Text frame opcode
            frame[1] = (byte) messageBytes.length;  // Length of the message
            System.arraycopy(messageBytes, 0, frame, 2, messageBytes.length);

            dataOut.write(frame);
            dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static String makeJoinMsg(String room, String name){
//        return new String("{ \"type\": \"join\", \"room\": \"" + room + "\", \"user\": \"" + name + "\" }");
//    }
    //    public static String makeMsgMsg(String room, String name, String message) {
//        return new String("{ \"type\": \"message\", \"user\": \"" + room + "\", \"room\": \"" + name + "\", \"message\": \"" + message + "\" }");
//    }
//    public static String makeLeaveMsg(String room, String name) {
//        return new String("{ \"type\": \"leave\", \"room\": \"" + room + "\", \"user\": \"" + name + "\" }");
//    }

}


