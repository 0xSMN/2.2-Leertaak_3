package receiver;

import java.net.ServerSocket;
import java.net.Socket;

public class Receiver {
    public static final int PORT = 7789;
    private static final int maxnrofConnections=4;

    public static void main(String[] args) {
        Socket connection;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.err.println("MT Server started..bring on the load, to a maximum of: " + maxnrofConnections);

            while (true) {
                connection = server.accept();
                System.err.println("New connection accepted..handing it over to worker thread");
                Thread conn = new Thread(new IncommingConn(connection));
                conn.start();
            }
        }

        catch (java.io.IOException ioe) { }
    }
}
