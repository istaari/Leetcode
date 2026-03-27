package leetcode.tree.DFS;

import leetcode.tree.TreeNode;

/**
 * LeetCode 100: Same Tree
 * https://leetcode.com/problems/same-tree/
 *
 * Given the roots of two binary trees p and q, check if they are the same or not.
 * Two binary trees are the same if they are structurally identical and the nodes
 * have the same value.
 *
 * Example 1:
 *   Input: p = [1,2,3], q = [1,2,3]
 *   Output: true
 *
 * Example 2:
 *   Input: p = [1,2], q = [1,null,2]
 *   Output: false
 *
 * Constraints:
 *   - The number of nodes in both trees is in [0, 100]
 *   - -10^4 <= Node.val <= 10^4
 *
 * Approach: Recursive DFS
 *   - Both null → same. One null → not same.
 *   - Values must match, and left/right subtrees must recursively be the same.
 *
 * Time Complexity: O(min(m, n))
 * Space Complexity: O(min(h1, h2))
 */
public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return (p.val == q.val)
                && isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);

        System.out.println(new SameTree().isSameTree(p, q)); // true

        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(2);

        TreeNode s = new TreeNode(1);
        s.right = new TreeNode(2);

        System.out.println(new SameTree().isSameTree(r, s)); // false
    }
}
