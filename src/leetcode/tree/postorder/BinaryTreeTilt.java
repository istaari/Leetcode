package leetcode.tree.postorder;


import leetcode.tree.TreeNode;

public class BinaryTreeTilt {

    int result = 0;


    // https://leetcode.com/problems/binary-tree-tilt/
    public int helper(TreeNode root) {
        if (root == null)
            return 0;

        int left = helper(root.left);
        int right = helper(root.right);

        result += Math.abs(left - right);

        return left + right + root.val;
    }

    public int findTilt(TreeNode root) {
        helper(root);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(new BinaryTreeTilt().findTilt(root));
    }

}
