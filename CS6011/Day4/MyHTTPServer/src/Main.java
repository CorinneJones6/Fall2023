import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import ServerClient.HTTPRequest;
import ServerClient.HTTPResponse;

public class Main {
    public static void main(String[] args) throws IOException {

        String inputString = "";
        String[] inputArray = new String[0];
        String extension = "";
        ArrayList<String> a1 = new ArrayList<String>();

//initializes a ServerSocket
        ServerSocket server = new ServerSocket(8080);

//Run through this while loop forever, use accept() to wait for and accept incoming client connections
        while (true) {
            Socket client = server.accept();
//Sets up a Scanner class object sc which is associated with the client socket
            Scanner sc = new Scanner(client.getInputStream());

            if (sc.hasNext()) {
                inputArray = sc.nextLine().split(" ");
                inputString = inputArray[1];
                System.out.println("From client: " + inputString);
                if (inputString.equals("/")) {
                    inputString = "/index.html";
                }

                   File file = new File("Resources" + inputString);
                   File failFile = new File ("Resources/ErrorPage.html");

                OutputStream outStream = client.getOutputStream();

                   try {
                       int i = inputString.lastIndexOf('.');
                       if (i > 0) {
                           extension = inputString.substring(i + 1);
                           System.out.println(extension);
                       }

                   FileInputStream fileStream = new FileInputStream(file);

                    if (extension.equals("jpeg")) {
                        outStream.write("http/1.1 200 Success \n".getBytes());
                        outStream.write(("Content-type: image/jpeg" + extension + "\n").getBytes());
                    } else if (extension.equals("pdf")) {
                        outStream.write("http/1.1 200 Success \n".getBytes());
                        outStream.write(("Content-type: application/pdf" + extension + "\n").getBytes());
                    } else {
                        outStream.write("http/1.1 200 Success \n".getBytes());
                        outStream.write(("Content-type: text/" + extension + "\n").getBytes());
                    }
                    outStream.write("\n".getBytes());
                    fileStream.transferTo(outStream);
                    outStream.flush();
                    outStream.close();
                   }

                    catch(Exception e){
                    FileInputStream failFileStream = new FileInputStream(failFile);
                    outStream.write("HTTP/1.1 200 OK\n".getBytes());
                    outStream.write("Content-type: text/html\n".getBytes());
                    outStream.write("\n".getBytes());
                    failFileStream.transferTo(outStream);
                    outStream.flush();
                    outStream.close();
                }
            }
        }
    }
}