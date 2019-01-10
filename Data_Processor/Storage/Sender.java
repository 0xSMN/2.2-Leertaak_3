package Storage;

import receiver.Receiver;
import XML.Measurement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Sender implements Runnable{
    private ArrayList<Measurement> dataList = new ArrayList<Measurement>();

    public void run() {
        while (true) {
            dataList.addAll(Receiver.getData());

            //TODO for now, the list is emptied, in the final product, the data will be stored in the database
            if (!dataList.isEmpty()) {
                Iterator<Measurement> i = dataList.iterator();

                while (i.hasNext()) {
                    Measurement m = i.next();
                    System.out.println(m.GenSendString());
                }
            }

            dataList.clear();
//            Thread.sleep(5000);
        }
    }
}
