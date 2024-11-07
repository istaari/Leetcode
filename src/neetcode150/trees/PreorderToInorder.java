package neetcode150.trees;

public class PreorderToInorder {

    public static TreeNode helper(int[] preorder, int[] inorder, int preRoot, int inStart, int inEnd) {

        if (inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preRoot]);
        int inRoot = -1;

        for (int i = inStart; i <= inEnd; i++) {
            if (preorder[preRoot] == inorder[i]) {
                inRoot = i;
                break;
            }
        }

        root.left = helper(preorder, inorder, preRoot + 1, inStart, inRoot - 1);
        root.right = helper(preorder, inorder, preRoot + (inRoot - inStart) + 1, inRoot + 1, inEnd);

        return root;
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(preorder, inorder, 0, 0, inorder.length - 1);
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println("efe");
    }


}
