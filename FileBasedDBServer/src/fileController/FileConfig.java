package fileController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileConfig {

    // All keys receiving from the lightweight server
    public static String[] DB_COLUMNS = {"ID", "STN", "DATETIME", "TEMP", "DEWP", "STP", "SLP", "VISIB", "WDSP", "PRCP", "SNDP", "FRSHTT", "CLDC", "WNDDIR"};

    private static DateFormat yearFormat = new SimpleDateFormat("yyyy");
    private static DateFormat monthFormat = new SimpleDateFormat("MM");
    private static DateFormat dayFormat = new SimpleDateFormat("dd");
    private static DateFormat hourFormat = new SimpleDateFormat("HH");

    private static Date date = new Date();

    public static String FOLDER_NAME = "_database_";
    public static String DYNAMIC_FILE_PATCH = FOLDER_NAME + "\\" +
            yearFormat.format(date) + "\\" +
            monthFormat.format(date) + "\\" +
            dayFormat.format(date);
    public static String DYNAMIC_FILE_NAME = yearFormat.format(date) + "-" +
            monthFormat.format(date) + "-" +
            dayFormat.format(date) + "_h" +
            hourFormat.format(date) + ".csv";

    /*
    public int STN;         // Station ID
    public String DATETIME; // Datum en tijd van versturen van deze gegevens
    public float TEMP;      // Temparatuur
    public float DEWP;      // Dauwpunt
    public float STP;       // Luchtdruk op stationsniveau
    public float SLP;       // Luchtdruk op zeeniveau
    public float VISIB;     // Zichtbaarheid in kilometers
    public float WDSP;      // Windsnelheid in kilometers per uur
    public float PRCP;      // Neerslag in centimeters
    public float SNDP;      // Gevallen sneeuw in centimeters
    public byte FRSHTT;
    public float CLDC;      // Bewolking in procenten
    public short WNDDIR;    // Windrichting in graden
    */
}

/*
public class StationTemplate {
    int station;
    String name;
    int latitude;
    int longitude;
    String country;
    int timezone;
}

public class TimezoneTemplate {
    int timezone_id;
    DateTime gmt_offset;
    int dst;
}
*/