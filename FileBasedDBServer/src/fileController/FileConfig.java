/* Author: Daniël Geerts
 */
package fileController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileConfig {

    // All keys receiving from the lightweight server
    public static String[] DB_COLUMNS = {"STN", "DATETIME", "TEMP", "DEWP", "STP", "SLP", "VISIB", "WDSP", "PRCP", "SNDP", "FRSHTT", "CLDC", "WNDDIR"};

    private DateFormat yearFormat = new SimpleDateFormat("yyyy");
    private DateFormat monthFormat = new SimpleDateFormat("MM");
    private DateFormat dayFormat = new SimpleDateFormat("dd");
    private DateFormat hourFormat = new SimpleDateFormat("HH");
    private Date date = new Date();

    // Default database folder
    public static String FOLDER_NAME = "_database_";

    // Dynamic path to the database
    public String DYNAMIC_FILE_PATH = FOLDER_NAME + "/" +
            yearFormat.format(date) + "/" +
            monthFormat.format(date) + "/" +
            dayFormat.format(date);

    // Dynamic filename of the records
    public String DYNAMIC_FILE_NAME = yearFormat.format(new Date()) + "-" +
            monthFormat.format(date) + "-" +
            dayFormat.format(date) + "_h" +
            hourFormat.format(date) + ".csv";
}
