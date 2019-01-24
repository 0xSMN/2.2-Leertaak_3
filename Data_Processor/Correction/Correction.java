package Correction;

import XML.Measurement;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class Correction {
    static private Map<Integer, Corrector> correctors = new HashMap<Integer, Corrector>();

    public static synchronized Measurement testAndAddMeasurement(Measurement m) {
        if (correctors.containsKey(m.STN)) {
            return (correctors.get(m.STN).testAndAddMeasurement(m));
        }
        else {
            correctors.put(m.STN, new Corrector());
            return  (correctors.get(m.STN).testAndAddMeasurement(m));
        }
    }



}