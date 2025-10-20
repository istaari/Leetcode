package leetcode.tree.pathSumVariations;


/**
 * LeetCode Problem 112: Path Sum
 *
 * Given the root of a binary tree and an integer targetSum, return true if the
 * tree has a root-to-leaf path such that adding up all the values along the
 * path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 * This solution uses a recursive DFS approach.
 *
 * Example:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Tree:
 *    5
 *  /  \
 * 4    8
 * /   / \
 * 11  13  4
 * /    \    \
 * 7     2    1
 *
 * Output: true (The path 5 -> 4 -> 11 -> 2 sums to 22)
 */
public class PathSum {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Determines if there exists a root-to-leaf path with the given sum.
     *
     * @param root The root of the binary tree.
     * @param targetSum The target sum to find.
     * @return true if such a path exists, false otherwise.
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {

        // **Base Case 1:** If the current node is null, there is no path.
        // This handles empty trees or paths that extend beyond a leaf.
        if (root == null) {
            return false;
        }

        // **Base Case 2:** We have reached a leaf node (no children).
        // This is the end of a potential path. We check if the remaining
        // targetSum is exactly the value of this leaf node.
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // **Recursive Step:** If it's not a leaf node, we continue the search.
        // We subtract the current node's value from the targetSum and
        // ask if a valid path exists in either the left OR the right subtree.
        // If either call returns true, the result is true.
        boolean foundInLeft = hasPathSum(root.left, targetSum - root.val);
        boolean foundInRight = hasPathSum(root.right, targetSum - root.val);

        return foundInLeft || foundInRight;
    }

    public static void main(String[] args) {
        // Construct the tree from the example:
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        PathSum solution = new PathSum();
        int targetSum = 22;

        System.out.println("Does a path with sum " + targetSum + " exist?");
        boolean result = solution.hasPathSum(root, targetSum);

        System.out.println(result); // Expected: true
    }
}
