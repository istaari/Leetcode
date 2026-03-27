package leetcode.tree.BST;

import leetcode.tree.TreeNode;

/**
 * LeetCode 98: Validate Binary Search Tree
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Given the root of a binary tree, determine if it is a valid BST.
 * A valid BST is defined as:
 *   - The left subtree of a node contains only nodes with keys < the node's key.
 *   - The right subtree of a node contains only nodes with keys > the node's key.
 *   - Both left and right subtrees must also be valid BSTs.
 *
 * Example 1:
 *   Input: root = [2,1,3]
 *   Output: true
 *
 * Example 2:
 *   Input: root = [5,1,4,null,null,3,6]
 *   Output: false (4 is in right subtree of 5 but 4 < 5... wait, 3 < 5)
 *
 * Constraints:
 *   - The number of nodes is in [1, 10^4]
 *   - -2^31 <= Node.val <= 2^31 - 1
 *
 * Approach 1: Recursive with valid range
 *   - Pass (min, max) bounds down. Each node must be within its valid range.
 *   - Left child gets (min, parent.val), right child gets (parent.val, max).
 *
 * Approach 2: Inorder traversal
 *   - Inorder of valid BST is strictly increasing.
 *   - Track previous value and check each node is greater.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class ValidateBST {

    // Approach 1: Valid range
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;

        if (node.val <= min || node.val >= max) return false;

        return validate(node.left, min, node.val)
                && validate(node.right, node.val, max);
    }

    //------------------------------------------------------------------//

    // Approach 2: Inorder traversal with previous tracking
    TreeNode prev = null;

    public boolean isValidBSTInorder(TreeNode root) {
        if (root == null) return true;

        if (!isValidBSTInorder(root.left)) return false;

        if (prev != null && root.val <= prev.val) return false;
        prev = root;

        return isValidBSTInorder(root.right);
    }

    public static void main(String[] args) {
        // Valid BST: [2,1,3]
        TreeNode valid = new TreeNode(2);
        valid.left = new TreeNode(1);
        valid.right = new TreeNode(3);

        // Invalid BST: [5,1,4,null,null,3,6]
        TreeNode invalid = new TreeNode(5);
        invalid.left = new TreeNode(1);
        invalid.right = new TreeNode(4);
        invalid.right.left = new TreeNode(3);
        invalid.right.right = new TreeNode(6);

        ValidateBST v = new ValidateBST();
        System.out.println(v.isValidBST(valid));   // true
        System.out.println(v.isValidBST(invalid)); // false
    }
}
