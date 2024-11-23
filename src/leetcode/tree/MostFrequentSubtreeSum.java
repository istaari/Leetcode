package leetcode.tree;

import java.util.*;

@SuppressWarnings("all")
public class MostFrequentSubtreeSum {

    public static int subTreeSum(TreeNode root, int sum, Map<Integer, Integer> map) {
        if (root == null) return 0;

        int left = subTreeSum(root.left, sum, map);
        int right = subTreeSum(root.right, sum, map);

        int currentSum = left + right + root.val;
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);

        return currentSum;
    }


    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        subTreeSum(root, 0,  map);

        int max = 0;
        for (int i : map.values()) {
            max = Math.max(max, i);
        }

        List<Integer> result = new ArrayList<>();
        for (int i : map.keySet()) {
            if (map.get(i) == max) {
                result.add(i);
            }
        }

        return result.stream().mapToInt(x -> x.intValue()).toArray();
    }


}
