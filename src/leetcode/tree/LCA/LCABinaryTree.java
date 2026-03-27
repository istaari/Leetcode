package leetcode.tree.LCA;

import leetcode.tree.TreeNode;

/**
 * LeetCode 236: Lowest Common Ancestor of a Binary Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes.
 * The LCA is the lowest node that has both p and q as descendants (a node can
 * be a descendant of itself).
 *
 * Example 1:
 *   Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *   Output: 3
 *
 * Example 2:
 *   Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 *   Output: 5
 *
 * Constraints:
 *   - The number of nodes is in [2, 10^5]
 *   - -10^9 <= Node.val <= 10^9
 *   - All Node.val are unique
 *   - p != q, both exist in the tree
 *
 * Approach: Recursive DFS
 *   - If current node is null, return null.
 *   - If current node is p or q, return it.
 *   - Recurse on left and right subtrees.
 *   - If both sides return non-null, current node is the LCA (split point).
 *   - Otherwise return whichever side is non-null.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class LCABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: reached null or found one of the targets
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both sides found a target, current node is the LCA
        if (left != null && right != null) {
            return root;
        }

        // Otherwise, return whichever side found something
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        LCABinaryTree lca = new LCABinaryTree();
        System.out.println(lca.lowestCommonAncestor(root, root.left, root.right).val); // 3
        System.out.println(lca.lowestCommonAncestor(root, root.left, root.left.right.right).val); // 5
    }
}
