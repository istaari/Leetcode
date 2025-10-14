package leetcode.tree.BST;

import leetcode.tree.TreeNode;

public class MinAbsoluteInBST {

    int minDiff = Integer.MAX_VALUE;
    TreeNode prev = null;


    // https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
    // Keeping track of previous value in inorder traversal
    public void helper(TreeNode root) {


        if (root == null) {
            return;
        }

        helper(root.left);

        if (prev != null) {
            minDiff = Math.min(minDiff, Math.abs(root.val - prev.val));
        }

        prev = root;

        helper(root.right);
    }


    public int getMinimumDifference(TreeNode root) {
        helper(root);
        return minDiff;
    }

    public static void main(String[] args) {
        // Constructing the nodes
        TreeNode root = new TreeNode(236);
        TreeNode node104 = new TreeNode(104);
        TreeNode node701 = new TreeNode(701);
        TreeNode node227 = new TreeNode(227);
        TreeNode node911 = new TreeNode(911);

        // Setting up the left and right child relationships
        root.left = node104;
        root.right = node701;
        node104.right = node227;
        node701.right = node911;

        System.out.println(new MinAbsoluteInBST().getMinimumDifference(root));
    }


}
