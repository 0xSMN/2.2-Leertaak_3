package XML;

import Correction.Correction;
import Correction.Corrector;

import java.awt.geom.FlatteningPathIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class XML_Parser {

    public static ArrayList<Measurement> ReadXML(String data) {

        if (!data.startsWith("WEATHERDATA")) {
            return null;
        }

        String line;
        ArrayList<Measurement> measurements = new ArrayList<Measurement>();
        ArrayList<String> XMLstack = new ArrayList<String>();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

        data = data.replace(">", ">\n");
        data = data.replace("<", "\n<");
        data = data.replace("\t", "");
        while (data.contains("\n\n")) {
            data = data.replace("\n\n", "\n");
        }

        BufferedReader reader = new BufferedReader(new StringReader(data));


        try {
            while ((line = reader.readLine()) != null) {
                line = line.replace("\n", "");
                if (line.isEmpty() || line.contains("?")) { // filter out blank lines and information tags
                    continue;
                }

//                System.out.println(line);

                if (line.startsWith("<")) { // if true the given line is a tag
                    line = line.replace("<", "");
                    line = line.replace(">", "");
                    if (line.contains("/")) { //it is a closing tag
                        if (XMLstack.get(XMLstack.size() - 1).equals(line.replace("/", ""))) {
                            XMLstack.remove(XMLstack.size() -1);
                        }
                        else {
                            System.err.println("Invalid XML");
                        }
                    }
                    else { //it is an opening tag
                        XMLstack.add(line);
//                        System.out.println("added to XML_Stack: " + line);
                        if (line.equals("MEASUREMENT")) {
                            measurements.add(new Measurement());
                        }
                    }
                }
                else { // the line is not a tag
                    switch (XMLstack.get(XMLstack.size() -1)) {
                        case "STN": //Het station waarvan deze gegevens zijn
                            measurements.get(measurements.size() -1).STN = Integer.parseInt(line);
                            break;
                        case "DATE": //Datum van versturen van deze gegevens, formaat: yyyy-mm-dd
                            try {
                                measurements.get(measurements.size() - 1).DATETIME = ft.parse(line);
                            }
                            catch (ParseException e) {System.err.println("Unable to set DATETIME");}
                            break;
                        case "TIME": //Tijd van versturen van deze gegevens, formaat: hh:mm:ss
                            String s[] = line.split(":");
                            int time = Integer.parseInt(s[0]) * 3600000;
                            time += Integer.parseInt(s[1]) * 60000;
                            time += Integer.parseInt(s[2]) * 1000;
                            measurements.get(measurements.size() - 1).DATETIME.setTime(measurements.get(measurements.size() - 1).DATETIME.getTime() + time);
                            break;
                        case "TEMP": //Temperatuur in graden Celsius, geldige waardes van -9999.9 t/m 9999.9 met 1 decimaal
                            if(line.trim().equals("")){
                                measurements.get(measurements.size() - 1).TEMP = Float.MIN_NORMAL;
                            }
                            else {
                                measurements.get(measurements.size() - 1).TEMP = Float.parseFloat(line);
                            }
                            break;
                        case "DEWP": // Dauwpunt in graden Celsius, geldige waardes van -9999.9 t/m 9999.9 met 1 decimaal
                            if(line.trim().equals("")){
                                measurements.get(measurements.size() - 1).DEWP = Float.MIN_NORMAL;
                            }
                            else {
                                measurements.get(measurements.size() - 1).DEWP = Float.parseFloat(line);
                            }
                            break;
                        case "STP": //Luchtdruk op stationsniveau in millibar, geldige waardes van 0.0 t/m 9999.9 met 1 decimaal
                            if(line.trim().equals("")){
                                measurements.get(measurements.size() - 1).STP = Float.MIN_NORMAL;
                            }
                            else {
                                measurements.get(measurements.size() - 1).STP = Float.parseFloat(line);
                            }
                            break;
                        case "SLP": //Luchtdruk op zeeniveau in millibar, geldige waardes van 0.0 t/m 9999.9 met 1 decimaal
                            if(line.trim().equals("")){
                                measurements.get(measurements.size() - 1).SLP = Float.MIN_NORMAL;
                            }
                            else {
                            measurements.get(measurements.size() - 1).SLP = Float.parseFloat(line);
                            }
                            break;
                        case "VISIB": //Zichtbaarheid in kilometers, geldige waardes van 0.0 t/m 999.9 met 1 decimaal
                            if(line.trim().equals("")){
                                measurements.get(measurements.size() - 1).VISIB = Float.MIN_NORMAL;
                            }
                            else {
                                measurements.get(measurements.size() - 1).VISIB = Float.parseFloat(line);
                            }
                            break;
                        case "WDSP": //Windsnelheid in kilometers per uur, geldige waardes van 0.0 t/m 999.9 met 1 decimaal
                            if(line.trim().equals("")){
                                measurements.get(measurements.size() - 1).TEMP = Float.MIN_NORMAL;
                            }
                            else {
                                measurements.get(measurements.size() - 1).WDSP = Float.parseFloat(line);
                            }
                            break;
                        case "PRCP": //Neerslag in centimeters, geldige waardes van 0.00 t/m 999.99 met 2 decimalen
                            if(line.trim().equals("")){
                                measurements.get(measurements.size() - 1).PRCP = Float.MIN_NORMAL;
                            }
                            else {
                                measurements.get(measurements.size() - 1).PRCP = Float.parseFloat(line);
                            }
                            break;
                        case "SNDP": //Gevallen sneeuw in centimeters, geldige waardes van -9999.9 t/m 9999.9 met 1 decimaal
                            if(line.trim().equals("")){
                                measurements.get(measurements.size() - 1).SNDP = Float.MIN_NORMAL;
                            }
                            else {
                                measurements.get(measurements.size() - 1).SNDP = Float.parseFloat(line);
                            }
                            break;
//                        Gebeurtenissen op deze dag, cummulatief, binair uitgedrukt.
//                        Opeenvolgend, van meest- naar minst significant:
//                        Vriezen, geeft aan of het gevroren heeft
//                        Regen, geeft aan of het geregend heeft.
//                        Sneeuw, geeft aan of het gesneeuwd heeft.
//                        Hagel, geeft aan of het gehageld heeft.
//                        Onweer, geeft aan of er onweer is geweest.
//                        Tornado/windhoos, geeft aan of er een tornado of windhoos geweest is.
                        case "FRSHTT":
                            measurements.get(measurements.size() - 1).FRSHTT = Byte.parseByte(line, 2);
                            break;
                        case "CLDC": //Bewolking in procenten, geldige waardes van 0.0 t/m 99.9 met 1 decimaal
                            measurements.get(measurements.size() - 1).CLDC = Float.parseFloat(line);
                            break;
                        case "WNDDIR": //Windrichting in graden, geldige waardes van 0 t/m 359 alleen gehele getallen
                            measurements.get(measurements.size() - 1).WNDDIR = Short.parseShort(line);
                            break;
                    }
                }
            }
        }

        catch (IOException ioe) { }
//        measurements = Corrector.makeList(measurements);

        // Sends all measurments trough the corrector
        for (Measurement m: measurements) {
            m = Correction.testAndAddMeasurement(m);
        }

        return measurements;
    }
}
