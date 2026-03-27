package leetcode.tree.pathSumVariations;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 113: Path Sum II
 * https://leetcode.com/problems/path-sum-ii/
 *
 * Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where the sum of the node values equals targetSum.
 * Each path should be returned as a list of node values.
 *
 * Example 1:
 *   Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 *   Output: [[5,4,11,2],[5,8,4,5]]
 *
 * Example 2:
 *   Input: root = [1,2,3], targetSum = 5
 *   Output: []
 *
 * Constraints:
 *   - The number of nodes is in [0, 5000]
 *   - -1000 <= Node.val <= 1000
 *   - -1000 <= targetSum <= 1000
 *
 * Approach: DFS Backtracking
 *   - Maintain a current path list. At each node, add its value and subtract from target.
 *   - At a leaf, if remaining target == 0, add a copy of the path to results.
 *   - Backtrack by removing the last element after exploring subtrees.
 *
 * Time Complexity: O(n^2) worst case (copying paths)
 * Space Complexity: O(n) for path + recursion stack
 */
public class PathSum_2 {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int remaining, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;

        path.add(node.val);

        // Leaf node check
        if (node.left == null && node.right == null && remaining == node.val) {
            result.add(new ArrayList<>(path)); // Copy the current path
        }

        dfs(node.left, remaining - node.val, path, result);
        dfs(node.right, remaining - node.val, path, result);

        path.remove(path.size() - 1); // Backtrack
    }

    public static void main(String[] args) {
        // [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        System.out.println(new PathSum_2().pathSum(root, 22));
        // [[5,4,11,2],[5,8,4,5]]
    }
}
