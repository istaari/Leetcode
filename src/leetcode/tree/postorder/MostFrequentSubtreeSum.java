package leetcode.tree.postorder;

import leetcode.tree.TreeNode;

import java.util.*;

/**
 * LeetCode 508: Most Frequent Subtree Sum
 * https://leetcode.com/problems/most-frequent-subtree-sum/
 *
 * Given the root of a binary tree, return the most frequent subtree sum.
 * The subtree sum of a node is the sum of all node values in its subtree
 * (including itself). If there is a tie, return all values with the highest
 * frequency in any order.
 *
 * Example 1:
 *   Input: root = [5,2,-3]
 *   Output: [2,-3,4]
 *
 * Example 2:
 *   Input: root = [5,2,-5]
 *   Output: [2]
 *
 * Constraints:
 *   - The number of nodes is in [1, 10^4]
 *   - -10^5 <= Node.val <= 10^5
 *
 * Approach: Postorder DFS + HashMap
 *   - Compute subtree sum via postorder: left + right + root.val.
 *   - Store each sum's frequency in a HashMap.
 *   - Find max frequency, collect all sums with that frequency.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
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
