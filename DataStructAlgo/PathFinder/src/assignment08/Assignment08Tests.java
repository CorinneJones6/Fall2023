package assignment08;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class Assignment08Tests {

    @Test
    void testSolveMazeWithValidPath() {
        // Maze file with valid path
        String inputFile = "mazes/straight.txt";
        String outputFile = "mazes/straightOutput.txt";
        String correctFile = "mazes/straightSol.txt";
        PathFinder.solveMaze(inputFile, outputFile);

        char[][] output = readContent(outputFile);
        char[][] correct = readContent(correctFile);

        for(int i=0; i<output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                assertEquals(output[i][j], correct[i][j]);
            }
        }
    }

    @Test
    void testSolveMazeWithBig(){
        // Maze file with valid path Big Maze
        String inputFile = "mazes/bigMaze.txt";
        String outputFile = "mazes/bigMazeOutput.txt";
        String correctFile = "mazes/bigMazeSol.txt";
        PathFinder.solveMaze(inputFile, outputFile);

        char[][] output = readContent(outputFile);
        char[][] correct = readContent(correctFile);

        for(int i=0; i<output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                assertEquals(output[i][j], correct[i][j]);
            }
        }
    }

    @Test
    void testSolveMazeWithNoPath() {
        // Maze file with no valid path
        String inputFile = "mazes/unsolvable.txt";
        String outputFile = "mazes/unsolvableOutput.txt";
        String correctFile = "mazes/unsolvableSol.txt";
        PathFinder.solveMaze(inputFile, outputFile);

        char[][] output = readContent(outputFile);
        char[][] correct = readContent(correctFile);

        for(int i=0; i<output.length; i++) {
            for (int j = 0; j < output[0].length; j++) {
                assertEquals(output[i][j], correct[i][j]);
            }
        }
    }

    private char[][] readContent(String input){
        try {
            File file = new File(input);
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

    @Test
    void testGraphConstructor() {
        char[][] inputData = {
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'S', ' ', ' ', 'X'},
                {'X', ' ', 'X', ' ', 'X'},
                {'X', ' ', 'X', 'G', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        };

        Graph graph = new Graph(inputData);

        // Assert to check graph after construction
        assertNotNull(graph.getStart());
        assertNotNull(graph.getEnd());
        assertEquals('S', graph.getStart().getValue());
        assertEquals('G', graph.getEnd().getValue());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                assertEquals(graph.getGraphData()[i][j].getValue(), inputData[i][j]);
        }
    }

    @Test
    void testGraphConnectNodes() {
        char[][] inputData = {
                {'X', 'X', 'X', 'X', 'X'},
                {'X', 'S', ' ', ' ', 'X'},
                {'X', ' ', 'X', ' ', 'X'},
                {'X', ' ', 'X', 'G', 'X'},
                {'X', 'X', 'X', 'X', 'X'}
        };

        Graph graph = new Graph(inputData);
        graph.connectNodes();

        Graph.Node[][] graphData = graph.getGraphData();

        // Check that all nodes in the graph have their neighbors connected appropriately
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (graphData[row][col] != null) {
                    Graph.Node currentNode = graphData[row][col];

                    // Check the node above
                    if (row > 0 && graphData[row - 1][col].getValue() != 'X') {
                        assertTrue(currentNode.getNeighbors().contains(graphData[row - 1][col]));
                    }
                    // Check the node below
                    if (row < graphData.length - 1 && graphData[row + 1][col].getValue() != 'X') {
                        assertTrue(currentNode.getNeighbors().contains(graphData[row + 1][col]));
                    }
                    // Check the left node
                    if (col > 0 && graphData[row][col - 1].getValue() != 'X') {
                        assertTrue(currentNode.getNeighbors().contains(graphData[row][col - 1]));
                    }
                    // Check the right node
                    if (col < graphData[0].length - 1 && graphData[row][col + 1].getValue() != 'X') {
                        assertTrue(currentNode.getNeighbors().contains(graphData[row][col + 1]));
                    }
                }
            }
        }
    }
}