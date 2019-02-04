package Storage;

import receiver.Receiver;
import XML.Measurement;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;


public class Sender implements Runnable{
    private ArrayList<Measurement> dataList;
    private static final String DatabaseHost = "0.0.0.0"; // de hostnaam of ip adress van de database
    private static final int DatabasePort = 3333; // de port waarop de data wordt ontvangen door de database
    private DataOutputStream DataOut;
    private BufferedReader DataIn;

    public void run() {

        while (true) {
            try {
                Socket connection = makeConnection(DatabaseHost, DatabasePort);

                if (connection != null) {
//                set the input and output streams
                    DataOut = new DataOutputStream(connection.getOutputStream());
                    DataIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    connection.setSoTimeout(500);

                    // keep sending until the connection is closed by the database
                    while (true) {
                        try {
                            if (DataIn.readLine() == null) {
                                break; // the connection has been closed, breaking out of the loop and reconnecting
                            }
                        } catch (SocketTimeoutException ste) {
                        } // connection is still alive, trying to send data

                        dataList = Receiver.getData();
                        if (!dataList.isEmpty()) {
                            Iterator<Measurement> i = dataList.iterator();
                            while (i.hasNext()) {
                                Measurement m = i.next();
                                DataOut.writeUTF(m.GenSendString());
                            }
                        }
                        Thread.yield();
                    }
                }
            }
            catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
    }

    private Socket makeConnection(String hostName, int port) {
        try {
            return new Socket(hostName, port);
        }
        catch (UnknownHostException uhe) {
            System.err.println("UnknownHostException");
        }
        catch (IOException ioe) {
            System.err.println("IOException");
        }
        return null;
    }
}

