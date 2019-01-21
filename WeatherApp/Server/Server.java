package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static final int PORT = 1234;

    public static void work() {
        Socket connection;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started");

            while (true) {
                connection = server.accept();
                System.out.println("connection established");

                BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String data = "";
                String s;

                while (true) {
                    if (!bin.ready() && !data.equals("")) { // the client has finished its request header
                        System.out.println(data);
                        break;
                    }

                    if ((s = bin.readLine()) != null) { // read the clients request
                        data = data.concat(s);
                        data = data.concat("\n");
                    } else {
                        break;
                    }
                }

                // write the reply to the client
                PrintWriter dataOut = new PrintWriter(connection.getOutputStream());
                // write the http-html header to the printWriter
                dataOut.print("HTTP/1.1 200 OK\n");
                dataOut.print("Content-type:text/html\n");
                dataOut.print("Connection: close\n");
                dataOut.println(); // a blank line to indicate the end of the header

                // open the HTML file
                BufferedReader fReader = new BufferedReader(new FileReader("HTML/index.html"));
                String outLine;

                // write the HTML to the printWriter
                while ((outLine = fReader.readLine()) != null) {
                    if (outLine.equals("")) {
                        continue;
                    }
                    System.err.println(outLine.trim());
                    dataOut.print(outLine.trim() + "\n");
                }

                // send everything and close the connection
                dataOut.flush();
                connection.close();
            }
        }
        catch (IOException ioe) {System.err.println("IOException");
        ioe.printStackTrace();
        }
    }
}
