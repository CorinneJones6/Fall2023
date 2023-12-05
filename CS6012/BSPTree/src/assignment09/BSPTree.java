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
         * @param front - The front subtree
         * @param back - The back subtree
         */
        public Node(Segment dividingSegment, Node front, Node back) {
            segment_ = dividingSegment;
            front_ = front;
            back_ = back;
        }
    }

    /**
     * Empty constructor for creating an empty BSPTree
     */
    public BSPTree() {
        root_ = null;
    }

    /**
     * Constructor with ArrayList as a parameter, Recursively calls the buildTree method
     *
     * @param segments ArrayList of Segments to be added to the BSP Tree
     */
    public BSPTree(ArrayList<Segment> segments) {
        root_ = buildTree(segments);
    }

    /**
     * Builds a BSPTree recursively from the given list of segments
     *
     * @param segments - The list of segments to build the tree from
     * @return The root node of the constructed tree
     */
    private Node buildTree(ArrayList<Segment> segments) {
        if (segments == null || segments.isEmpty()) {
            return null;
        }

        Segment dividingSegment = selectDividingSegment(segments);

        ArrayList<Segment> frontSegments = new ArrayList<>();
        ArrayList<Segment> backSegments = new ArrayList<>();

        for (Segment segment : segments) {
            if (segment.whichSide(dividingSegment) > 0) {
                frontSegments.add(segment);
            } else if (segment.whichSide(dividingSegment) < 0) {
                backSegments.add(segment);
            } else {
                Segment[] splitResult = segment.split(dividingSegment);
                frontSegments.add(splitResult[0]);
                backSegments.add(splitResult[1]);
            }
        }

        Node frontNode = buildTree(frontSegments);
        Node backNode = buildTree(backSegments);

        Node currentNode = new Node(dividingSegment, frontNode, backNode);

        callback(dividingSegment);

        return currentNode;
    }

    /**
     * Selects the dividing segment from the given list of segments
     *
     * @param segments - The list of segments to choose the dividing segment from
     * @return The selected dividing segment
     */
    private Segment selectDividingSegment(ArrayList<Segment> segments) {
        if (segments.size() % 2 == 0) {
            return segments.get((segments.size() / 2) - 1);
        } else {
            return segments.get(segments.size() / 2);
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
            root_ = new Node(newSegment, null, null);
        } else {
            // Otherwise, recursively insert the new segment into the tree
            insertRecursive(newSegment, root_);
        }
    }

    /**
     * Recursively inserts a new segment into the BSPTree
     *
     * @param newSegment - The segment to be inserted
     * @param currentNode - The current node being considered
     */
    private void insertRecursive(Segment newSegment, Node currentNode) {
        if (newSegment.whichSide(currentNode.segment_) > 0) {
            if (currentNode.front_ == null) {
                currentNode.front_ = new Node(newSegment, null, null);
            } else {
                insertRecursive(newSegment, currentNode.front_);
            }
        } else if (newSegment.whichSide(currentNode.segment_) < 0) {
            if (currentNode.back_ == null) {
                currentNode.back_ = new Node(newSegment, null, null);
            } else {
                insertRecursive(newSegment, currentNode.back_);
            }
        } else {
            Segment[] splitResult = currentNode.segment_.split(newSegment);

            currentNode.front_ = new Node(splitResult[0], null, null);
            currentNode.back_ = new Node(splitResult[1], null, null);
        }
    }

    /**
     * Traverses the BSPTree from far to near based on the given point (x, y)
     *
     * @param x - The x-coordinate of the point
     * @param y - The y-coordinate of the point
     * @param callback - The callback function to be applied to each traversed segment
     */
    public void traverseFarToNear(double x, double y, SegmentCallback callback) {
        traverseFarToNearRecursive(root_, x, y, callback);
    }

    /**
     * Recursively traverses the BSPTree from far to near based on the given point (x, y)
     *
     * @param currentNode - The current node being considered
     * @param x - The x-coordinate of the point
     * @param y - The y-coordinate of the point
     * @param callback - The callback function to be applied to each traversed segment
     */
    private void traverseFarToNearRecursive(Node currentNode, double x, double y, SegmentCallback callback) {
        if (currentNode == null) {
            return;
        }

        int side = currentNode.segment_.whichSidePoint(x, y);

        if (side > 0) {
            // Point is on the + side, traverse the back subtree first
            traverseFarToNearRecursive(currentNode.back_, x, y, callback);
            callback.callback(currentNode.segment_);
            traverseFarToNearRecursive(currentNode.front_, x, y, callback);
        } else if (side < 0) {
            // Point is on the - side, traverse the front subtree first
            traverseFarToNearRecursive(currentNode.front_, x, y, callback);
            callback.callback(currentNode.segment_);
            traverseFarToNearRecursive(currentNode.back_, x, y, callback);
        } else {
            // Point is on the dividing line, traverse both subtrees
            traverseFarToNearRecursive(currentNode.back_, x, y, callback);
            callback.callback(currentNode.segment_);
            traverseFarToNearRecursive(currentNode.front_, x, y, callback);
        }
    }

    /**
     * Finds the collision between the given query segment and the segments in the BSPTree
     *
     * @param query - The query segment to check for collision
     * @return The colliding segment, or null if no collision is found
     */
    Segment collision(Segment query) {
        return collision(root_, query);
    }

    /**
     * Recursively searches for a collision between the given query segment and the segments in the BSPTree
     *
     * @param currentNode - The current node in the search
     * @param query - The query segment to check for collision
     * @return The colliding segment, or null if no collision is found in this branch of the tree
     */
    private Segment collision(Node currentNode, Segment query) {
        if (currentNode == null) {
            return null;
        }

        int side = query.whichSide(currentNode.segment_);

        if (side < 0) {
            return collision(currentNode.front_, query);
        } else if (side > 0) {
            return collision(currentNode.back_, query);
        } else {
            return collision(currentNode.front_, query) != null ? collision(currentNode.front_, query) : collision(currentNode.back_, query);
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
