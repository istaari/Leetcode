package leetcode.tree.construction;

import leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 105: Construct Binary Tree from Preorder and Inorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder
 * traversal and inorder is the inorder traversal of the same tree, construct
 * and return the binary tree.
 *
 * Example 1:
 *   Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 *   Output: [3,9,20,null,null,15,7]
 *   Tree:
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 *
 * Example 2:
 *   Input: preorder = [−1], inorder = [−1]
 *   Output: [−1]
 *
 * Constraints:
 *   - 1 <= preorder.length <= 3000
 *   - inorder.length == preorder.length
 *   - -3000 <= preorder[i], inorder[i] <= 3000
 *   - All values are unique
 *
 * Approach: Recursive Divide and Conquer
 *   - First element of preorder is the root.
 *   - Find root's position in inorder → elements left of it = left subtree,
 *     right of it = right subtree.
 *   - Use a HashMap for O(1) index lookup in inorder array.
 *   - Recursively build left and right subtrees using index boundaries.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) — HashMap + recursion stack
 */
public class ConstructFromPreorderInorder {

    int preorderIndex = 0;
    Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // Build a value -> index map for inorder array
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(preorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int inLeft, int inRight) {
        if (inLeft > inRight) return null;

        // Pick the next element from preorder as root
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find root's index in inorder to split into left/right subtrees
        int inorderIdx = inorderMap.get(rootVal);

        // Build left subtree first (preorder: root -> LEFT -> right)
        root.left = build(preorder, inLeft, inorderIdx - 1);
        root.right = build(preorder, inorderIdx + 1, inRight);

        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = new ConstructFromPreorderInorder().buildTree(preorder, inorder);
        // root.val = 3, root.left.val = 9, root.right.val = 20
        System.out.println(root.val);       // 3
        System.out.println(root.left.val);  // 9
        System.out.println(root.right.val); // 20
    }
}
