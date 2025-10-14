package leetcode.tree.modification;

import leetcode.tree.TreeNode;

public class FlattenBinaryTreeLinkedList {

    TreeNode pre = null;

    // Reverse preorder traversal
    public  void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }



    public static void main(String[] args) {
        // Creating nodes
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5, null, node6); // Node 5 has only the right child
        // Node 2 has left child node3 and right child node4
        TreeNode node2 = new TreeNode(2, node3, node4);
        // Root node 1 has left child node2 and right child node5
        TreeNode root = new TreeNode(1, node2, node5);

        new FlattenBinaryTreeLinkedList().flatten(root);
    }
}
