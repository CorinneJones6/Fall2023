package ServerClient;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//Corinne Jones
//HTTP Refactoring Assignment

public class HTTPRequest {

    private String verb_;
    private String parameter_;
    private String header_;
    private String fileType_;
    private final InputStream inputStream_;

    //the initial constructor which requires a socket, finds the socket input stream, and assigns that as a variable
    public HTTPRequest(Socket socket) throws IOException {
        verb_ = null;
        parameter_ = null;
        header_ = null;
        fileType_ = null;
        inputStream_ = socket.getInputStream();

    }

    //all of these get functions allow you to access the saved variable
    public String getVerb() {
        return verb_;
    }

    public String getParameter() {
        return parameter_;
    }

    public String getHeader() {
        return header_;
    }

    public String getFileType() {
        return fileType_;
    }

    /* This finds the class inputStream and puts it into the scanner. It scans through the text and parses it, assigning
    the verb GET, the parameter, and the header. If the parameter is the generic "/" it changes it to "/index.html",
    then it defines the fileType by saving everything after the end of the .*/
    public void parse() {
        //store the get, the file, and the http, throw an exception if it fails
        Scanner sc = new Scanner(inputStream_);
        if (sc.hasNext()) {
            String[] inputArray = sc.nextLine().split(" ");
            verb_ = inputArray[0];
            parameter_ = inputArray[1];
            header_ = inputArray[2];

            if (parameter_.equals("/")) {
                parameter_ = "/index.html";
            }

            int i = parameter_.lastIndexOf('.');
            if (i > 0) {
                fileType_ = parameter_.substring(i + 1);
                System.out.println(fileType_);
            }
        }
    }
}

