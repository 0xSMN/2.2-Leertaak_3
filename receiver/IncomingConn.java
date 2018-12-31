package receiver;

import java.net.*;
import java.io.*;
import java.util.*;

class IncommingConn implements Runnable
{
    private Socket connection;

    public IncommingConn(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            String s;
            System.err.println("New worker thread started");

            BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            // do usefull stuff

            // now close the socket connection
            connection.close();
            System.err.println("Connection closed: workerthread ending");
        }
        catch (IOException ioe) { }
    }
}
