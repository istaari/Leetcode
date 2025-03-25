package dsa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {

    /**
     * Level order traversal
     **/
    static void BFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode x = queue.poll();
            System.out.print(x.val + " ");
            if (x.left != null) queue.add(x.left);
            if (x.right != null) queue.add(x.right);
        }
    }


    //-----------------------------------------DFS Recursive-----------------------------------//

    /**
     * Depth First Search recursion
     * Preorder
     **/
    static void preorderRecursion(TreeNode node) {
        if (node == null) return;

        System.out.print(node.val + " ");
        preorderRecursion(node.left);
        preorderRecursion(node.right);
    }


    /**
     * Depth-First Search recursion
     * Postorder
     **/
    static void postorderRecursion(TreeNode node) {
        if (node == null) return;

        postorderRecursion(node.left);
        postorderRecursion(node.right);
        System.out.print(node.val + " ");
    }

    /**
     * Depth-First Search recursion
     * Inorder
     **/
    static void inorderRecursion(TreeNode node) {
        if (node == null) return;

        inorderRecursion(node.left);
        System.out.print(node.val + " ");
        inorderRecursion(node.right);
    }


    //-----------------------------------------DFS Iterative-----------------------------------//

    /**
     * Depth-First Search iterative
     * Inorder
     **/
    static void inorderIterative(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.val + " ");
            current = current.right;
        }
    }


    /**
     * Depth First Search iterative
     * Preorder
     **/
    static void preorderIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode x = stack.pop();
            System.out.print(x.val + " ");
            if (x.right != null) stack.add(x.right);
            if (x.left != null) stack.add(x.left);
        }
    }


    /**
     * Depth-First Search iterative
     * Postorder
     **/
    static void postorderIterative(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            output.push(node);

            // Push the left child first, so it's processed after the right child
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }

        while (!output.isEmpty()) {
            System.out.print(output.pop().val + " ");
        }
    }


    public static void main(String[] args) {
        /*
                 5
               /   \
              3     7
             / \   / \
            2   4 6   8

       */
        // Create root node
        TreeNode root = new TreeNode(5);
        // Create left subtree
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        // Create right subtree
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(8);

        System.out.print("Preorder: ");
        preorderIterative(root);
        System.out.print("\n");
        System.out.print("Inorder: ");
        inorderIterative(root);
        System.out.print("\n");
        System.out.print("Postorder: ");
        postorderIterative(root);
    }
}
