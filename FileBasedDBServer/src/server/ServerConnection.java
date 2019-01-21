package server;

import fileController.FileController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
                System.out.println(textIn);
                // Send incoming message to the FileController
                String records = FileController.incomingMessage(textIn, ID);
                if (records != null) {
                    sendStringToClient(records);
                }
                //sendStringToAllClients(textIn);
            }
            din.close();
            dout.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
