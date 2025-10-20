package leetcode.tree.DFS;


import leetcode.tree.Node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode Problem 559: Maximum Depth of N-ary Tree
 * <p>
 * Given an n-ary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the
 * root node down to the farthest leaf node.
 * <p>
 * An N-ary tree is a tree in which each node has no more than N children.
 * <p>
 * This file provides two solutions:
 * 1. A recursive Depth-First Search (DFS) approach.
 * 2. An iterative Breadth-First Search (BFS) approach.
 * <p>
 * Example:
 * Input: root = [1,null,3,2,4,null,5,6]
 * This represents the tree:
 *   1
 * / | \
 * 3  2  4
 * / \
 * 5   6
 * <p>
 * Output: 3
 */
public class MaximumDepthOfNaryTree {

    /**
     * Finds the maximum depth of an N-ary tree using a recursive DFS approach.
     * The logic is: the depth of a tree is 1 + the maximum depth among all its children.
     *
     * @param root The root node of the N-ary tree.
     * @return The maximum depth of the tree.
     */
    public static int maxDepthDFS(Node root) {
        // Base case 1: If the tree is empty, its depth is 0.
        if (root == null) {
            return 0;
        }

        // Initialize a variable to keep track of the max depth found among children.
        int maxChildDepth = 0;

        // Recursively find the depth of each child subtree.
        for (Node child : root.children) {
            // Update maxChildDepth with the maximum value found so far.
            maxChildDepth = Math.max(maxChildDepth, maxDepthDFS(child));
        }

        // The depth of the tree rooted at the current node is 1 (for the current node)
        // plus the maximum depth of its children.
        return maxChildDepth + 1;
    }



    /**
     * Finds the maximum depth of an N-ary tree using an iterative BFS approach.
     * The logic is to perform a level-order traversal and count the number of levels.
     *
     * @param root The root node of the N-ary tree.
     * @return The maximum depth of the tree.
     */
    public static int maxDepthBFS(Node root) {
        // Base case: If the tree is empty, its depth is 0.
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        // Continue traversal as long as there are nodes to process.
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level.
            int size = queue.size();

            // Process all nodes at the current level.
            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                // Add all children of the current node to the queue for the next level.
                for (Node child : current.children) {
                    queue.offer(child);
                }
            }

            // After processing all nodes of a level, increment the depth.
            depth++;
        }

        return depth;
    }


    static void main(String[] args) {
        // Manually construct the N-ary tree from the LeetCode example.
        // Tree structure:
        //      1
        //    / | \
        //   3  2  4
        //  / \
        // 5   6
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node3 = new Node(3, Arrays.asList(node5, node6));
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node root = new Node(1, Arrays.asList(node3, node2, node4));

        System.out.println("Finding max depth for the example tree...");

        // Calculate depth using DFS
        int depthFromDFS = maxDepthDFS(root);
        System.out.println("Max depth (using DFS): " + depthFromDFS); // Expected: 3

        // Calculate depth using BFS
        int depthFromBFS = maxDepthBFS(root);
        System.out.println("Max depth (using BFS): " + depthFromBFS); // Expected: 3
    }
}