package leetcode.tree.DFS;

import leetcode.tree.TreeNode;

/**
 * LeetCode 1022: Sum of Root To Leaf Binary Numbers
 * https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/
 *
 * You are given the root of a binary tree where each node has a value 0 or 1.
 * Each root-to-leaf path represents a binary number starting with the most
 * significant bit. Return the sum of these numbers.
 *
 * Example 1:
 *   Input: root = [1,0,1,0,1,0,1]
 *   Output: 22 (paths: 100=4, 101=5, 110=6, 111=7 -> 22)
 *
 * Example 2:
 *   Input: root = [0]
 *   Output: 0
 *
 * Constraints:
 *   - The number of nodes is in [1, 1000]
 *   - Node.val is 0 or 1
 *
 * Approach 1: DFS with String concatenation
 *   - Build binary string along path, parse at leaf.
 *
 * Approach 2: DFS with Horner's method (optimal)
 *   - sum = 2 * sum + node.val at each step.
 *   - At leaf, return accumulated sum.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */
public class SumOfRootToLeafBinaryNumbers {

    String bits = "";

    int sum = 0;


    // Binary to decimal can be converted using Horner's method.
    //  Horner's method converts single MSB bit into decimal
    public void helper(TreeNode root) {
        if (root == null) return;

        bits = bits + root.val;

        if (root.left == null && root.right == null) {
            sum += Integer.parseInt(bits, 2);
        }

        helper(root.left);
        helper(root.right);

        bits = bits.substring(0, bits.length() - 1);
    }

    public int sumRootToLeaf(TreeNode root) {
        helper(root);
        return sum;
    }


    /**
     * Horner's method, 100
     * <p>
     * Sum = 0;
     * <p>
     * 1. sum = 1 + (2 * sum = 0) = 1
     * 2. sum = 0 + (2 * sum = 1) = 2
     * 3. sum = 0 + (2 * sum = 2) = 4
     *
     *
     **/

    /*
    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeaf(root, 0);
    }

    public int sumRootToLeaf(TreeNode root, int sum){
        if(root == null) return 0;
        sum = (2 * sum) + root.val;
        if(root.left == null && root.right == null) return sum;
        return sumRootToLeaf(root.left, sum) + sumRootToLeaf(root.right, sum);
    }
     */


    public static void main(String[] args) {
        // Creating each node
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(0);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(1);

        // Connecting nodes according to the structure [1,0,1,0,1,0,1]
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        System.out.println(new SumOfRootToLeafBinaryNumbers().sumRootToLeaf(node1));
    }


}
