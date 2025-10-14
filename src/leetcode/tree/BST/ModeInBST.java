package leetcode.tree.BST;

import leetcode.tree.TreeNode;

import java.util.*;

public class ModeInBST {



    // https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
    Map<Integer, Integer> map = new HashMap<>();

    public void helper(TreeNode root) {
        if (root == null) return;

        helper(root.left);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        helper(root.right);
    }

    public int[] findMode(TreeNode root) {
        map.clear();

        helper(root);
        int max = Integer.MIN_VALUE;

        for (int value : map.values()) {
            if (value > max) max = value;
        }

        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                result.add(entry.getKey());
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }


    public static void main(String[] args) {

        ModeInBST modeInBST = new ModeInBST();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(Arrays.toString(modeInBST.findMode(root)));

    }

}
