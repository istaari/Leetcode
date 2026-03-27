package leetcode.tree.DFS;

import leetcode.tree.TreeNode;

/**
 * LeetCode 404: Sum of Left Leaves
 * https://leetcode.com/problems/sum-of-left-leaves/
 *
 * Given the root of a binary tree, return the sum of all left leaves.
 * A leaf is a node with no children. A left leaf is a leaf that is the
 * left child of another node.
 *
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: 24 (9 + 15)
 *
 * Example 2:
 *   Input: root = [1]
 *   Output: 0
 *
 * Constraints:
 *   - The number of nodes is in [1, 1000]
 *   - -1000 <= Node.val <= 1000
 *
 * Approach: DFS with isLeft marker
 *   - Pass a boolean flag to track if current node is a left child.
 *   - At a leaf node that is a left child, return its value.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
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
