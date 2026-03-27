package leetcode.tree.BST;

import leetcode.tree.TreeNode;

/**
 * LeetCode 700: Search in a Binary Search Tree
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 *
 * You are given the root of a BST and an integer val. Find the node whose
 * value equals val and return the subtree rooted with that node. If such a
 * node does not exist, return null.
 *
 * Example 1:
 *   Input: root = [4,2,7,1,3], val = 2
 *   Output: [2,1,3]
 *
 * Example 2:
 *   Input: root = [4,2,7,1,3], val = 5
 *   Output: []
 *
 * Constraints:
 *   - Number of nodes in [1, 5000]
 *   - 1 <= Node.val <= 10^7
 *   - root is a valid BST
 *
 * Approach: Recursive + Iterative BST search
 *   - Exploit BST property: go left if val < root, right if val > root.
 *
 * Time Complexity: O(h) where h is tree height
 * Space Complexity: O(h) recursive, O(1) iterative
 */
public class SearchInBST {


    public TreeNode searchBST(TreeNode root, int val) {

        if (root == null) return null;

        if (val == root.val) return root;

        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }

    }


    public TreeNode searchBSTIterative(TreeNode root, int val) {
        TreeNode current = root;

        while (current != null) {

            if (val == current.val) {
                return current;
            }

            if (val < root.val) {
                current = current.left;
            } else {
                current = current.right;
            }

        }

        return null;
    }


}
