package neetcode150.trees;


public class RecoverTree {

    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = null;

    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);

        if (first == null && prev != null && prev.val > root.val) {
            first = prev;
            second = root;
        }

        if (first != null && prev != null && prev.val > root.val) {
            second = root;
        }

        prev = root;
        inorder(root.right);
    }

    public void recoverTree(TreeNode root) {
        inorder(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        new RecoverTree().recoverTree(root);
    }


}
