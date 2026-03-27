package leetcode.tree.modification;

import leetcode.tree.TreeNode;

/**
 * LeetCode 617: Merge Two Binary Trees
 * https://leetcode.com/problems/merge-two-binary-trees/
 *
 * You are given two binary trees root1 and root2. Merge them into a new tree.
 * If two nodes overlap, sum their values. Otherwise, the non-null node is used.
 *
 * Example:
 *   Input: root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
 *   Output: [3,4,5,5,4,null,7]
 *
 * Constraints:
 *   - The number of nodes in both trees is in [0, 2000]
 *   - -10^4 <= Node.val <= 10^4
 *
 * Approach: Recursive DFS (in-place on root1)
 *   - If one tree is null, return the other.
 *   - Otherwise, add root2's value to root1, and recursively merge children.
 *
 * Time Complexity: O(min(m, n)) where m, n are sizes of the two trees
 * Space Complexity: O(min(h1, h2)) — recursion stack
 */
public class MergeTrees {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root2 == null) return root1;
        if (root1 == null) return root2;

        root1.val = root1.val + root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }

}
