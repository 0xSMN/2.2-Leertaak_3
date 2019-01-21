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
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    // System.out.println(line);
                    String[] data = line.split(",");
                    String[] filter = instructions.split(",");

                    if (filter[0].equals(FileConfig.DB_COLUMNS[1])) {
                        // if not null then it is a date
                        Date date1 = isThisDateValid(filter[1]);
                        Date date2 = isThisDateValid(filter[2]);
                        if (date1 != null && date2 != null) {
                            //System.out.println(date1);
                            //System.out.println(date2);
                            Date date3 = isThisDateValid(data[1]);

                            //System.out.println("DATA: " + date3);

                            if (date3 != null) {
                                if (date1.compareTo(date3) * date3.compareTo(date2) > 0) {
                                    tosend.add(line);
                                    //System.out.println("YESSS!!!!! For the WIN bitches!");
                                }
                            }
                        }
                    }

                    // Split line op (,)
                    // Zet in dictionary? Is dit duur? Anders op gewoon volgorde
                    // Check op bepaalde positie van KEYS de value met bepaalde waarde van API
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

    private Date isThisDateValid(String dateToValidate){
        dateToValidate = dateToValidate.trim();
        if(dateToValidate == null || dateToValidate.equals("DATETIME")){
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(FileConfig.DATE_FORMAT);
        sdf.setLenient(false);

        try {
            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            //System.out.println("Date : " + date);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Jupp hier ging iets fout met parsen");
            return null;
        }
    }
}
