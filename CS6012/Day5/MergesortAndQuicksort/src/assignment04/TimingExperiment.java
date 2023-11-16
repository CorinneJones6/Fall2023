package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TimingExperiment {

    private static final int ITER_COUNT = 10;

    public static void main(String[] args) {
        // you spin me round baby, right round
        int beginning=2;
        int end=1000;
        int increment=5;

        Comparator<Integer> myComparator = Comparator.naturalOrder();

        ArrayList<Integer> randArray = SortUtil.generateAverageCase(end);
        ArrayList<Integer>bestArray=SortUtil.generateBestCase(end);
        ArrayList<Integer>worstArray=SortUtil.generateWorstCase(end); 

        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000) ;

        try (FileWriter fw = new FileWriter(new File("MergeVsQuickWorst_experiment.tsv"), false)) { // open up a file writer so we can write
            // to file.
            for (int size = beginning; size <= end; size+=increment) { // This is used as the exponent to calculate the size of the set.

                // Do the experiment multiple times, and average out the results
                long totalTime = 0;

                for (int iter = 0; iter <ITER_COUNT; iter++) {
                    ArrayList<Integer> arrayCopy=new ArrayList<>(worstArray);

                    // TIME IT!
                    long start = System.nanoTime();

                    SortUtil.mergesort(arrayCopy, 0, size, myComparator);

                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double averageTimeMerge = totalTime / (double) ITER_COUNT;




                totalTime = 0;

                for (int iter = 0; iter <ITER_COUNT; iter++) {
                    ArrayList<Integer> arrayCopy=new ArrayList<>(worstArray);

                    // TIME IT!
                    long start = System.nanoTime();

                    SortUtil.quicksort(arrayCopy, 0, size, myComparator);

                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double averageTimeQuick = totalTime / (double) ITER_COUNT;

                double mergeOutput=averageTimeMerge;
                double quickOutput=averageTimeQuick;

                var output = size + "," + mergeOutput + "," + quickOutput + "\r\n";

                System.out.println(output); // print to console
                fw.write(output); // write to file.

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

