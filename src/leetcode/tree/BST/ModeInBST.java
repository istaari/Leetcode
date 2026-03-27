package leetcode.tree.BST;

import leetcode.tree.TreeNode;

import java.util.*;

/**
 * LeetCode 501: Find Mode in Binary Search Tree
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/
 *
 * Given the root of a BST with duplicates, return all the mode(s)
 * (i.e., the most frequently occurred element).
 *
 * Example 1:
 *   Input: root = [1,null,2,2]
 *   Output: [2]
 *
 * Example 2:
 *   Input: root = [0]
 *   Output: [0]
 *
 * Constraints:
 *   - The number of nodes is in [1, 10^4]
 *   - -10^5 <= Node.val <= 10^5
 *
 * Approach: Inorder Traversal + Frequency Map
 *   - Inorder traversal to visit all nodes.
 *   - Build a frequency map, then find the max frequency and collect all keys with that frequency.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
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
