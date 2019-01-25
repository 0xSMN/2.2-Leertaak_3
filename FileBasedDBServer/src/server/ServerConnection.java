/* Author: Daniël Geerts
 */
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

    /* Init a single client here
     * This class represents one client that has connected to the server
     */
    public ServerConnection(Socket socket, Server server) {
        super("ServerConnectionThread");
        this.socket = socket;
        this.server = server;
    }

    /* Send here a string to this client
     */
    public void sendStringToClient(String text) {
        try {
            dout.writeUTF(text);
            dout.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /* Override of the Run function (starts when Thread is started)
     * Checks here if there is an incoming message
     * Then sends that message to the FileController
     * When done, you receive a list of records (with GET request, with INSERT request list will be empty)
     * Then send records to this client, if no records send '[]'
     */
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
                System.out.println("INSERTS received: " + textIn.split("INSERT").length);
                // Send incoming message to the FileController
                List<String> records = FileController.incomingMessage(textIn);

                if (records != null && records.size() >= 1) {
                    System.out.println("Records to send: " + records.size());
                    int maxToSend = 750;
                    int counter = 0;
                    int times = 0;
                    int totalSend = 0;

                    String tosend = "";

                    for (int i = 0; i < records.size(); i ++) {
                        tosend += records.get(i);
                        counter ++;
                        totalSend ++;

                        if (counter >= maxToSend ||
                            counter >= records.size() ||
                            counter + (maxToSend * times) >= records.size()) {
                            sendStringToClient("[" + tosend + "]");

                            tosend = "";
                            counter = 0;
                            times ++;
                        }
                    }
                    System.out.println("Total records send: " + totalSend);
                } else if (records != null && records.size() < 1) {
                    sendStringToClient("[]");
                }
            }
            System.out.println("Connection closed : " + socket.getInetAddress());
            din.close();
            dout.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
