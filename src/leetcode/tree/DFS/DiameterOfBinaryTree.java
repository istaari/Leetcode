package leetcode.tree.DFS;

import leetcode.tree.TreeNode;

/**
 * LeetCode 543: Diameter of Binary Tree
 * https://leetcode.com/problems/diameter-of-binary-tree/
 *
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter is the length of the longest path between any two nodes. This path
 * may or may not pass through the root. The length is measured by the number of
 * edges between them.
 *
 * Example 1:
 *   Input: root = [1,2,3,4,5]
 *   Output: 3 (path 4 -> 2 -> 1 -> 3 or 5 -> 2 -> 1 -> 3)
 *       1
 *      / \
 *     2   3
 *    / \
 *   4   5
 *
 * Example 2:
 *   Input: root = [1,2]
 *   Output: 1
 *
 * Constraints:
 *   - The number of nodes is in [1, 10^4]
 *   - -100 <= Node.val <= 100
 *
 * Approach: DFS (similar to Binary Tree Maximum Path Sum)
 *   - At each node, the path through it = height(left) + height(right).
 *   - Track the global maximum diameter.
 *   - Return height = 1 + max(height(left), height(right)) for parent.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class DiameterOfBinaryTree {

    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        // Update diameter: path through this node = left + right edges
        diameter = Math.max(diameter, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(new DiameterOfBinaryTree().diameterOfBinaryTree(root)); // 3
    }
}
