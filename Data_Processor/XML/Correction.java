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

    public static ArrayList<ArrayList<Measurement>> makeList(ArrayList<Measurement> data2) {
        for (int x = 1; x < data2.size(); x++) {
            System.out.println(data2.get(x).TEMP);
        }
        getAverage();
        //data2 = correctMissingData(data2);
        if (data3.size() > 30) {
            data2 = correctTemperature(data2);
        }
        data3.add(data2);
        /*
        if (data3.size()>60) {
            for(int x = 0; x < 30; x++){
                data3.remove(x);
            }
        }
        for(int x=0; x<data3.size();x++){
            ArrayList<Measurement> Temp = data3.get(x);
            for (int y = 0; y < Temp.size(); y++) {
                System.out.println(Temp.get(y).TEMP);
            }
        }*/
        return data3;
    }


    private static void getAverage() {
        //check if there is atleast 30 in the list to calculate average
        if (data3.size() > 30) {
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
            for (int x = 0; x < 30; x++) {
                ArrayList<Measurement> Temp = data3.get(x);
                for (int y = 0; y < Temp.size(); y++) {
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
            avgTEMP = avgTEMP / 30;
            avgDEWP = avgDEWP / 30;
            avgSTP = avgSTP / 30;
            avgSLP = avgSLP / 30;
            avgVISIB = avgVISIB / 30;
            avgWDSP = avgWDSP / 30;
            avgPRCP = avgPRCP / 30;
            avgSNDP = avgSNDP / 30;
            avgCLDC = avgCLDC / 30;
        }
    }

    private static ArrayList<Measurement> correctTemperature(ArrayList<Measurement> data2) {
        for (int x = 0; x < data2.size(); x++) {
            // look for temp and then compare it to average +/- 20%
            float percentageTEMP = data2.get(x).TEMP / 100 * 20;
            if (data2.get(x).TEMP > (data2.get(x).TEMP + percentageTEMP) || data2.get(x).TEMP < (data2.get(x).TEMP - percentageTEMP)) {
                data2.get(x).TEMP = avgTEMP;
            }
        }
        return data2;
    }
}
    /*private static ArrayList<Measurement> correctMissingData(ArrayList<Measurement> data2) {
        //check if any value == 0
        for (int x = 0; x < data2.size(); x++){
            if(data2.get(x).TEMP == 0.0f){
                data2.get(x).TEMP = avgTEMP;
            }
            if(data2.get(x).DEWP == 0.0f){
                data2.get(x).DEWP = avgDEWP;
            }
            if(data2.get(x).STP == 0.0f){
                data2.get(x).STP = avgSTP;
            }
            if(data2.get(x).SLP == 0.0f){
                data2.get(x).SLP = avgSLP;
            }
            if (data2.get(x).VISIB == 0.0f){
                data2.get(x).VISIB = avgVISIB;
            }
            if (data2.get(x).WDSP == 0.0f){
                data2.get(x).WDSP = avgWDSP;
            }
            if (data2.get(x).PRCP == 0.0f){
                data2.get(x).PRCP = avgPRCP;
            }
            if (data2.get(x).SNDP == 0.0f){
                data2.get(x).SNDP = avgSNDP;
            }
            if (data2.get(x).CLDC == 0.0f){
                data2.get(x).CLDC = avgCLDC;
            }
        }
        return data2;
    }
}*/

                      /*  if(line.trim().equals("") && Correction.avgTEMP != 0.0f){
                                    measurements.get(measurements.size() - 1).TEMP = Correction.avgTEMP;
                                    }
                                    else {
                                    measurements.get(measurements.size() - 1).TEMP = Float.parseFloat(line);
                                    }*/

                     // Correction.makeList(measurements);