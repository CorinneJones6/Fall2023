package assignment08;

import java.io.*;
import java.util.Scanner;

public class PathFinder {
    private static Graph.Node[][] path_;

    /**
     * This method calls the appropriate methods to complete the solution
     *
     * @param inputFile  - the filepath to solve
     * @param outputFile - the filepath solution, if solvable
     */
    public static void solveMaze(String inputFile, String outputFile) {
        char[][] maze_ = readInputFile(inputFile);
        Graph graph_ = new Graph(maze_);
        path_ = graph_.findPath();
        if (path_ == null) {
            returnOriginalFile(inputFile, outputFile);
        } else {
            returnPathFile(outputFile);
        }
    }

    /**
     * Reads in and stores the inputFIle
     *
     * @param inputFile - the maze to read in
     * @return - the inputFile as a 2D array
     */
    private static char[][] readInputFile(String inputFile) {
        try {
            File file = new File(inputFile);
            Scanner sc = new Scanner(file);

            int height = sc.nextInt();
            int width = sc.nextInt();
            sc.nextLine();

            char[][] grid = new char[height][width];

            for (int i = 0; i < height; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < width; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }
            sc.close();
            return grid;

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    /**
     * Writes the original inputFile with a marked path
     *
     * @param outputFile - the output file to write too
     */
    private static void returnPathFile(String outputFile) {
        try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
            output.println(path_.length + " " + path_[0].length);

            for (Graph.Node[] nodes : path_) {
                for (int j = 0; j < path_[0].length; j++) {
                    char value = nodes[j].getValue();
                    output.print(value);
                }
                output.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not write to file: " + e.getMessage());
        }
    }

    /**
     * Writes the original inputFile when no path is found
     *
     * @param inputFile  - the original inputFile to read from
     * @param outputFile - the outputFile to write too
     */
    private static void returnOriginalFile(String inputFile, String outputFile) {
        try (Scanner sc = new Scanner(new File(inputFile));
             PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {

            while (sc.hasNextLine()) {
                output.println(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not write to file: " + e.getMessage());
        }
    }

}
