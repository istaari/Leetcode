package leetcode.tree.BST;

import leetcode.tree.TreeNode;

public class SearchInBST {


    public TreeNode searchBST(TreeNode root, int val) {

        if (root == null) return null;

        if (val == root.val) return root;

        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }

    }


    public TreeNode searchBSTIterative(TreeNode root, int val) {
        TreeNode current = root;

        while (current != null) {

            if (val == current.val) {
                return current;
            }

            if (val < root.val) {
                current = current.left;
            } else {
                current = current.right;
            }

        }

        return null;
    }


}
