package leetcode.tree.pathSumVariations;

import leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 437: Path Sum III
 * https://leetcode.com/problems/path-sum-iii/
 *
 * Given the root of a binary tree and an integer targetSum, return the number
 * of paths where the values along the path sum to targetSum. The path does not
 * need to start at the root or end at a leaf, but it must go downwards
 * (traveling only from parent to child nodes).
 *
 * Example 1:
 *   Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 *   Output: 3
 *   Paths: 5->3, 5->2->1, -3->11
 *
 * Example 2:
 *   Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 *   Output: 3
 *
 * Constraints:
 *   - The number of nodes is in [0, 1000]
 *   - -10^9 <= Node.val <= 10^9
 *   - -1000 <= targetSum <= 1000
 *
 * Approach: Prefix Sum + DFS (similar to subarray sum equals K)
 *   - Maintain a running prefix sum from root to current node.
 *   - Use a HashMap to store frequency of each prefix sum seen so far.
 *   - At each node, check if (currentSum - targetSum) exists in map.
 *   - Backtrack: remove current prefix sum from map after exploring subtrees.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class PathSum_3 {

    int count = 0;

    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0L, 1); // Base case: empty path
        dfs(root, 0L, targetSum, prefixSumMap);
        return count;
    }

    private void dfs(TreeNode node, long currentSum, int targetSum, Map<Long, Integer> prefixSumMap) {
        if (node == null) return;

        currentSum += node.val;

        // Check how many paths ending here sum to targetSum
        count += prefixSumMap.getOrDefault(currentSum - targetSum, 0);

        // Add current prefix sum to map
        prefixSumMap.put(currentSum, prefixSumMap.getOrDefault(currentSum, 0) + 1);

        dfs(node.left, currentSum, targetSum, prefixSumMap);
        dfs(node.right, currentSum, targetSum, prefixSumMap);

        // Backtrack: remove current prefix sum
        prefixSumMap.put(currentSum, prefixSumMap.get(currentSum) - 1);
    }

    public static void main(String[] args) {
        // [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println(new PathSum_3().pathSum(root, 8)); // 3
    }
}
