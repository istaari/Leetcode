package leetcode.tree.DFS;

import leetcode.tree.TreeNode;

public class SumOfLeftLeaves {

    // Use marker to mark left leave with no child
    public static int helper(TreeNode root, boolean isLeft) {
        if (root == null) return 0;

        if (isLeft && root.left == null && root.right == null) return root.val;

        int left = helper(root.left, true);
        int right = helper(root.right, false);

        return left + right;
    }

    public static int sumOfLeftLeaves(TreeNode root) {
        return helper(root, false);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(8);
        System.out.println(sumOfLeftLeaves(root));
    }
}
