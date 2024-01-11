package ServerClient;
import java.io.*;
import java.net.Socket;

//Corinne Jones
//HTTP Refactoring Assignment

public class HTTPResponse {

    //createFile takes in a string as the name of the file and then returns a file
    public File createFile(String filename){

        return new File ("Resources" + filename);
    }

    /*sendResponse creates an OutputStream from the socket, passes a file into FileInputStream,
    and sends a header based on the filetype.*/
    public void sendResponse (Socket client, File file, String fileType) throws IOException {
        OutputStream outStream = client.getOutputStream();

        FileInputStream fileStream = new FileInputStream(file);
        outStream.write("http/1.1 200 Success \n".getBytes());
        if (fileType.equals("jpeg")) {
            outStream.write(("Content-type: image/" + fileType + "\n").getBytes());
        }
        else if (fileType.equals("pdf")) {
            outStream.write(("Content-type: application/" + fileType + "\n").getBytes());
        }
        else {
            outStream.write(("Content-type: text/" + fileType + "\n").getBytes());
        }
        outStream.write("\n".getBytes());
//        fileStream.transferTo(outStream);
        for( int i = 0; i < file.length();  i++ ) {
            outStream.write( fileStream.read() );
            outStream.flush();
            // Thread.sleep( 10 ); // Maybe add <- if images are still loading too quickly...
        }
        outStream.flush();
        outStream.close();
    }
    //sendFailResponse requires a socket and sends the fail html file to the client
    public void sendFailResponse(Socket client) throws IOException {
        File failFile = new File ("Resources/ErrorPage.html");
        OutputStream outStream = client.getOutputStream();
        FileInputStream failFileStream = new FileInputStream(failFile);

        outStream.write("HTTP/1.1 200 OK\n".getBytes());
        outStream.write("Content-type: text/html\n".getBytes());
        outStream.write("\n".getBytes());
        failFileStream.transferTo(outStream);
        outStream.flush();
        outStream.close();

    }
}