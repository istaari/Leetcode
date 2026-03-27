package leetcode.dp.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. House Robber III
 * https://leetcode.com/problems/house-robber-iii/
 *
 * The thief has found a new place to rob. The houses form a binary tree.
 * The thief cannot rob two directly linked houses (parent-child).
 * Return the maximum amount of money the thief can rob.
 *
 * Example 1:
 *       3
 *      / \
 *     2   3
 *      \   \
 *       3   1
 *   Output: 7  (3 + 3 + 1 = 7, rob root + grandchildren)
 *
 * Example 2:
 *       3
 *      / \
 *     4   5
 *    / \   \
 *   1   3   1
 *   Output: 9  (4 + 5 = 9, rob the two children)
 *
 * Constraints:
 *   The number of nodes is in the range [1, 10^4].
 *   0 <= Node.val <= 10^4
 *
 * ---
 * Approach: Tree DP (post-order DFS)
 *
 * For each node, return a pair [robThis, skipThis]:
 *   robThis:  max money if we ROB this node (can't rob children)
 *   skipThis: max money if we SKIP this node (children free to be robbed or not)
 *
 * TRANSITION:
 *   robThis  = node.val + left[skipThis] + right[skipThis]
 *   skipThis = max(left[robThis], left[skipThis]) + max(right[robThis], right[skipThis])
 *
 * ANSWER: max(root[robThis], root[skipThis])
 *
 * Time:  O(n) — visit each node once
 * Space: O(h) — recursion stack, h = tree height
 */
public class HouseRobber3 {

    // Simple TreeNode definition for this problem
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * Optimal approach: return int[2] = {robThis, skipThis} for each node.
     * No extra hash map needed.
     */
    public static int rob(TreeNode root) {
        int[] result = dfs(root);
        return Math.max(result[0], result[1]);
    }

    /**
     * Post-order DFS: returns [robThisNode, skipThisNode]
     */
    private static int[] dfs(TreeNode node) {
        if (node == null) return new int[]{0, 0};

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Rob this node: can't rob children, take their skip values
        int robThis = node.val + left[1] + right[1];

        // Skip this node: each child can be robbed or skipped (take max of each)
        int skipThis = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{robThis, skipThis};
    }

    /**
     * Alternative: HashMap memoization (less optimal but intuitive)
     *
     * rob(node) = max money we can get from the subtree rooted at node.
     * Two choices:
     *   1. Rob node: node.val + rob(grandchildren)
     *   2. Skip node: rob(left child) + rob(right child)
     */
    public static int robMemo(TreeNode root) {
        return robHelper(root, new HashMap<>());
    }

    private static int robHelper(TreeNode node, Map<TreeNode, Integer> memo) {
        if (node == null) return 0;
        if (memo.containsKey(node)) return memo.get(node);

        // Choice 1: Rob this node + skip children + rob grandchildren
        int robThis = node.val;
        if (node.left != null) {
            robThis += robHelper(node.left.left, memo) + robHelper(node.left.right, memo);
        }
        if (node.right != null) {
            robThis += robHelper(node.right.left, memo) + robHelper(node.right.right, memo);
        }

        // Choice 2: Skip this node, rob children
        int skipThis = robHelper(node.left, memo) + robHelper(node.right, memo);

        int result = Math.max(robThis, skipThis);
        memo.put(node, result);
        return result;
    }

    public static void main(String[] args) {
        // Example 1:     3
        //               / \
        //              2   3
        //               \   \
        //                3   1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(1);
        System.out.println("Example 1 (pair):  " + rob(root1));     // 7
        System.out.println("Example 1 (memo):  " + robMemo(root1)); // 7

        // Example 2:     3
        //               / \
        //              4   5
        //             / \   \
        //            1   3   1
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(1);
        System.out.println("Example 2 (pair):  " + rob(root2));     // 9
        System.out.println("Example 2 (memo):  " + robMemo(root2)); // 9
    }
}
