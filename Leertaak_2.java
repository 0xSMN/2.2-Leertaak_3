import receiver.*;

public class Leertaak_2 {

    public static void main(String[] args) {

        //create a receiver object to handle incoming messages
        Thread receiver = new Thread(new Receiver());
        receiver.run();

    }

}
