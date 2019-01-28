/* Author: Daniël Geerts
 */
package fileController;

import java.io.*;
import java.util.List;
import java.util.Map;

public class CreateFile {

    FileConfig fc = new FileConfig();

    /* Check if file already exists else return
     * If not exists create a new one with the right name
     * Initialize it with the right columns (see FileConfig for the columns)
     *
     * @Author Daniël Geerts
     */
    public CreateFile(int stn_ID) {
        InitFile(stn_ID);
    }

    private void InitFile(int stn_ID) {
        String filepath = fc.DYNAMIC_FILE_PATH + "\\" + stn_ID;
        String filename = fc.DYNAMIC_FILE_NAME;
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            new File(filepath).mkdirs();
            File file = new File(filepath + "\\" + filename);

            // if file does not exists, then create one
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

    /* This function will add data to a file (so this does not overwrite it!)
     *
     * @parameter data Is a List of a Map that has as keys the DB columns and as value the values received from client
     *
     * @Author Daniël Geerts
     */
    public void addDataToFile(Map<String,String> data, int stn_ID) {
        String filepath = fc.DYNAMIC_FILE_PATH + "\\" + stn_ID;
        String filename = fc.DYNAMIC_FILE_NAME;
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
            for (int j = 0; j < FileConfig.DB_COLUMNS.length; j++) {
                if (data.containsKey(FileConfig.DB_COLUMNS[j])) {
                    sb.append(data.get(FileConfig.DB_COLUMNS[j]));
                    if (j < FileConfig.DB_COLUMNS.length - 1) {
                        sb.append(',');
                    }
                }
            }
            sb.append(System.getProperty("line.separator"));
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
