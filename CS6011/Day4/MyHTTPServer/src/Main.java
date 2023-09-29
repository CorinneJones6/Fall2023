import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {

        String inputString = "";
        String[] inputArray = new String[0];
        String extension = "";
        Path file = null;
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
            }

            OutputStream outStream = client.getOutputStream();

                file = Paths.get("/Users/corinnejones/myGithubrepo/CS6011/Day4/MyHTTPServer/Resources" + inputString);


                int i = inputString.lastIndexOf('.');
                if (i > 0) {
                    extension = inputString.substring(i + 1);
                    System.out.println(extension);
                }

                if (extension.equals("jpeg")) {
                    outStream.write("http/1.1 200 Success \n".getBytes());
                    outStream.write(("Content-type: image/jpeg" + extension + "\n").getBytes());
                    outStream.write("\n".getBytes());
                } else if (extension.equals("pdf")) {
                    outStream.write("http/1.1 200 Success \n".getBytes());
                    outStream.write(("Content-type: application/pdf" + extension + "\n").getBytes());
                    outStream.write("\n".getBytes());
                } else {
                    outStream.write("http/1.1 200 Success \n".getBytes());
                    outStream.write(("Content-type: text/" + extension + "\n").getBytes());
                    outStream.write("\n".getBytes());
                }

                byte[] fileContent = Files.readAllBytes(file);
                outStream.write(fileContent);

                outStream.flush();
                outStream.close();

        }
    }
}