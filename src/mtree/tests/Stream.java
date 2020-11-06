package mtree.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

import mtree.utils.Constants;

public class Stream {

    PriorityQueue<Data> streams;

    public static Stream streamInstance;
    public static int lastArrivalTime = 0;

    public static Stream getInstance(String type) {

        if (streamInstance != null) {
            return streamInstance;
        } else if (!Constants.dataFile.trim().equals("")) {
            streamInstance = new Stream();
//            streamInstance.getData(Constants.dataFile);
            return streamInstance;
        } 
//        else if ("ForestCover".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.forestCoverFileName);
//            return streamInstance;
//        } else if ("TAO".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.taoFileName);
//            return streamInstance;
//        } else if ("randomData".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.randomFileName1111);
//            return streamInstance;
//        } else if ("randomData0.001".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.randomFileName001);
//            return streamInstance;
//        } else if ("randomData0.01".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.randomFileName01);
//            return streamInstance;
//        } else if ("randomData0.1".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.randomFileName1);
//            return streamInstance;
//        } else if ("randomData1".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.randomFileName1percent);
//            return streamInstance;
//        } else if ("randomData10".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.randomFileName10percent);
//            return streamInstance;
//        } else if ("tagData".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.tagCALC);
//            return streamInstance;
//        } else if ("Trade".equals(type)) {
//            streamInstance = new Stream();
//            streamInstance.getData(Constants.STT);
//            return streamInstance;
//        } 
        else {
            streamInstance = new Stream();
            streamInstance.getRandomInput(1000, 10);
            return streamInstance;

        }

    }

    public boolean hasNext() {
        return !streams.isEmpty();
    }

    public ArrayList<Data> getIncomingDataSparse(int currentTime, int length, String filename, int numCols) {
        ArrayList<Data> results = new ArrayList<>();

        double[][] data = new double[length][numCols];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < numCols; j++) {
                data[i][j] = 0;
            }
        }
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File(filename)));

            String line = "";
            try {
                while ((line = bfr.readLine()) != null) {
                    String[] atts = line.split(",");
                    if (atts.length <= 1) {
                        break;
                    }
                    //check if we have correct row
                    Integer numRow = Integer.valueOf(atts[1]);
                    Integer numCol = Integer.valueOf(atts[0]);
                    Double value = Double.valueOf(atts[2]);
                    if (currentTime < numRow && numRow <= currentTime + length) {
                        data[numRow - currentTime - 1][numCol - 1] = value;
                    }
                }

            } catch (Exception e) {
            }

        } catch (Exception e) {
        }

        // System.out.println("-----------------------------");
        for (int i = 0; i < data.length; i++) {
//            
//            for(int j=0;j<numCols; j++)
//                System.out.print(data[i][j]+",");

            //  System.out.println("-----------------------------");
            Data d = new Data(data[i]);
            d.arrivalTime = currentTime + 1 + i;
            results.add(d);
        }

        return results;
    }

    public ArrayList<Data> getIncomingData(int currentTime, int length, String filename) {

        
        ArrayList<Data> results = new ArrayList<>();
        
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File(filename)));

            String line = "";
            int time = 0;
//            HashSet<Integer> selected_idx = new HashSet<Integer>();
            try {
                while ((line = bfr.readLine()) != null) {
                    time++;
                    if (time > currentTime && time <= currentTime + length) {
                        String[] atts = line.split(",");
                        double[] d = new double[atts.length];
                        for (int i = 0; i < d.length; i++) {

                            d[i] = Double.valueOf(atts[i])
//                                    + (new Random()).nextDouble() / 10000000
                                    ;
                        }
                        Data data = new Data(d);
                        data.arrivalTime = time;
                        results.add(data);
                    }
                }
                bfr.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return results;
    }

    public void getRandomInput(int length, int range) {

        Random r = new Random();
        for (int i = 1; i <= length; i++) {
            double d = r.nextInt(range);
            Data data = new Data(d);
            data.arrivalTime = i;
            streams.add(data);

        }

    }

    public void getData(String filename) {

        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File(filename)));

            String line = "";
            int time = 1;
            try {
                while ((line = bfr.readLine()) != null) {

                    String[] atts = line.split(",");
                    double[] d = new double[atts.length];
                    for (int i = 0; i < d.length; i++) {

                        d[i] = Double.valueOf(atts[i]);
                    }
                    Data data = new Data(d);
                    data.arrivalTime = time;
                    streams.add(data);
                    time++;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Stream() {
        // Comparator<Data> comparator = new DataComparator();

//        streams = new PriorityQueue<Data>(comparator);
    }

   
}

class DataComparator implements Comparator<Data> {

    @Override
    public int compare(Data x, Data y) {
        if (x.arrivalTime < y.arrivalTime) {
            return -1;
        } else if (x.arrivalTime > y.arrivalTime) {
            return 1;
        } else {
            return 0;
        }

    }

}
