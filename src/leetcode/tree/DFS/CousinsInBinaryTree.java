package leetcode.tree.DFS;

import leetcode.tree.TreeNode;

/**
 * LeetCode Problem 993: Cousins in Binary Tree
 * <p>
 * Given the root of a binary tree with unique values, and the values of two
 * different nodes in the tree x and y, return true if the nodes corresponding
 * to the values x and y in the tree are cousins, or false otherwise.
 * <p>
 * Two nodes of a binary tree are cousins if they have the same depth with
 * different parents.
 * <p>
 * This class uses an optimized single-pass DFS approach.
 * <p>
 * Example:
 * Input: root = [1,2,3,4], x = 4, y = 3
 * 1
 * / \
 * 2   3
 * /
 * 4
 * Output: false (Different depths)
 */
public class CousinsInBinaryTree {

    // Class-level variables to store the results from our single traversal.
    private TreeNode xParent = null;
    private TreeNode yParent = null;
    private int xDepth = -1;
    private int yDepth = -1;


    /**
     * Determines if nodes x and y are cousins.
     *
     * @param root The root of the binary tree.
     * @param x    The value of the first node.
     * @param y    The value of the second node.
     * @return true if x and y are cousins, false otherwise.
     */
    public boolean isCousins(TreeNode root, int x, int y) {
        // Start the DFS traversal to find parents and depths.
        findDepthAndParent(root, x, y, 0, null);

        // After the traversal, check the two conditions for being cousins.
        // 1. Depths must be equal.
        // 2. Parents must NOT be the same.
        return xDepth == yDepth && xParent != yParent;
    }

    /**
     * A helper function that traverses the tree to find the depth and parent
     * of both nodes x and y in a single pass.
     *
     * @param node   The current node in the traversal.
     * @param x      The value of the first target node.
     * @param y      The value of the second target node.
     * @param depth  The depth of the current node.
     * @param parent The parent of the current node.
     */
    private void findDepthAndParent(TreeNode node, int x, int y, int depth, TreeNode parent) {
        // Base case: If the node is null, stop this path.
        if (node == null) {
            return;
        }

        // If the current node's value is x, record its parent and depth.
        if (node.val == x) {
            xParent = parent;
            xDepth = depth;
        }
        // If the current node's value is y, record its parent and depth.
        else if (node.val == y) {
            yParent = parent;
            yDepth = depth;
        }

        // Optimization: If we have already found both nodes, we can stop early.
        if (xDepth != -1 && yDepth != -1) {
            return;
        }

        // Continue the traversal on the left and right children.
        // The depth for the children will be `depth + 1`.
        // The parent for the children will be the `node` itself.
        findDepthAndParent(node.left, x, y, depth + 1, node);
        findDepthAndParent(node.right, x, y, depth + 1, node);
    }


    static void main(String[] args) {
        // Construct the tree:
        //      1
        //     / \
        //    2   3
        //     \   \
        //      4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);

        CousinsInBinaryTree solution = new CousinsInBinaryTree();
        // Nodes 4 and 5 are at the same depth (2) and have different parents (2 and 3).
        boolean result = solution.isCousins(root, 5, 4);

        System.out.println("Are nodes 5 and 4 cousins? " + result); // Expected: true
    }

}
