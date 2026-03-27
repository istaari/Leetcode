package leetcode.tree.LCA;

import leetcode.tree.TreeNode;

/**
 * LeetCode 235: Lowest Common Ancestor of a Binary Search Tree
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * Given a BST, find the lowest common ancestor (LCA) of two given nodes.
 * The LCA is the lowest node that has both p and q as descendants (a node
 * can be a descendant of itself).
 *
 * Example 1:
 *   Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 *   Output: 6
 *
 * Example 2:
 *   Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 *   Output: 2
 *
 * Constraints:
 *   - The number of nodes is in [2, 10^5]
 *   - -10^9 <= Node.val <= 10^9
 *   - All Node.val are unique
 *   - p != q, both exist in the BST
 *
 * Approach: Exploit BST property
 *   - If both p and q are smaller than root, LCA is in left subtree.
 *   - If both are larger, LCA is in right subtree.
 *   - Otherwise, root is the split point = LCA.
 *
 * Time Complexity: O(h)
 * Space Complexity: O(1) iterative, O(h) recursive
 */
public class LCABinarySearchTree {

    // Iterative approach
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;
        while (current != null) {
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            } else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            } else {
                return current; // Split point = LCA
            }
        }
        return null;
    }

    //------------------------------------------------------------------//

    // Recursive approach
    public TreeNode lowestCommonAncestorRecursive(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestorRecursive(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestorRecursive(root.right, p, q);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        LCABinarySearchTree lca = new LCABinarySearchTree();
        System.out.println(lca.lowestCommonAncestor(root, root.left, root.right).val); // 6
        System.out.println(lca.lowestCommonAncestor(root, root.left, root.left.right).val); // 2
    }
}
