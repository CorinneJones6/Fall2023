package assignment07;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class TimingExperiment {
    private static LinkedList<String> readStringsFromFile(File file) throws IOException {
        LinkedList<String> strings = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        }
        return strings;
    }

    private static final int MEASURED_RUNS = 100;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000)
            ;
        try (FileWriter fw = new FileWriter(new File("RemovCollisionTime.tsv"))) {
            double goodHashTime = 0;
            double mediocreHashTime = 0;
            double badHashTime = 0;

            for (int exp = 8; exp <= 18; exp++) {
                int size = (int) Math.pow(2, exp);

                // Initialize a new ChainingHashTable with the appropriate size
                ChainingHashTable goodHashTable = new ChainingHashTable(size, new GoodHashFunctor());
                ChainingHashTable mediocreHashTable = new ChainingHashTable(size, new MediocreHashFunctor());
                ChainingHashTable badHashTable = new ChainingHashTable(size, new BadHashFunctor());

                // Populate the hash table with random strings
//                for (int j = 0; j < size; j++) {
//                    byte[] array = new byte[5]; // length is bounded by 7
//                    new Random().nextBytes(array);
//                    String generatedString = new String(array, Charset.forName("UTF-8"));

                var file = new File("dictionary.txt");
                LinkedList<String> stringsFromFile = readStringsFromFile(file);
                goodHashTable.addAll(stringsFromFile);
                mediocreHashTable.addAll(stringsFromFile);
                badHashTable.addAll(stringsFromFile);


                // Add the generated string to the hash table
                long totalTime = 0;
                long start = System.nanoTime();
                goodHashTable.removeAll(stringsFromFile);
                long stop = System.nanoTime();
                totalTime += stop - start;
                goodHashTime = totalTime;

                totalTime = 0;
                start = System.nanoTime();
                mediocreHashTable.removeAll(stringsFromFile);
                stop = System.nanoTime();
                totalTime += stop - start;
                mediocreHashTime = totalTime;

                totalTime = 0;
                start = System.nanoTime();
                badHashTable.removeAll(stringsFromFile);
                stop = System.nanoTime();
                totalTime += stop - start;
                badHashTime = totalTime;
//                }

                // Check for collisions
                int goodcount = 0;
                for (LinkedList<String> list : goodHashTable.getStorage_()) {
                    if (list != null && list.size() > 1) {
                        goodcount += list.size() - 1;
                    }
                }

                int mediocrecount = 0;
                for (LinkedList<String> list : mediocreHashTable.getStorage_()) {
                    if (list != null && list.size() > 1) {
                        mediocrecount += list.size() - 1;
                    }
                }

                int badcount = 0;
                for (LinkedList<String> list : badHashTable.getStorage_()) {
                    if (list != null && list.size() > 1) {
                        badcount += list.size() - 1;
                    }
                }

                var output = size + "," + badcount + "," + mediocrecount + "," + goodcount + "\r\n";

                var timeoutput = size + "," + badHashTime + "," + mediocreHashTime + "," + goodHashTime + "\r\n";

                System.out.println(timeoutput);
                fw.write(timeoutput);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
