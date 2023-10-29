package ServerClient;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MyRunnable implements Runnable {

    Socket client_;
    private static String username_;
    private static String roomName_;
    private static String message_;
    RoomManager roomManager_;

    public MyRunnable(Socket client) {
        client_ = client;
        username_=null;
        roomName_=null;
        message_=null;
    }

    private String decodeMessage() throws IOException {

        Boolean fin_;
        int opcode_;
        Boolean mask_;
        long payloadLen_;
        byte[] maskBytes = new byte[0];
        String message;

        DataInputStream in = new DataInputStream(client_.getInputStream());

            System.out.println("handling incoming message for: " + client_);

            byte[] input = in.readNBytes(2);

            fin_ = (input[0] & 0x80) > 0;

            opcode_ = (input[0] & 0x0F);

            mask_ = (input[1] & 0x80) > 0;

            payloadLen_ = (input[1] & 0x7f);

            if (payloadLen_ == 126) {
                payloadLen_ = in.readShort();
            } else if (payloadLen_ == 127) {
                payloadLen_ = in.readLong();
            }

            System.out.println("Masked: " + mask_ + " Length: " + payloadLen_);

            if (mask_) {
                maskBytes = in.readNBytes(4);
            }

            byte[] payloadArr = in.readNBytes((int) payloadLen_);

            if (mask_) {
                for (int i = 0; i < payloadArr.length; i++) {
                    payloadArr[i] = (byte) (payloadArr[i] ^ maskBytes[i % 4]);
                }
                message = new String(payloadArr, StandardCharsets.UTF_8);
                System.out.println("MESSAGE: " + message + "---------------------------------------------");
            } else {
                message = new String(payloadArr, StandardCharsets.UTF_8);
            }
            return message;
    }

    public void encodeMessage(String message) {
        try {
            DataOutputStream dataOut = new DataOutputStream(client_.getOutputStream());
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

    private void parseMessType(String message) {
       String cmd_;
       RoomManager rm = new RoomManager();
       String[] messArr = message.split(":");
       cmd_ = messArr[0];
        username_=messArr[1];
        roomName_=messArr[2];
       if(cmd_.equals("join")){
           rm.joinRoom(this);
       }
       if (cmd_.equals("leave")){
           rm.leaveRoom(this);
        }
      if (cmd_.equals("message")){
          message_=messArr[3];
            rm.sendMess(message, this);
        }
    }

    public String getUsername_(){
        return username_;
    }
    public String getRoomName_(){
        return roomName_;
    }
    public String getMessage_(){
        return message_;
    }

    public void sendMessage(String msg){
//        encodeMessage(msg);
        //send the message
    }

    public static String makeJoinMsg(String room, String name){
        return "{ \"type\": \"join\", \"room\": \"" + room + "\", \"user\": \"" + name + "\" }";
    }
    public static String makeMsgMsg(String room, String name, String message) {
        return "{ \"type\": \"message\", \"user\": \"" + roomName_ + "\", \"room\": \"" + username_ + "\", \"message\": \"" + message + "\" }";
    }
    public static String makeLeaveMsg(String room, String name) {
        return "{ \"type\": \"leave\", \"room\": \"" + roomName_ + "\", \"user\": \"" + username_ + "\" }";
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
                System.out.println("bad req");
            }

            if (request.isWebSocket()) {
                response.sendWebSockHandshake(client_, request.getWebSocketKey());
                while (true) {

                    //this decodes the packet after the handshake was completed
                    String msg = decodeMessage();

                    parseMessType(msg);

                    System.out.println("While true loop: " + msg);
//                    encodeMessage(msg);
                }
            } else {

                //creates the file that is required to send into the response.sendResponse
                File file = response.createFile(request.getParameter());

                //sends the response back to the client
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






