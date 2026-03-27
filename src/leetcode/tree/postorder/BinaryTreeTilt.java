package leetcode.tree.postorder;


import leetcode.tree.TreeNode;

/**
 * LeetCode 563: Binary Tree Tilt
 * https://leetcode.com/problems/binary-tree-tilt/
 *
 * Given the root of a binary tree, return the sum of every tree node's tilt.
 * The tilt of a tree node is the absolute difference between the sum of all
 * left subtree node values and all right subtree node values. If a node does
 * not have a left/right child, the sum is 0.
 *
 * Example 1:
 *   Input: root = [1,2,3]
 *   Output: 1 (tilt of 2=0, tilt of 3=0, tilt of 1=|2-3|=1, total=1)
 *
 * Example 2:
 *   Input: root = [4,2,9,3,5,null,7]
 *   Output: 15
 *
 * Constraints:
 *   - The number of nodes is in [0, 10^4]
 *   - -1000 <= Node.val <= 1000
 *
 * Approach: Postorder DFS
 *   - Return subtree sum from each node.
 *   - Accumulate |leftSum - rightSum| into global result.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class BinaryTreeTilt {

    int result = 0;


    // https://leetcode.com/problems/binary-tree-tilt/
    public int helper(TreeNode root) {
        if (root == null)
            return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        result += Math.abs(left - right);

        return left + right + root.val;
    }

    public int findTilt(TreeNode root) {
        helper(root);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(new BinaryTreeTilt().findTilt(root));
    }

}
