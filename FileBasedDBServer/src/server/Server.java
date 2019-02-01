/* Author: Daniël Geerts
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    ServerSocket ss;
    //ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
    boolean shouldrun = true;

    /* Receive here all clients trying to connect
     * For each client a new treads starts
     */
    public Server() {
        try {
        	int port = 3333;
            ss = new ServerSocket(port);
            System.out.println("Server started on port: " + port);

            while (shouldrun) {
                Socket s = ss.accept();
                ServerConnection sc = new ServerConnection(s, this);
                sc.start();
                System.out.println("New connection : " + sc.socket.getInetAddress());
                //connections.add(sc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
