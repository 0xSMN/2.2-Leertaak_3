package fileController;

import java.io.*;
import java.util.List;
import java.util.Map;

public class CreateFile {

    public CreateFile() {
        String filepath = FileConfig.filepath;
        String filename = FileConfig.filename;
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            new File(filepath).mkdirs();
            File file = new File(filepath + "\\" + filename);
            System.out.println(file.toString());

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), false);
            bw = new BufferedWriter(fw);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < FileConfig.keys.length; i++) {
                sb.append(FileConfig.keys[i]);
                if (i < FileConfig.keys.length-1) {
                    sb.append(',');
                }
            }
            sb.append('\n');
            System.out.println(sb.toString());
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
        String filepath = FileConfig.filepath;
        String filename = FileConfig.filename;
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            new File(filepath).mkdirs();
            File file = new File(filepath + "\\" + filename);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < FileConfig.keys.length; j++) {
                    if (data.get(i).containsKey(FileConfig.keys[j])) {
                        sb.append(data.get(i).get(FileConfig.keys[j]));
                        if (j < FileConfig.keys.length - 1) {
                            sb.append(',');
                        } else {
                            sb.append('\n');
                        }
                    }
                }
            }
            System.out.println(sb.toString());
            bw.write(sb.toString());
            System.out.println("Done - Added some data to file");
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
