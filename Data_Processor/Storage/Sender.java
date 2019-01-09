package Storage;

import receiver.Receiver;
import XML.Measurement;
import java.util.ArrayList;
import java.util.Collections;

public class Sender implements Runnable{
    private ArrayList<Measurement> dataList = new ArrayList<Measurement>();

    public void run() {
        dataList.addAll(Receiver.getData());

        //TODO for now, the list is emptied, in the final product, the data will be stored in the database
        dataList.clear();
    }
}
