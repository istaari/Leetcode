package leetcode.tree.BST;

import leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 653: Two Sum IV - Input is a BST
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 *
 * Given the root of a binary search tree and an integer k, return true if there
 * exist two elements in the BST such that their sum is equal to k.
 *
 * Example 1:
 *   Input: root = [5,3,6,2,4,null,7], k = 9
 *   Output: true
 *
 * Example 2:
 *   Input: root = [5,3,6,2,4,null,7], k = 28
 *   Output: false
 *
 * Constraints:
 *   - The number of nodes is in [1, 10^4]
 *   - -10^4 <= Node.val <= 10^4
 *   - root is a valid BST
 *   - -10^5 <= k <= 10^5
 *
 * Approach: DFS + Complement List
 *   - For each node, check if (k - node.val) was seen before.
 *   - Store complements in a list during DFS traversal.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class FindTarget {

    public static boolean helper(TreeNode root, int k, List<Integer> remaining) {
        if (root == null) return false;

        if(remaining.contains(root.val)) return true;

        remaining.add(k - root.val);

        return helper(root.left, k ,remaining) || helper(root.right, k, remaining);
    }

    public static boolean findTarget(TreeNode root, int k) {
        return helper(root, k, new ArrayList<>());
    }

    public static void main(String[] args) {
        int k = 9;
        // Creating nodes manually
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println(findTarget(root, k));
    }


}
