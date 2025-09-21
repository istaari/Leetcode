package leetcode.tree.views;

import leetcode.tree.TreeNode;

import java.util.ArrayList;

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
