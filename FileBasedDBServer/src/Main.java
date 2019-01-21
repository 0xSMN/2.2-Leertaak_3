
import fileController.FileConfig;
import fileController.FileController;
import server.Server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        //new Server()

        Date now = new Date();
        long milliseconds = now.getTime();
        String temp = "INSERT 1," + milliseconds + ",24.4,10.2,36.9,44.1,5.5,8.6,70.6,88.8,99.9,0.4,35.3";
        String fakedata = temp;

        for (int i = 0; i < 2000; i ++)
        {
            fakedata += temp;
        }

        System.out.println("Data ready, lines: " + fakedata.split("INSERT").length);

        long startTime = System.currentTimeMillis();
        long elapsedTimeInMS = 0;
        for (int i = 0; i < 32; i ++)   // For a minute long receiving data
        {
            FileController.incomingMessage(fakedata);
        }
        elapsedTimeInMS = (new Date()).getTime() - startTime;
        System.out.println("Data saved, time took: " + elapsedTimeInMS + "ms");

        startTime = System.currentTimeMillis();
        elapsedTimeInMS = 0;

        String getdata = "GET DATETIME, 1548073794300, 1548073794399";
        FileController.incomingMessage(getdata);

        elapsedTimeInMS = (new Date()).getTime() - startTime;
        System.out.println("Data send to client, time took: " + elapsedTimeInMS + "ms");

    }
}
