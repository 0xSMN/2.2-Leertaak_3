package fileController;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReadFile {

    public ReadFile() {
    }

    public String ReadFromFile(String instructions) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        List<String> tosend = new ArrayList<>();

        try {
            List<File> list = new ArrayList<File>();
            ReadDirectoryAddFiles(FileConfig.FOLDER_NAME, list);
            for (int i = 0; i < list.size(); i++) {
                inputStream = new FileInputStream(list.get(i));
                sc = new Scanner(inputStream, "UTF-8");
                while (sc.hasNextLine())
                {
                    String line = sc.nextLine();
                    if (!line.contains(FileConfig.DB_COLUMNS[1]))
                    {
                        String[] filter = instructions.split(",");
                        if (filter[0].equals(FileConfig.DB_COLUMNS[1]))
                        {
                            Long min_date = stringToLong(filter[1].trim());
                            Long max_date = stringToLong(filter[2].trim());
                            if (min_date != -1 && max_date != -1)
                            {
                                Long db_date = stringToLong(line.split(",")[1].trim()); // [1] == DATETIME
                                if (db_date != -1)
                                {
                                    if (min_date.compareTo(db_date) * db_date.compareTo(max_date) > 0)
                                    {
                                        tosend.add(line);
                                    }
                                }
                            }
                        }
                    }
                }

                // note that Scanner suppresses exceptions
                if (sc.ioException() != null) {
                    throw sc.ioException();
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (sc != null) {
                sc.close();
            }
        }

        for (int i = 0; i < tosend.size(); i ++) {
            System.out.println("TOSEND: " + tosend.get(i));
        }

        return null;
        //return tosend;
    }

    private void ReadDirectoryAddFiles(String directoryName, List<File> files) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();
        if (fList != null)
            for (File file : fList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    ReadDirectoryAddFiles(file.getAbsolutePath(), files);
                }
            }
    }

    private Long stringToLong(String data) {
        try {
            return Long.parseLong(data);
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR-NumberFormatException: " + nfe.getMessage());
        }

        return new Long(-1);
    }
}
