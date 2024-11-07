package neetcode150.trees;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointers {

    // Without using constant space
    public static Node connect(Node root) {

        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int level = queue.size();
            Node nextPointer = null;

            for (int i = 0; i < level; i++) {

                Node node = queue.poll();
                node.next = nextPointer;
                nextPointer = node;

                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);

            }
        }

        return root;
    }

    public static void main(String[] args) {
        // Manually create each node
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        // Set up the binary tree structure
        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        System.out.println(connect(node1));


    }
}
