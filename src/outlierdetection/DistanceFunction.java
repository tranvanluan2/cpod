/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlierdetection;

import be.tarsos.lsh.Vector;
import mtree.tests.Data;
import mtree.tests.MTTest;

/**
 *
 * @author Luan
 */
public class DistanceFunction {

    // public static HashMap<String, Double> cache = new HashMap<>();
    public static double euclideanDistance(Data d1, Data d2) {


        double sumSquare = 0;
        for (int i = 0; i < d1.values.length; i++) {
            sumSquare += Math.pow((d1.values[i] - d2.values[i]), 2);
        }
        double distance = Math.sqrt(sumSquare);
        return distance;
    }

    public static double euclideanDistance(Vector d1, Vector d2) {
        double sumSquare = 0;
        for (int i = 0; i < d1.values.length; i++) {
            sumSquare += Math.pow((d1.values[i] - d2.values[i]), 2);
        }
        double distance = Math.sqrt(sumSquare);
        return distance;
    }

   
}
