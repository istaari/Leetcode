package leetcode.tree.BST;

import leetcode.tree.TreeNode;

/**
 * LeetCode 108: Convert Sorted Array to Binary Search Tree
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * Given an integer array nums where the elements are sorted in ascending order,
 * convert it to a height-balanced BST.
 *
 * Example 1:
 *   Input: nums = [-10,-3,0,5,9]
 *   Output: [0,-3,9,-10,null,5] (one possible answer)
 *
 * Example 2:
 *   Input: nums = [1,3]
 *   Output: [3,1] or [1,null,3]
 *
 * Constraints:
 *   - 1 <= nums.length <= 10^4
 *   - -10^4 <= nums[i] <= 10^4
 *   - nums is sorted in strictly increasing order
 *
 * Approach: Binary Search + Divide and Conquer
 *   - Pick middle element as root for balanced tree.
 *   - Recursively build left subtree from left half, right subtree from right half.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(log n) — recursion stack
 */
public class SortedArrayToBinarySearchTree {

    public static TreeNode sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        TreeNode root = helper(nums, left, right);

        return root;
    }

    public static TreeNode helper(int[] nums, int left, int right) {

        if (left > right)
            return null;

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);

        return root;
    }

    public static void main(String[] args) {
        int[] nums = { -10, -3, 0, 5, 9 };
        System.out.println(sortedArrayToBST(nums));
    }
}
