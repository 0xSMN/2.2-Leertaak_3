import receiver.*;
import Storage.Sender;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        //create a receiver object to handle incoming messages

        //somewere around here, we set up a object to handle the data that has been send in

        ExecutorService executioner  = Executors.newFixedThreadPool(4); // map all activity to four threads

        executioner.execute(new Thread(new Sender()));


        Receiver.work(executioner);
    }

}
