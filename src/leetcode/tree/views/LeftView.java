package leetcode.tree.views;

import leetcode.tree.TreeNode;

import java.util.ArrayList;

/**
 * Left View of Binary Tree (GeeksforGeeks)
 * https://www.geeksforgeeks.org/problems/left-view-of-binary-tree/1
 *
 * Given the root of a binary tree, return the left view — the set of nodes
 * visible when the tree is viewed from the left side.
 *
 * Example:
 *   Input:
 *        1
 *      /   \
 *     2     3
 *    / \     \
 *   4   5     6
 *   Output: [1, 2, 4]
 *
 * Approach: DFS with depth tracking
 *   - If list.size() == depth, this is the first node at this depth (leftmost).
 *   - Visit left child before right child to see left side first.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class LeftView {

    public ArrayList<Integer> leftView(TreeNode root) {
        var list = new ArrayList<Integer>();
        dfs(root, list, 0);
        return list;
    }

    public void dfs(TreeNode root, ArrayList<Integer> list, int depth) {
        if (root == null) return;
        if (list.size() == depth) list.add(root.val);
        if (root.left != null) dfs(root.left, list, depth + 1);
        if (root.right != null) dfs(root.right, list, depth + 1);
    }

}
