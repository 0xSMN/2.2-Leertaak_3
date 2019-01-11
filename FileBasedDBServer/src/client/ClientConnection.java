package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnection extends Thread {
    Socket s;
    DataInputStream din;
    DataOutputStream dout; // global variables
    boolean shouldrun = true;

    public ClientConnection(Socket socket, Client client) {
        s = socket;

    }

    public void sendStringToServer(String text) {
        try {
            dout.writeUTF(text);
            dout.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void run() {

        try {
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (shouldrun)
                try {
                    while (din.available() == 0) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                        }
                    }
                    String reply = din.readUTF();
                    System.out.println(reply);
                } catch (IOException e) {
                    e.printStackTrace();
                    close();
                }

        } catch (IOException e) {

            e.printStackTrace();
            close();
        }

    }

    public void close() {
        try {
            din.close();
            dout.close();
            s.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
