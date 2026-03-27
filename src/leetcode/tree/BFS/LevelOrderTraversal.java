package leetcode.tree.BFS;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 102: Binary Tree Level Order Traversal
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Given the root of a binary tree, return the level order traversal of its
 * nodes' values (i.e., from left to right, level by level).
 *
 * Example 1:
 *   Input: root = [3,9,20,null,null,15,7]
 *   Output: [[3],[9,20],[15,7]]
 *
 * Example 2:
 *   Input: root = [1]
 *   Output: [[1]]
 *
 * Example 3:
 *   Input: root = []
 *   Output: []
 *
 * Constraints:
 *   - The number of nodes is in [0, 2000]
 *   - -1000 <= Node.val <= 1000
 *
 * Approach: BFS with Queue
 *   - Use a queue to process nodes level by level.
 *   - For each level, drain the queue (size = number of nodes at this level).
 *   - Collect values for the level, enqueue children for the next level.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            result.add(level);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(new LevelOrderTraversal().levelOrder(root));
        // [[3], [9, 20], [15, 7]]
    }
}
