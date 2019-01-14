package fileController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileController {

    private static String folderName = "DBname";
    private static DateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static DateFormat monthFormat = new SimpleDateFormat("MM");
    private static DateFormat dayFormat = new SimpleDateFormat("dd");
    private static DateFormat hourFormat = new SimpleDateFormat("HH");

    private FileController() {
        // TODO: check welke files bestaan en save de name in een Array
    }

    public static void incomingMessage(String msg) {
        // TODO: check message for the command (write to file/ read from file)
        System.out.println("Ingekomen bericht: " + msg);

        if (msg.contains("INSERT")) {
            WriteDataToFile(msg);
        }

        if (msg.contains("GET")) {
            ReadDataFromFile(msg);
        }
    }

    private static void WriteDataToFile(String msg) {
        String[] data = msg.split("INSERT ");
        List<Map<String,String>> dict = new ArrayList<Map<String,String>>();

        for (int i = 0; i < data.length; i++) {
            if (data[i].length() > 0) {
                String[] list = data[i].split(",");
                dict.add(new HashMap<String,String>());
                Map<String,String> currentDictionary = dict.get(dict.size()-1);

                for (int j = 0; j < list.length; j++) {
                    currentDictionary.put(FileConfig.keys[j], list[j]);
                }
            }
        }
        Date date = new Date();

        String filepath = folderName + "\\" +
                          yearFormat.format(date) + "\\" +
                          monthFormat.format(date) + "\\" +
                          dayFormat.format(date);
        String filename = yearFormat.format(date) + "-" +
                          monthFormat.format(date) + "-" +
                          dayFormat.format(date) + "_h" +
                          hourFormat.format(date) + ".csv";

        CreateFile file = new CreateFile(filepath, filename);
        file.addDataToFile(filepath, filename, dict);
        dict.clear();
    }

    private static void ReadDataFromFile(String msg) {
        // TODO: Read from files and send to client
    }
}
