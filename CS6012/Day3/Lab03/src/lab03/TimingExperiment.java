package lab03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class TimingExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;

        try (FileWriter fw = new FileWriter(new File("tailSet_experiment.tsv"))) { // open up a file writer so we can write
            // to file.
            Random random = new Random();
            for (int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
                int size = (int) Math.pow(2, exp); // or ..

                // Do the experiment multiple times, and average out the results
                long totalTime = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // SET UP!
                    SortedSet<Integer> set = new TreeSet<>();
                    for (int i = 0; i < size; i++) {
                        set.add(i);
                    }
                    int elem = random.nextInt(size); // This gets me a random int between 0 and size;

                    // TIME IT!
                    long start = System.nanoTime();
                    set.tailSet(elem);
                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }
                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.println("Size: " + size + "\t" + "AvgTime: " + averageTime); // print to console
                fw.write("Size: " + size + "\t" + "AvgTime: " + averageTime + "\n"); // write to file.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

