package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 1234;

    public static void work() {
        Socket connection;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started");

            while (true) {
                connection = server.accept();
                System.out.println("connection established");

                BufferedReader bin = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String data = "";
                String s;

                while (true) {
                    if (!bin.ready() && !data.equals("")) { // the client has finished its request header
                        System.out.println(data);
                        break;
                    }

                    if ((s = bin.readLine()) != null) { // read the clients request
                        data = data.concat(s);
                        data = data.concat("\n");
                    } else {
                        break;
                    }
                }

                String fileName = getRequestFileName(data);

                // a PrintWriter object to write the reply to the client to
                PrintWriter dataOut = new PrintWriter(connection.getOutputStream());


                if (fileName.equals("/")) { // no specific filename given, using index.html
                    fileName = "/index.html";
                }

                if (new File("HTML" + fileName).exists()) {
                    // write the http-html header to the printWriter
                    addHtmlHeader(dataOut);
                    // add the html to the printWriter
                    addHtmlByFilename(fileName, dataOut);
                }
                else {
                    add404Page(dataOut);
                }

                // send everything and close the connection
                dataOut.flush();
                connection.close();
            }
        }
        catch (IOException ioe) {System.err.println("IOException");
        ioe.printStackTrace();
        }
    }

    private static void addHtmlHeader(PrintWriter pw) {
        // write the http-html header to the printWriter
        pw.println("HTTP/1.1 200 OK");
        pw.println("Content-type:text/html");
        pw.println("Connection: close");
        pw.println(); // a blank line to indicate the end of the header
    }

    private static String getRequestFileName(String header) {
        int index = header.indexOf("GET"); // locate the keyword "GET"
        index += 4;
        String fileName = "";
        while (header.charAt(index) != ' ') {
            fileName = fileName += (header.charAt(index));
            index++;
        }
        return fileName;
    }

    private static boolean addHtmlByFilename(String fileName, PrintWriter pw) {
        try {
            // open the HTML file
            BufferedReader fReader = new BufferedReader(new FileReader("HTML" + fileName));
            String outLine;

            // write the HTML to the printWriter
            while ((outLine = fReader.readLine()) != null) {
                if (outLine.equals("")) {
                    continue;
                }
//                    System.err.println(outLine.trim());
                pw.println(outLine.trim());
            }
        }
        catch (IOException ex) {
            return false; // no file was found with the given name, or the file file could not be read
        }

        return true; // file was read successfully
    }

    private static void add404Page(PrintWriter pw) {
        // write the http-html header to the printWriter
        pw.println("HTTP/1.1 404 Not Found");
        pw.println("Content-type:text/html");
        pw.println("Connection: close");
        pw.println(); // a blank line to indicate the end of the header
        pw.print("<h1>404 - Not Found</h1>");
        pw.print("<p>Congratulations, you broke it</p>");
    }
}