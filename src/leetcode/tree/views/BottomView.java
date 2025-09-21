package leetcode.tree.views;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class BottomView {

    public static ArrayList<Integer> bottomView(TreeNode root) {
        // add your code
        var map = new TreeMap<Integer, Integer>();
        var q = new LinkedList<Pair>();
        var list = new ArrayList<Integer>();
        if (root == null) return list;
        q.add(new Pair(0, root));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            int pos = pair.ind;
            TreeNode node = pair.root;
            if (!map.containsKey(pos)) map.put(pos, node.val);
            if (map.containsKey(pos)) map.replace(pos, node.val);
            if (node.left != null) q.add(new Pair(pos - 1, node.left));
            if (node.right != null) q.add(new Pair(pos + 1, node.right));

        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(entry.getValue());
        }

        return list;
    }


    static class Pair {
        int ind;
        TreeNode root;

        Pair(int _ind, TreeNode _root) {
            ind = _ind;
            root = _root;
        }
    }

}
