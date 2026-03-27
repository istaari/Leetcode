package leetcode.tree.BST;

import leetcode.tree.TreeNode;

/**
 * LeetCode 230: Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Given the root of a BST and an integer k, return the kth smallest value
 * (1-indexed) of all the values of the nodes in the tree.
 *
 * Example 1:
 *   Input: root = [3,1,4,null,2], k = 1
 *   Output: 1
 *
 * Example 2:
 *   Input: root = [5,3,6,2,4,null,null,1], k = 3
 *   Output: 3
 *
 * Constraints:
 *   - The number of nodes is n, 1 <= k <= n <= 10^4
 *   - 0 <= Node.val <= 10^4
 *
 * Approach: Inorder Traversal (BST property → sorted order)
 *   - Inorder traversal visits nodes in ascending order.
 *   - Count nodes visited; when count == k, that's the answer.
 *   - Early termination once found.
 *
 * Time Complexity: O(h + k) — traverse to leftmost, then k more
 * Space Complexity: O(h)
 */
public class KthSmallestInBST {

    int count = 0;
    int result = -1;

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return result;
    }

    private void inorder(TreeNode node, int k) {
        if (node == null || count >= k) return;

        inorder(node.left, k);

        count++;
        if (count == k) {
            result = node.val;
            return;
        }

        inorder(node.right, k);
    }

    public static void main(String[] args) {
        // [5,3,6,2,4,null,null,1], k = 3
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        System.out.println(new KthSmallestInBST().kthSmallest(root, 3)); // 3
    }
}
