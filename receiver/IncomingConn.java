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
            String data = "";

            BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while (true) {
                // reads the incoming message and prints it to the console

                //als dit if statement waar is, dan is er even geen data verzonden, de bestaande data kan worden verwerkt
                if (!bin.ready() && !data.equals("")) {
                    //TODO: data laten verwerken door XML_parser
                    System.out.println("data verwerken");
                    //TODO: data legen
                }

                if ((s = bin.readLine()) != null) {
                    data = data.concat(s);
                    data = data.concat("\n");
                }
                else {
                    break;
                }
            }

            // now close the socket connection
            connection.close();
            System.err.println("Connection closed");

            System.out.println(data);

            Receiver.decConns(); //notifies that the thread is no longer in use by decresing the number of connections
            // waits for the garbage collection to kill the thread
        }
        catch (IOException ioe) { }
    }
}
