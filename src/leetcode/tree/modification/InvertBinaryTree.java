package leetcode.tree.modification;

import leetcode.tree.TreeNode;

/**
 * LeetCode 226: Invert Binary Tree
 * https://leetcode.com/problems/invert-binary-tree/
 *
 * Given the root of a binary tree, invert the tree, and return its root.
 * Inverting means swapping left and right children at every node.
 *
 * Example 1:
 *   Input: root = [4,2,7,1,3,6,9]
 *   Output: [4,7,2,9,6,3,1]
 *          4              4
 *        /   \    →     /   \
 *       2     7        7     2
 *      / \   / \      / \   / \
 *     1   3 6   9    9   6 3   1
 *
 * Example 2:
 *   Input: root = [2,1,3]
 *   Output: [2,3,1]
 *
 * Constraints:
 *   - The number of nodes is in [0, 100]
 *   - -100 <= Node.val <= 100
 *
 * Approach: Recursive DFS
 *   - At each node, swap left and right children.
 *   - Recursively invert left and right subtrees.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        // Swap children
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeNode inverted = new InvertBinaryTree().invertTree(root);
        System.out.println(inverted.left.val);  // 7
        System.out.println(inverted.right.val); // 2
    }
}
