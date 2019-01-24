package XML;

//import java.util.ArrayList;
        import java.util.Date;

public class Measurement {
    private long ID = 1234;
    public int STN;
    public Date DATETIME;
    public float TEMP;
    public float DEWP;
    public float STP;
    public float SLP;
    public float VISIB;
    public float WDSP;
    public float PRCP;
    public float SNDP;
    public byte FRSHTT;
    public float CLDC;
    public short WNDDIR;

    public String GenSendString() { //
        String s = "INSERT ";
        s = s.concat(ID + ", ");
        s = s.concat(STN + ", ");
        s = s.concat(DATETIME.getTime() + ", ");
        s = s.concat(TEMP + ", ");
        s = s.concat(DEWP + ", ");
        s = s.concat(STP + ", ");
        s = s.concat(SLP + ", ");
        s = s.concat(VISIB + ", ");
        s = s.concat(WDSP + ", ");
        s = s.concat(PRCP + ", ");
        s = s.concat(SNDP + ", ");
        s = s.concat(Integer.toHexString(FRSHTT) + ", "); //TODO: niet dit
        s = s.concat(CLDC + ", ");
        s = s.concat(Short.toString(WNDDIR));
        return s;
    }
}

