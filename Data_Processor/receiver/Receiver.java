package receiver;

import XML.Measurement;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Receiver {
    private static final int PORT = 7789;
    private static final int maxnrofConnections=800;
    private static int connections = 0; // int to keep track of the number of connections
    private static ArrayList<Measurement> data = new ArrayList<Measurement>();
    private static ExecutorService executioner  = Executors.newFixedThreadPool(maxnrofConnections/2);


    public static void work() {
        Socket connection;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Receiver started on port " + PORT);
            System.out.println("maximum number of connections of: " + maxnrofConnections);

            while (true) {
                if (connections < maxnrofConnections) {
                    connection = server.accept();

//                    Thread conn = new Thread(new IncomingConn(connection));
//                    conn.start();

                    executioner.execute(new IncomingConn(connection));
                    connections++;
                    if (connections % 50 == 0) {
                        System.err.println("New connection established, there are " + connections + " connections");
                    }
                }
                else {
                    //System.out.println("Maximum number of threads exceeded");
                }
            }
        }

        catch (java.io.IOException ioe) { }
    }


    public static synchronized void decConns() { // decreases the number of active connections
        connections--;
    }

    public static synchronized void addData(ArrayList<Measurement> measurements) {
        data.addAll(measurements);
//        System.out.println("Data added, there are now " + data.size() + " measurements");
    }

    //geeft alle data terug en leegt de arraylist
    public synchronized static ArrayList<Measurement> getData() {
        ArrayList<Measurement> data2 = new ArrayList<>(data);
        data.clear();
        return data2;
    }
}
