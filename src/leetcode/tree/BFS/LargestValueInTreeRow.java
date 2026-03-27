package leetcode.tree.BFS;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 515: Find Largest Value in Each Tree Row
 * https://leetcode.com/problems/find-largest-value-in-each-tree-row/
 *
 * Given the root of a binary tree, return an array of the largest value
 * in each row of the tree (0-indexed).
 *
 * Example 1:
 *   Input: root = [1,3,2,5,3,null,9]
 *   Output: [1,3,9]
 *
 * Example 2:
 *   Input: root = [1,2,3]
 *   Output: [1,3]
 *
 * Constraints:
 *   - The number of nodes is in [0, 10^4]
 *   - -2^31 <= Node.val <= 2^31 - 1
 *
 * Approach: Level-order BFS
 *   - Process each level, track the max value per level.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LargestValueInTreeRow {

    @SuppressWarnings("all")
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                max = Math.max(max, current.val);

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            result.add(max);
        }

        return result;
    }


}
