/* Author: Daniël Geerts
 */
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

    // Default database folder
    public static String FOLDER_NAME = "_database_";

    // Dynamic path to the database
    public static String DYNAMIC_FILE_PATH = FOLDER_NAME + "\\" +
            yearFormat.format(date) + "\\" +
            monthFormat.format(date) + "\\" +
            dayFormat.format(date);

    // Dynamic filename of the records
    public static String DYNAMIC_FILE_NAME = yearFormat.format(date) + "-" +
            monthFormat.format(date) + "-" +
            dayFormat.format(date) + "_h" +
            hourFormat.format(date) + ".csv";
}
