package leetcode.tree.views;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Top View of Binary Tree (GeeksforGeeks)
 * https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
 *
 * Given a binary tree, return the top view from left to right.
 * The top view contains the first node seen at each horizontal distance.
 *
 * Example:
 *   Input:
 *        1
 *      /   \
 *     2     3
 *    / \   / \
 *   4   5 6   7
 *   Output: [4, 2, 1, 3, 7]
 *
 * Approach: BFS + TreeMap (horizontal distance)
 *   - Assign horizontal distance 0 to root, -1 for left, +1 for right.
 *   - BFS level by level; only store the first node at each horizontal distance.
 *   - TreeMap keeps keys sorted for left-to-right output.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class TopView {

    public static ArrayList<Integer> topView(TreeNode root) {
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
