package leetcode.tree.BST;

import leetcode.tree.TreeNode;

public class SortedArrayToBinarySearchTree {

    // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
    // Binary Search and Divide and conquer
    // constructing BST from a sorted array
    public static TreeNode sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        TreeNode root = helper(nums, left, right);

        return root;
    }

    public static TreeNode helper(int[] nums, int left, int right) {

        if (left > right) return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(mid);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);

        return root;
    }

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
    }
}
