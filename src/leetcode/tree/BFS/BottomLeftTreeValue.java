package leetcode.tree.BFS;

import leetcode.tree.TreeNode;

import java.util.*;

/**
 * LeetCode 513: Find Bottom Left Tree Value
 * https://leetcode.com/problems/find-bottom-left-tree-value/
 *
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 * Example 1:
 *   Input: root = [2,1,3]
 *   Output: 1
 *
 * Example 2:
 *   Input: root = [1,2,3,4,null,5,6,null,null,7]
 *   Output: 7
 *
 * Constraints:
 *   - The number of nodes is in [1, 10^4]
 *   - -2^31 <= Node.val <= 2^31 - 1
 *
 * Approach: BFS right-to-left
 *   - Process each level right-to-left (add right child before left child).
 *   - The last node processed in the entire BFS is the bottom-left value.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class BottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                assert current != null;
                result = current.val;

                if (current.right != null) queue.add(current.right);
                if (current.left != null) queue.add(current.left);
            }
        }

        return result;
    }

}
