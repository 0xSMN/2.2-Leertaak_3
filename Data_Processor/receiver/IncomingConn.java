package receiver;

import java.net.*;
import java.io.*;
import java.util.*;
import XML.*;

public class IncomingConn implements Runnable
{
    private Socket connection;
    private ArrayList<Measurement> dataList = new ArrayList<Measurement>();

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
                    //data laten verwerken door XML_parser
                    //System.out.println(data);
                    Receiver.addData(XML_Parser.ReadXML(data));

                    //data legen
                    data= "";
                }
                if (bin.ready()) {
                    if ((s = bin.readLine()) != null) {
                        data = data.concat(s);
                        data = data.concat("\n");
                    } else {
                        break;
                    }
                }
                Thread.yield();
            }

            // now close the socket connection
            connection.close();
            System.err.println("Connection closed");

            //System.out.println(data);

            Receiver.decConns(); //notifies that the thread is no longer in use by decreasing the number of connections
            // waits for the garbage collection to kill the thread
        }
        catch (IOException ioe) { }
    }
}
