/* Author: Daniël Geerts
 */
package fileController;

import java.io.IOException;
import java.lang.reflect.Array;
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
    private static synchronized void WriteDataToFile(String msg) {
        List<String> data = Arrays.asList(msg.split("INSERT "));
        Map<Integer, Map<String,String>> dict = new HashMap<Integer, Map<String,String>>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).length() > 3) {
                List<String> list = Arrays.asList(data.get(i).split(","));
                int stn_id = Integer.parseInt(list.get(0));
                if (!dict.containsKey(stn_id)) {
                    dict.put(stn_id, new HashMap<String, String>());
                }

                for (int j = 0; j < list.size(); j++) {
                    dict.get(stn_id).put(FileConfig.DB_COLUMNS[j], list.get(j));
                }
            }
        }

        for (Integer key : dict.keySet()) {
            CreateFile file = new CreateFile(key);
            file.addDataToFile(dict.get(key), key);
        }
        dict.clear();
    }

    /* Handle here the GET instruction
     * Sow scan from the files the records we need
     *
     * @parameter msg Instructions received from client
     *
     * @Author Daniël Geerts
     */
    private static synchronized List<String> ReadDataFromFile(String msg) {
        List<String> data = Arrays.asList(msg.split("GET "));
        List<String> gotReturned = new ArrayList<>();

        for (int i = 0; i < data.size(); i++ ) {
            if (data.get(i).length() > 3) {
                try {
                    ReadFile reader = new ReadFile();
                    gotReturned.addAll(reader.ReadRecordsFromFile(data.get(i)));
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return gotReturned;
    }
}
