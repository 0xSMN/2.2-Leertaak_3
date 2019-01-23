package fileController;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReadFile {

    public ReadFile() {
    }

    public List<String> ReadRecordsFromFile(String instructions) throws IOException {
        FileInputStream inputStream = null;
        Scanner sc = null;
        List<String> tosend = new ArrayList<>();

        String[] filter = instructions.split(",");
        Long min_date = convertDateToLong(filter[1].trim());
        Long max_date = convertDateToLong(filter[2].trim());

        try {
            List<File> list = new ArrayList<File>();
            List<String> dates = getAllDatesBetween(new Date(min_date), new Date(max_date));
            ReadDirectoryAddFiles(FileConfig.FOLDER_NAME, list, dates);
            for (File f : list) {
                System.out.println("File added: " + f);
            }
            for (int i = 0; i < list.size(); i++) {
                inputStream = new FileInputStream(list.get(i));
                sc = new Scanner(inputStream, "UTF-8");

                while (sc.hasNextLine())
                {
                    String line = sc.nextLine();
                    if (!line.contains(FileConfig.DB_COLUMNS[1]))
                    {
                        if (filter[0].equals(FileConfig.DB_COLUMNS[1]))
                        {
                            if (min_date != -1 && max_date != -1)
                            {
                                Long db_date = convertDateToLong(line.split(",")[1].trim()); // [1] == DATETIME
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
                // After reading one file is done, close file Stream
                if (inputStream != null) {
                    inputStream.close();
                }
                if (sc != null) {
                    sc.close();
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
        return tosend;
    }

    private void ReadDirectoryAddFiles(String directoryName, List<File> files, List<String> dates) {
        File directory = new File(directoryName);

        // Get all files from a directory.
        File[] fList = directory.listFiles();

        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    if (dates.contains(file.getName().replace(".csv", ""))) {
                        System.out.println(file);
                        if (!files.contains(file)) {
                            files.add(file);
                        }
                    }
                } else if (file.isDirectory()) {
                    ReadDirectoryAddFiles(file.getAbsolutePath(), files, dates);
                }
            }
        }
    }

    private Long convertDateToLong(String data) {
        try {
            return Long.parseLong(data);
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR-NumberFormatException: " + nfe.getMessage());
        }

        return -1L;
    }

    private List<String> getAllDatesBetween(Date min, Date max){
        List<String> dates = new ArrayList<>();

        long diff = min.getTime() - max.getTime();
        diff = diff / (1000 * 60 * 60);
        if (diff < 0) {
            diff = -diff;
        }
/*        System.out.println("hourdiff: " + diff);

        String newstring = new SimpleDateFormat("yyyy-MM-dd HH").format(min);
        System.out.println(newstring);
        newstring = new SimpleDateFormat("yyyy-MM-dd HH").format(max);
        System.out.println(newstring);
*/
        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(min));
        int month = Integer.parseInt(new SimpleDateFormat("MM").format(min));
        int day = Integer.parseInt(new SimpleDateFormat("dd").format(min));
        int hour = Integer.parseInt(new SimpleDateFormat("HH").format(min));

        for (int i = 0; i <= diff; i++) {
            int newYear = year;
            int newMonth = month;
            int newDay = day;
            int newHour = hour + i;
            if (newHour >= 24) {
                newDay += newHour / 24;
                newHour = newHour % 24;
            }
            if (newDay >= 31) {
                newMonth += newDay / 31;
                newDay = (newDay % 31) + 1;
            }
            if (newMonth >= 13) {
                newYear += newMonth / 13;
                newMonth = (newMonth % 13) + 1;
            }
            //System.out.println(i + ", Date between: " + newYear + "-" + newMonth + "-" + newDay + "_H" + newHour);

            String datebetween = year + "-";
            if (newMonth < 10) {
                datebetween += "0";
            }
            datebetween += newMonth + "-";
            if (newDay < 10) {
                datebetween += "0";
            }
            datebetween += newDay + "_h";
            if (newHour < 10) {
                datebetween += "0";
            }
            datebetween += newHour;

            dates.add(datebetween);
        }
        return dates;
    }
}
