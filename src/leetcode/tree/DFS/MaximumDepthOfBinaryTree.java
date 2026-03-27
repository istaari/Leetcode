package leetcode.tree.DFS;

import leetcode.tree.TreeNode;

/**
 * LeetCode 104: Maximum Depth of Binary Tree
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 *
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: 3
 *
 * Example 2:
 *   Input: root = [1,null,2]
 *   Output: 2
 *
 * Constraints:
 *   - The number of nodes is in [0, 10^4]
 *   - -100 <= Node.val <= 100
 *
 * Approach: Recursive DFS
 *   - Depth = 1 + max(depth(left), depth(right))
 *   - Base case: null node has depth 0.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(new MaximumDepthOfBinaryTree().maxDepth(root)); // 3
    }
}
