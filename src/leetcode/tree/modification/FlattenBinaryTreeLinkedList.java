package leetcode.tree.modification;

import leetcode.tree.TreeNode;

/**
 * LeetCode 114: Flatten Binary Tree to Linked List
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *   - The linked list should use the same TreeNode class where the right child
 *     pointer points to the next node and the left child pointer is always null.
 *   - The linked list should be in the same order as a pre-order traversal.
 *
 * Example:
 *   Input: root = [1,2,5,3,4,null,6]
 *   Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Constraints:
 *   - The number of nodes is in [0, 2000]
 *   - -100 <= Node.val <= 100
 *
 * Approach: Reverse Preorder (right -> left -> root)
 *   - Process right subtree, then left, then current node.
 *   - Set current node's right to previously processed node (pre).
 *   - Set left to null. This builds the flattened list from tail to head.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h) — recursion stack
 */
public class FlattenBinaryTreeLinkedList {

    TreeNode pre = null;

    // Reverse preorder traversal
    public void flatten(TreeNode root) {
        if (root == null)
            return;

        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    public static void main(String[] args) {
        // Creating nodes
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5, null, node6); // Node 5 has only the right child
        // Node 2 has left child node3 and right child node4
        TreeNode node2 = new TreeNode(2, node3, node4);
        // Root node 1 has left child node2 and right child node5
        TreeNode root = new TreeNode(1, node2, node5);

        new FlattenBinaryTreeLinkedList().flatten(root);
    }
}
