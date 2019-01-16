package fileController;

import java.io.*;
import java.util.List;
import java.util.Map;

public class CreateFile {

    public CreateFile() {
        String filepath = FileConfig.DYNAMIC_FILE_PATCH;
        String filename = FileConfig.DYNAMIC_FILE_NAME;
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            new File(filepath).mkdirs();
            File file = new File(filepath + "\\" + filename);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            } else {
                return;
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), false);
            bw = new BufferedWriter(fw);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < FileConfig.DB_COLUMNS.length; i++) {
                sb.append(FileConfig.DB_COLUMNS[i]);
                if (i < FileConfig.DB_COLUMNS.length-1) {
                    sb.append(',');
                }
            }
            sb.append(System.getProperty("line.separator"));
            bw.write(sb.toString());
            System.out.println("Done - File init");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addDataToFile(List<Map<String,String>> data) {
        String filepath = FileConfig.DYNAMIC_FILE_PATCH;
        String filename = FileConfig.DYNAMIC_FILE_NAME;
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            new File(filepath).mkdirs();
            File file = new File(filepath + "\\" + filename);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                System.out.println("Something went wrong");
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            StringBuilder sb = new StringBuilder();

            // Loop trough list of data and set with the right key on right position
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < FileConfig.DB_COLUMNS.length; j++) {
                    if (data.get(i).containsKey(FileConfig.DB_COLUMNS[j])) {
                        sb.append(data.get(i).get(FileConfig.DB_COLUMNS[j]));
                        if (j < FileConfig.DB_COLUMNS.length - 1) {
                            sb.append(',');
                        }
                    }
                }
                sb.append(System.getProperty("line.separator"));
            }
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
