package leetcode.tree.DFS;


import leetcode.tree.Node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfNaryTree {


    // https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/
    // recursion return values from above.
    public static int maxDepthDFS(Node root) {

        if (root == null) return 0;

        if (root.children.isEmpty()) return 1;

        int depth = 0;

        for (Node child : root.children) {
            depth = Math.max(depth, maxDepthDFS(child));
        }

        return depth + 1;
    }


    public static int maxDepthBFS(Node root) {
        if (root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                for (Node child : current.children) {
                    queue.offer(child);
                }
            }

            depth++;
        }

        return depth;
    }


    public static void main(String[] args) {
        // Manually constructing the N-ary tree
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node3 = new Node(3, Arrays.asList(node5, node6));
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        // Root node with children 3, 2, 4
        Node root = new Node(1, Arrays.asList(node3, node2, node4));

        System.out.println(maxDepthDFS(root));
    }


}
