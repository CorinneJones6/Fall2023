package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.SortedSet;

public class TimingExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {

        int beginning=1;
        int end=5001;
        int increment=500;

        // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;

        try (FileWriter fw = new FileWriter(new File("BSTTree_experiment.tsv"), false)) { // open up a file writer so we can write
            // to file.

            for (int size = beginning; size <= end; size+=increment) { // This is used as the exponent to calculate the size of the set.
//                int size = (int) Math.pow(2, exp); // or ..

                // Do the experiment multiple times, and average out the results
                long totalTime = 0;

                ArrayList<Integer> rando = new ArrayList<>();
                Random random = new Random();

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // SET UP!
                    for (int i = 0; i < size; i++) {
                        rando.add(i);
                    }

                    for(int k = 0; k < size; k++){
                        rando.set(k, rando.get(random.nextInt(size)));
                    }

                    BinarySearchTree<Integer> bst = new BinarySearchTree<>();

                    bst.addAll(rando);

                    // TIME IT!
                    long start = System.nanoTime();

                    for(int i=0; i<bst.size(); i++) {
                        bst.contains(i);
                    }

                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double bstOrder = totalTime / (double) ITER_COUNT;

/**
 * Adding in random order
 */
                totalTime=0;

                ArrayList<Integer> randList = new ArrayList<>();
                Random rand= new Random();

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // SET UP!
                    for (int i = 0; i < size; i++) {
                        randList.add(i);
                    }

                    for(int k = 0; k < size; k++){
                        randList.set(k, randList.get(rand.nextInt(size)));
                    }

                    TreeSet<Integer> treeSet = new TreeSet<>(randList);

                    // TIME IT!
                    long start = System.nanoTime();

                    for(int i=0; i<treeSet.size(); i++) {
                        treeSet.contains(i);
                    }

                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double treeSetOrder=totalTime/(double) ITER_COUNT;


                var output=size + "," + treeSetOrder + "," + bstOrder  + "\r\n";
                System.out.println(output); // print to console
                fw.write(output); // write to file.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

