package receiver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Receiver implements Runnable {
    private static final int PORT = 7789;
    private static final int maxnrofConnections=4;
    private static ArrayList conns = new ArrayList(); // een arraylist om de connections in te bewaren

    public void run() {
        Socket connection;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.err.println("MT Server started..bring on the load, to a maximum of: " + maxnrofConnections);

            while (true) {
                if (conns.size() < maxnrofConnections) {
                    connection = server.accept();
                    System.err.println("New connection accepted..handing it over to worker thread");
                    Thread conn = new Thread(new IncommingConn(connection));
                    conn.start();
                    conns.add(conn);
                }
            }
        }

        catch (java.io.IOException ioe) { }
    }

    public static void addData(){
        // do stuff
    }
}
