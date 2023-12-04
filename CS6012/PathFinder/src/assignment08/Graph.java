package assignment08;

import java.util.*;

public class Graph {
    private Node[][] graphData_;
    private Node start_;
    private Node end_;

    public class Node {
        private char value_;
        private boolean visited_;
        private final ArrayList<Node> neighbors_;
        private Node cameFrom_;

        /**
         * Node constructor which initializes all the member variables
         *
         * @param value - value to be stored in the Node value
         */
        public Node(char value) {
            value_ = value;
            visited_ = false;
            neighbors_ = new ArrayList<>();
            cameFrom_ = null;
        }

        /**
         * All methods below return Node member variables
         *
         * @return member variable requested
         */
        public char getValue() {
            return value_;
        }

        public void setValue(char s) {
            value_ = s;
        }

        public Node getCameFrom() {
            return cameFrom_;
        }

        public void setCameFrom(Node cameFrom) {
            cameFrom_ = cameFrom;
        }

        public ArrayList<Node> getNeighbors() {
            return neighbors_;
        }
    }

    /**
     * @return - member variable Node start_
     */
    public Node getStart() {
        return start_;
    }

    /**
     * @return - member variable Node end_
     */
    public Node getEnd() {
        return end_;
    }

    public Node[][] getGraphData() {
        return graphData_;
    }

    /**
     * Graph constructor which stores all the inputData as graphData
     *
     * @param inputData - 2D array of chars to convert to a graph
     */
    Graph(char[][] inputData) {
        int numRows = inputData.length;
        int numCols = inputData[0].length;

        graphData_ = new Node[numRows][numCols];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                graphData_[row][col] = new Node(inputData[row][col]);
                if (graphData_[row][col].value_ == 'S') {
                    start_ = graphData_[row][col];
                } else if (graphData_[row][col].value_ == 'G') {
                    end_ = graphData_[row][col];
                }
            }
        }
        connectNodes();
    }

    /**
     * Helper method that stores all the node neighbors
     */
    public void connectNodes() {
        for (int row = 0; row < graphData_.length; row++) {
            for (int col = 0; col < graphData_[0].length; col++) {
                if (graphData_[row][col] != null) {
                    //Add the node above
                    if (row > 0 && graphData_[row - 1][col].value_ != 'X') {
                        graphData_[row][col].neighbors_.add(graphData_[row - 1][col]);
                    }
                    //Add the node below
                    if (row < graphData_.length - 1 && graphData_[row + 1][col].value_ != 'X') {
                        graphData_[row][col].neighbors_.add(graphData_[row + 1][col]);
                    }
                    //Add the left node
                    if (col > 0 && graphData_[row][col - 1].value_ != 'X') {
                        graphData_[row][col].neighbors_.add(graphData_[row][col - 1]);
                    }
                    //Add the right node
                    if (col < graphData_[0].length - 1 && graphData_[row][col + 1].value_ != 'X') {
                        graphData_[row][col].neighbors_.add(graphData_[row][col + 1]);
                    }
                }
            }
        }
    }

    /**
     * Implements a Breadth First Search
     *
     * @return - the 2D array of Nodes with "." in the path, or null if no path exists
     */
    public Node[][] findPath() {
        Queue<Node> frontier = new LinkedList<>();
        Node start = getStart();
        Node end = getEnd();
        start.visited_ = true;
        frontier.add(start);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            // If end is found
            if (current == end) {
                current = current.getCameFrom();
                while (current != null && current != start) {
                    current.setValue('.');
                    current = current.getCameFrom();
                }
                return graphData_;
            }

            //If end is not current
            for (Node neighbor : current.getNeighbors()) {
                if (!neighbor.visited_) {
                    neighbor.visited_ = true;
                    neighbor.setCameFrom(current);
                    frontier.add(neighbor);
                }
            }
        }
        //Not found, return null
        return null;
    }
}

