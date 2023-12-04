package assignment08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Assignment08Tests {

    @Test
    void testConstructor() {
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
    void testConnectNodes() {
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

    @Test
    void findPath() {
        //============Tested via TestPathFinder===========//
    }
}