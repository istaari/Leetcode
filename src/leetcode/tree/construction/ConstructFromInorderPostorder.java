package leetcode.tree.construction;

import leetcode.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 106: Construct Binary Tree from Inorder and Postorder Traversal
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * Given two integer arrays inorder and postorder where inorder is the inorder
 * traversal and postorder is the postorder traversal of the same tree, construct
 * and return the binary tree.
 *
 * Example 1:
 *   Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 *   Output: [3,9,20,null,null,15,7]
 *   Tree:
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 *
 * Example 2:
 *   Input: inorder = [-1], postorder = [-1]
 *   Output: [-1]
 *
 * Constraints:
 *   - 1 <= inorder.length <= 3000
 *   - postorder.length == inorder.length
 *   - -3000 <= inorder[i], postorder[i] <= 3000
 *   - All values are unique
 *
 * Approach: Recursive Divide and Conquer (reverse of LC 105)
 *   - Last element of postorder is the root.
 *   - Find root's position in inorder → split into left/right subtrees.
 *   - Traverse postorder from right to left; build RIGHT subtree first
 *     (postorder: left -> right -> ROOT, so reverse = ROOT -> right -> left).
 *   - Use a HashMap for O(1) index lookup in inorder array.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) — HashMap + recursion stack
 */
public class ConstructFromInorderPostorder {

    int postorderIndex;
    Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length - 1;

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int inLeft, int inRight) {
        if (inLeft > inRight) return null;

        // Pick the next element from the end of postorder as root
        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);

        int inorderIdx = inorderMap.get(rootVal);

        // Build RIGHT subtree first (postorder reverse: root -> right -> left)
        root.right = build(postorder, inorderIdx + 1, inRight);
        root.left = build(postorder, inLeft, inorderIdx - 1);

        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = new ConstructFromInorderPostorder().buildTree(inorder, postorder);
        System.out.println(root.val);       // 3
        System.out.println(root.left.val);  // 9
        System.out.println(root.right.val); // 20
    }
}
