package receiver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Receiver {
    private static final int PORT = 7789;
    private static final int maxnrofConnections=4;
    private static int connections = 0; // int to keep track of the number of connections

    public static void work() {
        Socket connection;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Receiver started on port " + PORT);
            System.out.println("maximum number of connections of: " + maxnrofConnections);

            while (true) {
                if (connections < maxnrofConnections) {
                    connection = server.accept();
                    System.err.println("New connection established");
                    Thread conn = new Thread(new IncomingConn(connection));
                    conn.start();
                    connections++;
                }
                else {
                    //System.out.println("Maximum number of threads exceeded");
                }
            }
        }

        catch (java.io.IOException ioe) { }
    }

    public static synchronized void addData(){
        // do stuff
    }

    public static synchronized void decConns() { // decreases the number of active connections
        connections--;
    }
}
