
import fileController.FileConfig;
import fileController.FileController;
import server.Server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        //new Server()

        SimpleDateFormat sdf = new SimpleDateFormat(FileConfig.DATE_FORMAT);
        Date now = new Date();
        String strDate = sdf.format(now);
        String temp = "INSERT 1," + strDate + ",24.4,10.2,36.9,44.1,5.,8.6,70.6,88.8,99.9,0.4,35.3";
        String fakedata = temp;

        for (int i = 0; i < 30; i ++)
        {
            fakedata += temp;
        }

        System.out.println("Data ready, lines: " + fakedata.split("INSERT").length);

        long startTime = System.currentTimeMillis();
        long elapsedTimeInMS = 0;
        for (int i = 0; i < 300; i ++)   // For a minute long receiving data
        {
            FileController.incomingMessage(fakedata);
        }
        elapsedTimeInMS = (new Date()).getTime() - startTime;
        System.out.println("Data saved, time took: " + elapsedTimeInMS + "ms");

        startTime = System.currentTimeMillis();
        elapsedTimeInMS = 0;

        String getdata = "GET DATETIME, 2019-01-21 12:00:00, 2019-01-21 12:30:00";
        FileController.incomingMessage(getdata);

        elapsedTimeInMS = (new Date()).getTime() - startTime;
        System.out.println("Data send to client, time took: " + elapsedTimeInMS + "ms");

    }
}
