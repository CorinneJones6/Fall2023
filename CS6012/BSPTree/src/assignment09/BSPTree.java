package assignment09;

import java.util.ArrayList;

public class BSPTree implements SegmentCallback {

    public Node root_;

    public class Node {
        private Segment segment_;
        private Node front_;
        private Node back_;

        /**
         * Constructs a Node with the given dividing segment and front and back subtrees.
         *
         * @param dividingSegment - The dividing segment associated with this node
         */
        public Node(Segment dividingSegment) {
            segment_ = dividingSegment;
            front_ = null;
            back_ = null;
        }
    }

    /**
     * Empty constructor for creating an empty BSPTree
     */
    public BSPTree() {
        root_ = null;
    }

    /**
     * Constructor with ArrayList as a parameter, loops through calling the "insert" method
     *
     * @param segments ArrayList of Segments to be added to the BSP Tree
     */
    public BSPTree(ArrayList<Segment> segments) {
        for (Segment segment : segments) {
            insert(segment);
        }
    }

    /**
     * Inserts a new segment into the BSPTree
     *
     * @param newSegment - The segment to be inserted
     */
    public void insert(Segment newSegment) {
        if (root_ == null) {
            // If the tree is empty, create a new root node with the new segment
            root_ = new Node(newSegment);
        } else {
            // Otherwise, recursively insert the new segment into the tree
            insertRecursive(root_, newSegment);
        }
    }

    /**
     * Recursively inserts a new segment into the BSPTree
     *
     * @param newSegment  - The segment to be inserted
     * @param currentNode - The current node being considered
     */
    private void insertRecursive(Node currentNode, Segment newSegment) {

        if (newSegment.whichSide(currentNode.segment_) < 0) {
            // If the back subtree is empty, create a new node with the new segment
            if (currentNode.back_ == null) {
                currentNode.back_ = new Node(newSegment);
            } else {
                // If the back subtree is not empty, recursively insert the new segment into the back subtree
                insertRecursive(currentNode.back_, newSegment);
            }
        } else if (newSegment.whichSide(currentNode.segment_) > 0) {
            if (currentNode.front_ == null) {
                currentNode.front_ = new Node(newSegment);
            } else {
                insertRecursive(currentNode.front_, newSegment);
            }
        } else {
            // If the new segment is coincident with the current node's segment, perform a split
            Segment[] splitResult = currentNode.segment_.split(newSegment);
            if (currentNode.front_ == null) {
                currentNode.front_ = new Node(splitResult[0]);
            } else {
                insertRecursive(currentNode.front_, splitResult[0]);
            }
            if (currentNode.back_ == null) {
                currentNode.back_ = new Node(splitResult[1]);
            } else {
                insertRecursive(currentNode.back_, splitResult[1]);
            }
        }
    }

    /**
     * Traverses the BSPTree from far to near based on the given point (x, y)
     *
     * @param x        - The x-coordinate of the point
     * @param y        - The y-coordinate of the point
     * @param callback - The callback function to be applied to each traversed segment
     */
    public void traverseFarToNear(double x, double y, SegmentCallback callback) {
        traverseFarToNearRecursive(root_, x, y, callback);
    }

    /**
     * Recursively traverses the BSPTree from far to near based on the given point (x, y)
     *
     * @param currentNode - The current node being considered
     * @param x           - The x-coordinate of the point
     * @param y           - The y-coordinate of the point
     * @param callback    - The callback function to be applied to each traversed segment
     */
    private void traverseFarToNearRecursive(Node currentNode, double x, double y, SegmentCallback callback) {
        if (currentNode == null) {
            return;
        }

        int side = currentNode.segment_.whichSidePoint(x, y);

        if (side < 0) {
            // Point is on the + side, traverse the back subtree first
            traverseFarToNearRecursive(currentNode.back_, x, y, callback);
            callback.callback(currentNode.segment_);
            traverseFarToNearRecursive(currentNode.front_, x, y, callback);
        } else if (side > 0) {
            // Point is on the - side, traverse the front subtree first
            traverseFarToNearRecursive(currentNode.front_, x, y, callback);
            callback.callback(currentNode.segment_);
            traverseFarToNearRecursive(currentNode.back_, x, y, callback);
        } else {
            // Handle coincident segments
            callback.callback(currentNode.segment_);
            traverseFarToNearRecursive(currentNode.back_, x, y, callback);
            traverseFarToNearRecursive(currentNode.front_, x, y, callback);
        }
    }

    /**
     * Finds the collision between the given query segment and the segments in the BSPTree
     *
     * @param query - The query segment to check for collision
     * @return The colliding segment, or null if no collision is found
     */
    public Segment collision(Segment query) {
        return collision(root_, query);
    }

    /**
     * Recursively searches for a collision between the given query segment and the segments in the BSPTree
     *
     * @param currentNode - The current node in the search
     * @param query       - The query segment to check for collision
     * @return The colliding segment, or null if no collision is found in this branch of the tree
     */
    private Segment collision(Node currentNode, Segment query) {
        if (currentNode == null) {
            return null;
        }

        int side = query.whichSide(currentNode.segment_);

        if (side < 0) {
            return collision(currentNode.back_, query);
        } else if (side > 0) {
            return collision(currentNode.front_, query);
        } else {
            // Handle coincident segments
            if (query.intersects(currentNode.segment_)) {
                return currentNode.segment_;
            } else {
                Segment leftCollision = collision(currentNode.back_, query);
                if (leftCollision != null) {
                    return leftCollision;
                } else {
                    return collision(currentNode.front_, query);
                }
            }
        }
    }

    /**
     * Callback method to be called for each segment during tree traversal
     *
     * @param s - The segment being traversed
     */
    @Override
    public void callback(Segment s) {

    }
}
