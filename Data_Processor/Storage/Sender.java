package Storage;

import receiver.Receiver;
import XML.Measurement;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;


public class Sender implements Runnable{
    private ArrayList<Measurement> dataList;
    private static final String DatabaseHost = "0.0.0.0"; // de hostnaam of ip adress van de database
    private static final int DatabasePort = 3333; // de port waarop de data wordt ontvangen door de database

    public void run() {

        try {


            Socket connection = new Socket(DatabaseHost, DatabasePort);
            DataOutputStream DataOut = new DataOutputStream(connection.getOutputStream());

            while (true) {
                dataList = Receiver.getData();
                if (!dataList.isEmpty()) {
                    Iterator<Measurement> i = dataList.iterator();
                    while (i.hasNext()) {
                        Measurement m = i.next();
                        String output = m.GenSendString();
                        System.out.println(output);
                        DataOut.writeUTF(output);
                    }
                }
                Thread.yield();
            }

//            connection.close();
        } catch (UnknownHostException uhe) {
            System.err.println("Cannot connect to database: unknown host");
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        System.out.println("Sender stopped");
    }
}

