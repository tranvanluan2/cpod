package mtree.tests;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

import mtree.utils.Constants;
import mtree.utils.Utils;

import outlierdetection.CPOD;

public class MTTest {

    public static int currentTime = 0;

    public static boolean stop = false;

    public static HashSet<Integer> idOutliers = new HashSet<>();

    public static String algorithm;
    public static int numberWindows = 0;

    public static double start;

    public static void main(String[] args) {

        readArguments(args);
        for (String arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println("");

        MesureMemoryThread mesureThread = new MesureMemoryThread();
        mesureThread.start();
        Stream s = Stream.getInstance("");

        CPOD cpod = new CPOD();

        double totalTime = 0;
        while (!stop) {

            if (Constants.numberWindow != -1 && numberWindows > Constants.numberWindow) {
                break;
            }
            numberWindows++;
            System.out.println("Num window = " + numberWindows);

            ArrayList<Data> incomingData;
            if (currentTime != 0) {
                incomingData = s.getIncomingData(currentTime, Constants.slide, Constants.dataFile);
                currentTime = currentTime + Constants.slide;
//                System.out.println("Last idx time = " + (incomingData.get(incomingData.size()-1).arrivalTime-1));
            } else {
                incomingData = s.getIncomingData(currentTime, Constants.W, Constants.dataFile);
                currentTime = currentTime + Constants.W;

                Constants.dimensions = incomingData.get(0).values.length;

            }

            start = Utils.getCPUTime(); // requires java 1.5

            /**
             * do algorithm
             */
            switch (algorithm) {
                case "cpod":
                    ArrayList<Data> outliers = cpod.detectOutlier(incomingData, currentTime, Constants.W, Constants.slide);

                    double elapsedTimeInSec = (Utils.getCPUTime() - start) * 1.0 / 1000000000;
                    System.out.println("Num outliers = " + outliers.size());
                    if (numberWindows > 1) {
                        totalTime += elapsedTimeInSec;
                    }

                    break;

            }

            if (numberWindows == 1) {
                totalTime = 0;
                MesureMemoryThread.timeForIndexing = 0;
                MesureMemoryThread.timeForNewSlide = 0;
                MesureMemoryThread.timeForExpireSlide = 0;
                MesureMemoryThread.timeForQuerying = 0;

            }

        }

        mesureThread.averageTime = totalTime * 1.0 / (numberWindows - 1);
        mesureThread.writeResult();
        mesureThread.stop();
        mesureThread.interrupt();

    }

    public static void readArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {

            //check if arg starts with --
            String arg = args[i];
            if (arg.indexOf("--") == 0) {
                switch (arg) {
                    case "--algorithm":
                        algorithm = args[i + 1];
                        break;
                    case "--R":
                        Constants.R = Double.valueOf(args[i + 1]);
                        break;
                    case "--W":
                        Constants.W = Integer.valueOf(args[i + 1]);
                        break;
                    case "--k":
                        Constants.k = Integer.valueOf(args[i + 1]);
                        Constants.minSizeOfCluster = Constants.k + 1;
                        break;
                    case "--datafile":
                        Constants.dataFile = args[i + 1];
                        break;
                    case "--output":
                        Constants.outputFile = args[i + 1];
                        break;
                    case "--numberWindow":
                        Constants.numberWindow = Integer.valueOf(args[i + 1]);
                        break;
                    case "--slide":
                        Constants.slide = Integer.valueOf(args[i + 1]);
                        break;
                    case "--resultFile":
                        Constants.resultFile = args[i + 1];
                        break;
                    case "--samplingTime":
                        Constants.samplingPeriod = Integer.valueOf(args[i + 1]);
                        break;


                }
            }
        }
    }

    public static void writeResult() {

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Constants.resultFile, true)))) {
            for (Integer time : idOutliers) {
                out.println(time);
            }
        } catch (IOException e) {
        }

    }

}
