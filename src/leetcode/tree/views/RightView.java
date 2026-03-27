package leetcode.tree.views;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 199: Binary Tree Right Side View
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * Given the root of a binary tree, return the values of the nodes you can see
 * ordered from top to bottom when looking from the right side.
 *
 * Example 1:
 *   Input: root = [1,2,3,null,5,null,4]
 *   Output: [1,3,4]
 *
 * Example 2:
 *   Input: root = [1,null,3]
 *   Output: [1,3]
 *
 * Constraints:
 *   - The number of nodes is in [0, 100]
 *   - -100 <= Node.val <= 100
 *
 * Approach: DFS with depth tracking
 *   - Same as LeftView but visit right child before left.
 *   - If list.size() == depth, this is the first (rightmost) node at this depth.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class RightView {

    void dfs(TreeNode root, ArrayList<Integer> list, int depth) {
        if (root == null) return;
        if (list.size() == depth) list.add(root.val);
        if (root.right != null) dfs(root.right, list, depth + 1);
        if (root.left != null) dfs(root.left, list, depth + 1);
    }


    public List<Integer> rightSideView(TreeNode root) {
        var list = new ArrayList<Integer>();
        dfs(root, list, 0);
        return list;
    }

}
