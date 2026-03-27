package leetcode.tree.pathSumVariations;

import leetcode.tree.TreeNode;

/**
 * LeetCode 124: Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent
 * nodes has an edge. A node can only appear at most once in the path. The path
 * does not need to pass through the root.
 *
 * Return the maximum path sum of any non-empty path.
 *
 * Example 1:
 *   Input: root = [1,2,3]
 *   Output: 6 (path 2 -> 1 -> 3)
 *
 * Example 2:
 *   Input: root = [-10,9,20,null,null,15,7]
 *   Output: 42 (path 15 -> 20 -> 7)
 *
 * Constraints:
 *   - The number of nodes is in [1, 3 * 10^4]
 *   - -1000 <= Node.val <= 1000
 *
 * Approach: Postorder DFS
 *   - At each node, compute the max gain from left and right children (clamp to 0).
 *   - The path through this node = node.val + leftGain + rightGain.
 *   - Update global max with this "arch" path sum.
 *   - Return node.val + max(leftGain, rightGain) for parent to use (single branch only).
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class BinaryTreeMaximumPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    // Returns max gain extending downward from this node (single branch)
    private int maxGain(TreeNode node) {
        if (node == null) return 0;

        // Max gain from left/right children; ignore negative paths
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // Path through this node as the "turning point" (arch)
        int currentPathSum = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, currentPathSum);

        // Return the max single-branch gain for the parent
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        // Example: [-10,9,20,null,null,15,7] -> 42
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(new BinaryTreeMaximumPathSum().maxPathSum(root)); // 42
    }
}
