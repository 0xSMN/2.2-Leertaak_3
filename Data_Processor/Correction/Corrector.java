package Correction;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import XML.*;


public class Corrector {

    // defining instance variables
    private ArrayList<Float> listTEMP = new ArrayList<Float>();
    private ArrayList<Float> listDEWP = new ArrayList<Float>();
    private ArrayList<Float> listSTP = new ArrayList<Float>();
    private ArrayList<Float> listSLP = new ArrayList<Float>();
    private ArrayList<Float> listVISIB = new ArrayList<Float>();
    private ArrayList<Float> listWDSP = new ArrayList<Float>();
    private ArrayList<Float> listPRCP = new ArrayList<Float>();
    private ArrayList<Float> listSNDP = new ArrayList<Float>();
    private ArrayList<Float> listCLDC = new ArrayList<Float>();



    public Measurement testAndAddMeasurement(Measurement m) {
        // TODO: actually test the measurement

        m.TEMP = testAndAddValue(m.TEMP, listTEMP);
        m.DEWP = testAndAddValue(m.DEWP, listDEWP);
        m.STP = testAndAddValue(m.STP, listSTP);
        m.SLP = testAndAddValue(m.SLP, listSLP);
        m.VISIB = testAndAddValue(m.VISIB, listVISIB);
        m.WDSP = testAndAddValue(m.WDSP, listWDSP);
        m.PRCP = testAndAddValue(m.PRCP, listPRCP);
        m.SNDP = testAndAddValue(m.SNDP, listSNDP);
        m.CLDC = testAndAddValue(m.CLDC, listCLDC);

        return m;
    }

    private float testAndAddValue(float value, ArrayList<Float> deeznuts) {
        if (deeznuts.size() >= 30) {
            float averageValue = 0;
            for (float f : deeznuts) {
                averageValue += f;
            }
            averageValue = averageValue / deeznuts.size();
            float difference = Math.abs(value - averageValue);
            float perDifference = 0; // the difference in percents
            perDifference = ((difference / Math.abs(averageValue)) * 100);

            if (perDifference < 20) {
                deeznuts.remove(0);
                deeznuts.add(value);
            }
            else {
                value = averageValue;
            }
        }
        else {
            deeznuts.add(value);
        }

        return value;
    }
}
