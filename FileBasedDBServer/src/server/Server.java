package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    ServerSocket ss;
    ArrayList<ServerConnection> connections = new ArrayList<ServerConnection>();
    boolean shouldrun = true;

    public Server() {
        try {
            ss = new ServerSocket(3333);
            System.out.println("Server started");

            while (shouldrun) {
                Socket s = ss.accept();
                ServerConnection sc = new ServerConnection(s, this);
                sc.start();
                System.out.println("New connection");
                connections.add(sc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
