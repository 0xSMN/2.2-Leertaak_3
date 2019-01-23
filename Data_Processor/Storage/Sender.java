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
    private ArrayList<Measurement> dataList = new ArrayList<Measurement>();
    public static final String DatabaseHost = ""; // de hostnaam of ip adress van de database
    public static final int DatabasePort = 1234; // de port waarop de data wordt ontvangen door de database

    public void run() {
        while (true) {
            dataList.addAll(Receiver.getData());



            if (!dataList.isEmpty()) {
                try {
                    Iterator<Measurement> i = dataList.iterator();

                    Socket connection = new Socket(DatabaseHost, DatabasePort);
                    DataOutputStream DataOut = new DataOutputStream(connection.getOutputStream());

                    while (i.hasNext()) {
                        Measurement m = i.next();
                        String output = m.GenSendString();
                        System.out.println(output);
                        DataOut.writeUTF(output);

                    }

                    connection.close();
                }
                catch (UnknownHostException uhe) {
                    System.err.println("Cannot connect to database: unknown host");
                }
                catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }

            dataList.clear();
//            Thread.sleep(5000);
        }
    }
}

