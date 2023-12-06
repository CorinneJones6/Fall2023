package assignment09;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BSPTreeTest {

    @org.junit.jupiter.api.Test
    void testInsertEmptyTree() {
        BSPTree bspTree = new BSPTree();
        Segment newSegment = new Segment(3, 4, 5, 1);

        bspTree.insert(newSegment);

        // Check that the root is not null after insertion
        assertNotNull(bspTree.root_);
    }

    @org.junit.jupiter.api.Test
    public void testInsertNonEmptyTree() {
        // Create a tree with an existing segment
        ArrayList<Segment> existingSegments = new ArrayList<>();
        existingSegments.add(new Segment(Math.random(), Math.random(), Math.random(), Math.random()));
        BSPTree bspTree = new BSPTree(existingSegments);

        // Insert a new segment, size is now 2
        Segment newSegment = new Segment(0,.25, 0, .25);
        bspTree.insert(newSegment);

        ArrayList<Segment> traversedSegments = new ArrayList<>();
        bspTree.traverseFarToNear(0, 0, traversedSegments::add);

        // Assert the size is the same, the root is the same.  Add 1 because the values cause a split
        // Ensure this happens in the testing
        assertEquals(3, traversedSegments.size());
        assertNotNull(bspTree.root_);
    }

    @org.junit.jupiter.api.Test
    void testTraverseFarToNearEmptyTree() {
        BSPTree bspTree = new BSPTree();
        ArrayList<Segment> traversedSegments = new ArrayList<>();

        // Perform traversal
        bspTree.traverseFarToNear(0, 0, traversedSegments::add);

        // Check that the list of traversed segments is empty
        assertTrue(traversedSegments.isEmpty());
    }


    @org.junit.jupiter.api.Test
    void testTraverseFarToNearNonEmptyTree() {
        // Create a tree with segments
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(Math.random(), Math.random(), Math.random(), Math.random()));
        segments.add(new Segment(Math.random(), Math.random(), Math.random(), Math.random()));

        BSPTree bspTree = new BSPTree(segments);
        ArrayList<Segment> traversedSegments = new ArrayList<>();

        // Perform traversal
        bspTree.traverseFarToNear(0, 0, traversedSegments::add);

        //Check that the size is the same, the root is the same
        assertEquals(segments.size(), traversedSegments.size());
        assertEquals(segments.get(0), traversedSegments.get(0));
    }

    @org.junit.jupiter.api.Test
    void collision() {
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(new Segment(0, 0, 2, 0));  // Horizontal segment
        segments.add(new Segment(1, 1, 1, 3));  // Vertical segment
        BSPTree bspTree = new BSPTree(segments);

        // Query segment that collides with the existing segments
        Segment query = new Segment(0.5, 0.5, 1.5, 2.5);

        // Perform collision check
        Segment result = bspTree.collision(query);

        // Assert that a collision occurred and the result is the colliding segment
        assertNotNull(result);
        assertTrue(result.intersects(query));
    }

}