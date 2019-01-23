package server;

import fileController.FileController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ServerConnection extends Thread {

    Socket socket;
    Server server;
    DataInputStream din;
    DataOutputStream dout;
    boolean shouldrun = true;

    public ServerConnection(Socket socket, Server server) {
        super("ServerConnectionThread");
        this.socket = socket;
        this.server = server;
    }

    public void sendStringToClient(String text) {
        try {
            dout.writeUTF(text);
            dout.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendStringToAllClients(String text) {
        for (int index = 0; index < server.connections.size(); index++) {
            ServerConnection sc = server.connections.get(index);
            sc.sendStringToClient(text);
        }
    }

    @Override
    public void run() {
        try {
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            while (shouldrun) {
                while (din.available() == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String textIn = din.readUTF();
                // System.out.println(textIn);
                // Send incoming message to the FileController
                List<String> records = FileController.incomingMessage(textIn);

                if (records != null && records.size() > 1) {
                    System.out.println("Records to send: " + records.size());
                    int maxToSend = 750;
                    int counter = 0;

                    for (int i = 0; i < records.size(); i += maxToSend) {
                        String tosend = "";
                        for (int j = 0; j < maxToSend; j++) {
                            int element = (counter*maxToSend)+j;
                            if (element < records.size()) {
                                tosend += records.get(element);
                            }
                        }
                        byte[] b = tosend.getBytes();
                        System.out.println("Bytes to send: " + b.length);
                        sendStringToClient(tosend);
                        counter += 1;
                    }
                }
            }
            din.close();
            dout.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
