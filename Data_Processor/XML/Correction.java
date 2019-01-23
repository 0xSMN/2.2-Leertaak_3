package XML;


import java.util.ArrayList;
import java.util.Arrays;

import XML.*;


public class Correction {


    public static float avgTEMP = 0.0f;
    public static float avgDEWP = 0.0f;
    public static float avgSTP = 0.0f;
    public static float avgSLP = 0.0f;
    public static float avgVISIB = 0.0f;
    public static float avgWDSP = 0.0f;
    public static float avgPRCP = 0.0f;
    public static float avgSNDP = 0.0f;
    public static float avgCLDC = 0.0f;

    public static ArrayList<ArrayList<Measurement>> data3 = new ArrayList<>();

    public static ArrayList<Measurement> makeList(ArrayList<Measurement> data2) {


        /*if (data3.size() > 30) {
            data2 = correctTemperature(data2);
        }*/
        getAverage();
        data3.add(data2);

        while (data3.size()>=31) {
                data3.remove(0);
        }
        System.out.println("the avg temp is" + avgTEMP);
        for (int x = 0; x < data2.size(); x++) {
            System.out.println(data2.get(x).TEMP);
        }
        System.out.println("the size is"+  data3.size());
        /*for(int x=0; x<data3.size()-1;x++){
            ArrayList<Measurement> Temp = data3.get(x);
            for (int y = 0; y < Temp.size()-1; y++) {
                System.out.println(Temp.get(y).TEMP);
            }
        }*/
        return data2;
    }


    private static void getAverage() {
        //check if there is atleast 30 in the list to calculate average
        if (data3.size() > 29) {
            //Clear average data.
            avgTEMP = 0.0f;
            avgDEWP = 0.0f;
            avgSTP = 0.0f;
            avgSLP = 0.0f;
            avgVISIB = 0.0f;
            avgWDSP = 0.0f;
            avgPRCP = 0.0f;
            avgSNDP = 0.0f;
            avgCLDC = 0.0f;
            //get for each variable 30pieces of data
            for (int x = 0; x <= 29; x++) {
                ArrayList<Measurement> Temp = data3.get(x);
                for (int y = 0; y < Temp.size()-1; y++) {
                    avgTEMP += Temp.get(y).TEMP;
                    avgDEWP += Temp.get(y).DEWP;
                    avgSTP += Temp.get(y).STP;
                    avgSLP += Temp.get(y).SLP;
                    avgVISIB += Temp.get(y).VISIB;
                    avgWDSP += Temp.get(y).WDSP;
                    avgPRCP += Temp.get(y).PRCP;
                    avgSNDP += Temp.get(y).SNDP;
                    avgCLDC += Temp.get(y).CLDC;
                }
            }//calculate average of each variable
            avgTEMP = avgTEMP / 30f;
            avgDEWP = avgDEWP / 30f;
            avgSTP = avgSTP / 30f;
            avgSLP = avgSLP / 30f;
            avgVISIB = avgVISIB / 30f;
            avgWDSP = avgWDSP / 30f;
            avgPRCP = avgPRCP / 30f;
            avgSNDP = avgSNDP / 30f;
            avgCLDC = avgCLDC / 30f;
        }
    }

    /*private static ArrayList<Measurement> correctTemperature(ArrayList<Measurement> data2) {
        for (int x = 0; x < data2.size()-1; x++) {
            // look for temp and then compare it to average +/- 20%
            float percentageTEMP = data2.get(x).TEMP / 100f * 20f;
            if (data2.get(x).TEMP > (data2.get(x).TEMP + percentageTEMP) || data2.get(x).TEMP < (data2.get(x).TEMP - percentageTEMP)) {
                data2.get(x).TEMP = avgTEMP;
            }
        }
        return data2;
    }*/
}
