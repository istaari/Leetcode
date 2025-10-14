package dsa.tree;

import dsa.trie.TreeNode;

public  class BST {

    private TreeNode root;

    //----------------------- Insertion --------------------------//
    private TreeNode insertHelper(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (key < root.val) {
            root.left = insertHelper(root.left, key); // Fill the new node
        } else if (key > root.val) {
            root.right = insertHelper(root.right, key); // // Fill the new node
        }
        return root; // Return the root, means fill the left and right child of parent node
    }

    // Inserts and return the root
    public void insert(int key) {
        root = insertHelper(root, key);
    }

    //----------------------- Searching --------------------------//
    private boolean containsHelper(TreeNode root, int key) {
        if (root == null) {
            return false;
        }

        if (key == root.val) {
            return true;
        }

        return key < root.val ? containsHelper(root.left, key) : containsHelper(root.right, key);
    }

    // Check if the key is in the tree
    public boolean contains(int key) {
        return containsHelper(root, key);
    }

    //----------------------- Deletion --------------------------//
    private TreeNode deleteHelper(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val)
            root.left = deleteHelper(root.left, key); // If no child,  null is filled, if one node filled either one left or right node
        else if (key > root.val)
            root.right = deleteHelper(root.right, key);
        else {
            // Node with only one child or no child
            if (root.left == null) return root.right;
            else if (root.right == null)  return root.left;

            root.val = inorderSuccessor(root.right); // Replace with Inorder Successor
            root.right = deleteHelper(root.right, root.val); // Delete the inorder successor
        }

        return root;
    }

    private int inorderSuccessor(TreeNode root) {
        int min = root.val;
        while (root.left != null) {
            min = root.left.val;
            root = root.left;
        }
        return min;
    }

    // Deletes the key from the tree
    public void delete(int key) {
        root = deleteHelper(root, key);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = {-10, -3, 0, 5, 9};
        for (int value : values) {
            bst.insert(value);
        }

        bst.delete(3);

        // Check if values exist
        System.out.println("Contains 3: " + bst.contains(3)); // Should print: false
    }

}