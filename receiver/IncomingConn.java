package receiver;

import java.net.*;
import java.io.*;
import java.util.*;

public class IncomingConn implements Runnable
{
    private Socket connection;

    public IncomingConn(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            String s;
            System.err.println("New worker thread started");

            BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // reads the incoming message and prints it to the console
            while ((s = bin.readLine()) != null) {
                System.out.println(s);
            }

            // now close the socket connection
            connection.close();
            System.err.println("Connection closed: workerthread ending");

            Receiver.decConns(); //notifies that the thread is no longer in use by decresing the number of connections
            // waits for the garbage collections kill the thread
        }
        catch (IOException ioe) { }
    }
}
