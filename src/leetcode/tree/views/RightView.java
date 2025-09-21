package leetcode.tree.views;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

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
