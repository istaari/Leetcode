package leetcode.tree.BST;

import leetcode.tree.TreeNode;

/**
 * LeetCode 530: Minimum Absolute Difference in BST
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 *
 * Given the root of a BST, return the minimum absolute difference between
 * the values of any two different nodes in the tree.
 *
 * Example 1:
 *   Input: root = [4,2,6,1,3]
 *   Output: 1
 *
 * Example 2:
 *   Input: root = [1,0,48,null,null,12,49]
 *   Output: 1
 *
 * Constraints:
 *   - The number of nodes is in [2, 10^4]
 *   - 0 <= Node.val <= 10^5
 *
 * Approach: Inorder Traversal with Previous Tracking
 *   - Inorder traversal of BST yields sorted order.
 *   - Track previous node and compute diff with current node at each step.
 *   - Keep the minimum difference.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h) — recursion stack
 */
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
