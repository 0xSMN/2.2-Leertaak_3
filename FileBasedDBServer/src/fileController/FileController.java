/* Author: Daniël Geerts
 */
package fileController;

import java.io.IOException;
import java.util.*;

public class FileController {

    private FileController() {
    }

    /* Incoming message from client
     * Send this message to the right function
     *
     * @parameter msg Instructions received from client
     *
     * @Author Daniël Geerts
     */
    public static List<String> incomingMessage(String msg) {
        // TODO: check message for the command (write to file/ read from file)
        if (msg.contains("INSERT") && msg.contains("GET")) {
            System.out.println("Not possible!");

            // What to do from here on out?
            // Could not happen, else change request from client
        }

        if (msg.contains("INSERT")) {
            WriteDataToFile(msg);
            return null;
        }

        if (msg.contains("GET")) {
            return ReadDataFromFile(msg);
        }
        return null;
    }

    /* Handle here the INSERT instruction
     * Sow right the INSERT to a file
     *
     * @parameter msg Instructions received from client
     *
     * @Author Daniël Geerts
     */
    private static void WriteDataToFile(String msg) {
        String[] data = msg.split("INSERT ");
        List<Map<String,String>> dict = new ArrayList<Map<String,String>>();

        for (int i = 0; i < data.length; i++) {
            if (data[i].length() > 0) {
                String[] list = data[i].split(",");
                dict.add(new HashMap<String,String>());

                for (int j = 0; j < list.length; j++) {
                    dict.get(dict.size()-1).put(FileConfig.DB_COLUMNS[j], list[j]);
                }
            }
        }

        CreateFile file = new CreateFile();
        file.addDataToFile(dict);
        dict.clear();
    }

    /* Handle here the GET instruction
     * Sow scan from the files the records we need
     *
     * @parameter msg Instructions received from client
     *
     * @Author Daniël Geerts
     */
    private static List<String> ReadDataFromFile(String msg) {
        String[] data = msg.split("GET ");
        List<String> gotReturned = new ArrayList<>();

        for (int i = 0; i < data.length; i++ ) {
            if (data[i].length() > 5) {
                System.out.println("GET request: " + data[i]);

                try {
                    ReadFile reader = new ReadFile();
                    gotReturned.addAll(reader.ReadRecordsFromFile(data[i]));
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }

        return gotReturned;
    }
}
