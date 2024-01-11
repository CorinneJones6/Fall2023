package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class TimingExperiment {

    //initializing the iteration count to 100
    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        int beginning=1;
        int end=10000;
        int increment=5;

        Random rand = new Random();
        //set up the filewriter to hold the data
        try (FileWriter fw = new FileWriter(new File("arrayLinkedPeek.tsv"), false)) {

            for (int size = beginning; size <= end; size+=increment) {

                //initialize the total time at 0 to be updated later
                long totalTime = 0;

                //setting up the type of stack
                LinkedListStack<Integer> linkedStack = new LinkedListStack<>();
                ArrayStack<Integer> arrayStack = new ArrayStack<>();

//                int elem = rand.nextInt(size);

                for(int i = 0; i < size; i++){
                    arrayStack.push(i);
                    linkedStack.push(i);
                }


                //start loop
                for (int iter = 0; iter < ITER_COUNT; iter++) {

                    //start the timer (get the start time)
                    long start = System.nanoTime();

                    //execute the contains function
                    if (!arrayStack.isEmpty()) {
                        arrayStack.peek();
                    }

                    //stop the timer (get the end time)
                    long stop = System.nanoTime();
                    //get the total time (stop time - start time = execution time)
                    totalTime += stop - start;
                }

                //get the average time from all 100 experiments per sample size
                double averageTimeArray = totalTime / (double) ITER_COUNT;




                totalTime = 0;

                for (int iter = 0; iter <ITER_COUNT; iter++) {

                    // TIME IT!
                    long start = System.nanoTime();

                    if (!linkedStack.isEmpty()) {
                        linkedStack.peek();
                    }

                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double averageTimeLinked = totalTime / (double) ITER_COUNT;




                //write to the console and to a file what the average time was for each sample size
                var output=size + "," + averageTimeArray + "," + averageTimeLinked + "\r\n";

                System.out.println(output);
                fw.write(output);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
