/* Author: Daniël Geerts
 */
package testclient;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;

public class Client {

    ClientConnection cc;

    private static int linesOfCode = 20;
    private static int inputXTimes = 2;

    private static int DoThisXTimes = 30;

    private static Client client;

    public static void main(String[] args) {
        client = new Client();

        //sendDataToServer();
        getDataToServer();

    }

    public Client() {
        try {
            Socket s = new Socket("localhost", 3333);
            cc = new ClientConnection(s, this);
            cc.start();
            InetAddress ip = InetAddress.getLocalHost();
            cc.sendStringToServer("IP of new connection: " + ip.getHostAddress());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenForInput() {
        Scanner console = new Scanner(System.in);

        while (true) {
            while (!console.hasNextLine()) {
                try {
                    Thread.sleep(1); // 1 ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String input = console.nextLine();
            // quit ontsnappen.
            if (input.toLowerCase().equals("quit")) {
                break;
            }
            System.out.println(input);
            cc.sendStringToServer(input);

        }
        cc.close();
    }

    private static void sendDataToServer() {
        long startTime = System.currentTimeMillis();
        long elapsedTimeInMS = 0;
        boolean dataIsSend = false;

        while (true) {
            elapsedTimeInMS = (new Date()).getTime() - startTime;

            if (elapsedTimeInMS < 1000 && dataIsSend == false) {
                Date now = new Date();
                long milliseconds = now.getTime();
                int min = 1;
                int max = 10;
                int random = ThreadLocalRandom.current().nextInt(min, max + 1);
                String temp = "INSERT " + random + "," + milliseconds + ",24.4,10.2,36.9,44.1,5.5,8.6,70.6,88.8,99.9,0.4,35.3";
                String fakedata = temp;

                for (int i = 0; i < linesOfCode; i++) {
                    fakedata += temp;
                }

                System.out.println("Data ready, lines: " + linesOfCode * inputXTimes);

                for (int i = 0; i < inputXTimes; i++)   // For a minute long receiving data
                {
                    client.cc.sendStringToServer(fakedata);
                }
                System.out.println("Data saved, time took: " + elapsedTimeInMS + "ms");

                dataIsSend = true;
            } else if (elapsedTimeInMS > 1000) {
                System.out.println("elapsedTimeInMS: " + elapsedTimeInMS);
                startTime = System.currentTimeMillis();
                elapsedTimeInMS = 0;
                dataIsSend = false;
            }
        }
    }

    private static void getDataToServer() {
        String getdata = "GET 6,1548420840342,1548420843345";

        client.cc.sendStringToServer(getdata);

        System.out.println("Request for data from server send");
    }
}