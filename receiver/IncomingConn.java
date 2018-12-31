package receiver;

import java.net.*;
import java.io.*;
//import java.util.concurrent.*;

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

            //lets check if we already accepted maximum number of connections
            Receiver.mijnSemafoor.probeer();

            PrintWriter pout = new PrintWriter(connection.getOutputStream(), true);
            BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((s = bin.readLine()) != null) {
                pout.println(s.toUpperCase());
            }

            // now close the socket connection
            connection.close();
            System.err.println("Connection closed: workerthread ending");
            // upping the semaphore.. since the connnection is gone....
            Receiver.mijnSemafoor.verhoog();
        }
        catch (IOException ioe) { }
        catch (InterruptedException ie) {}
    }
}
