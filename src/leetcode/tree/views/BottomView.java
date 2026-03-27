package leetcode.tree.views;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Bottom View of Binary Tree (GeeksforGeeks)
 * https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
 *
 * Given a binary tree, return the bottom view from left to right.
 * The bottom view contains the last node at each horizontal distance.
 *
 * Example:
 *   Input:
 *          20
 *        /    \
 *      8       22
 *    /   \       \
 *   5     3      25
 *        / \
 *      10   14
 *   Output: [5, 10, 3, 14, 25]
 *
 * Approach: BFS + TreeMap (horizontal distance)
 *   - Assign horizontal distance 0 to root, -1 for left, +1 for right.
 *   - BFS level by level; for each node, overwrite the map entry at its
 *     horizontal distance (last node at that position wins = bottom view).
 *   - TreeMap keeps keys sorted for left-to-right output.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
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
