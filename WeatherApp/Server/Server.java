package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static final int PORT = 12345;

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
                DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
                dataOut.writeUTF("HTTP/1.1 200 OK");
                dataOut.writeUTF("Content-type:text/html");
                dataOut.writeUTF("Connection: close");
                dataOut.writeUTF("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<title>Page Title</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h1>This is a Heading</h1>\n" +
                        "<p>This is a paragraph.</p>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html> ");
                connection.close();
            }
        }
        catch (IOException ioe) {System.err.println("IOException");
        ioe.printStackTrace();
        }
    }
}
