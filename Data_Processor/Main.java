import receiver.*;
import Storage.Sender;

public class Main {

    public static void main(String[] args) {

        //create a receiver object to handle incoming messages

        //somewere around here, we set up a object to handle the data that has been send in

        Thread s = new Thread(new Sender());
        s.start();

        Receiver.work();
    }

}
