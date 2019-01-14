package fileController;

import java.util.Date;

public class FileConfig {

    // All keys receiving from the lightweight server
    public static String[] keys = {"STN", "DATETIME", "TEMP", "DEWP", "STP", "SLP", "VISIB", "WDSP", "PRCP", "SNDP", "FRSHTT", "CLDC", "WNDDIR"};

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