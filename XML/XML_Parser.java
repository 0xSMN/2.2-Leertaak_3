package XML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class XML_Parser {

    public static Measurement[] ReadXML(String data) {
        String line;
        ArrayList<Measurement> measurements = new ArrayList();
        ArrayList<String> XMLstack = new ArrayList();

        data = data.replace(">", ">\n");
        data = data.replace("<", "\n<");
        data = data.replace("\t", "");
        while (data.contains("\n\n")) {
            data = data.replace("\n\n", "\n");
        }

        BufferedReader reader = new BufferedReader(new StringReader(data));

        try {
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("<")) { // if true the given line is a tag
                    line = line.replace("<", "");
                    line = line.replace(">", "");
                    if (line.contains("/")) {
                        if (XMLstack.get(XMLstack.size() - 1).equals(line.replace("/", ""))) {
                            XMLstack.remove(XMLstack.size() -1);
                        }
                        else {
                            System.err.println("Invalid XML");
                        }
                    }
                    else {
                        XMLstack.add(line);
                        if (line.equals("MEASUREMENT")) {
                            measurements.add(new Measurement());
                        }
                    }
                }
                else { // the line is not a tag
                    switch (XMLstack.get(XMLstack.size() -1)) {
                        case "STN":
                            measurements.get(measurements.size() -1).STN = Integer.parseInt(line);
                            break;
                        case "DATE":
                            break;
                        case "TIME":
                            break;
                    }
                }
            }
        }
        catch (IOException ioe) { }

        Measurement[] array = {new Measurement()};
        return array;
    }
}
