package mtree.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Constants {
    
    public static double R = 1;
    public static int k = 50;
    public static int W = 10000;
    public static int numberWindow = -1;
    public static int slide = 500;
    public static int numCols = 1;
    public static int dimensions = 55;
    public static int samplingPeriod = 100;
//    public static String forestCoverFileName = "C:\\Users\\Luan\\MTree_New\\covtype.data";
//    public static String taoFileName = "C:\\Users\\Luan\\MTree_New\\tao.txt";
//    public static String outputStorm = "C:\\Users\\Luan\\MTree_New\\storm";
//
//    public static String outputapStorm = "C:\\Users\\Luan\\MTree_New\\ap_storm";
//    public static String outputabstractC = "C:\\Users\\Luan\\MTree_New\\abstractC";
//    public static String outputLUE = "C:\\Users\\Luan\\MTree_New\\LUE";
//    public static String outputDUE = "C:\\Users\\Luan\\MTree_New\\DUE";
//    public static String outputMESI = "C:\\Users\\Luan\\MTree_New\\MESI";
//    
//    public static String randomFileName = "C:\\Users\\Luan\\MTree_New\\GaussMixture.txt";
//    public static String randomFileName1111 = "C:\\Users\\Luan\\MTree_New\\randomData1.txt";
//    public static String randomFileName001 = "C:\\Users\\Luan\\MTree_New\\Gauss0.001percentOutlier.txt";
//    public static String randomFileName01 = "C:\\Users\\Luan\\MTree_New\\Gauss0.01percentOutlier.txt";
//    public static String randomFileName1 = "C:\\Users\\Luan\\MTree_New\\Gauss0.1percentOutlier.txt";
//    public static String randomFileName1percent =  "C:\\Users\\Luan\\MTree_New\\Gauss1percentOutlier.txt";
//    public static String randomFileName10percent =  "C:\\Users\\Luan\\MTree_New\\Gauss10percentOutlier.txt"; 
//    public static String tagFileName = "C:\\Users\\Luan\\MTree_New\\tag.txt";
//    public static String tagCALC = "C:\\Users\\Luan\\MTree_New\\tagCALC.txt";
//    public static String tagFileName1 = "C:\\Users\\Luan\\MTree_New\\tag1.txt";
//
//    public static String tagFileName2 = "C:\\Users\\Luan\\MTree\\tag2.txt";
//    public static String tagFileName3 = "C:\\Users\\Luan\\MTree\\tag3.txt";
//
//    public static String tagFileName4 = "C:\\Users\\Luan\\MTree\\tag4.txt";
//
//    public static String tagFileName5 = "C:\\Users\\Luan\\MTree\\tag5.txt";
//    public static String outputMicro = "C:\\Users\\Luan\\MTree\\micro";
//    public static String STT = "C:\\Users\\Luan\\MTree_New\\stt.txt";
    public static String dataFile= "";
    public static String outputFile= "outputDefault.txt";
    public static String resultFile="";
    
    public static int type_MCOD =0;
    public static int minSizeOfCluster = Constants.k+1;
    public static int weightedCluster = 2;
    public static int numClusterToAdd = 5;
    public static int maxClusterEachPoint=5;
    public static String sharedFolder;
    public static String algorithm;
    
    
    public static void readConfig() throws FileNotFoundException, IOException {
        //read slide size
        //read window size
        //k 
        //R
        //read data file 
        //shared folder
        //algorithm

        FileInputStream fis = new FileInputStream("config.txt");

        try ( //Construct BufferedReader from InputStreamReader
                BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            String line = null;
            Constants.slide = Integer.valueOf(br.readLine().trim());
            Constants.W = Integer.valueOf(br.readLine().trim());
            Constants.k = Integer.valueOf(br.readLine().trim());
            Constants.R = Double.valueOf(br.readLine().trim());
            Constants.dataFile = br.readLine().trim();
            Constants.sharedFolder = br.readLine().trim();
            Constants.algorithm = br.readLine().trim();
            
        }

    }
    

}
