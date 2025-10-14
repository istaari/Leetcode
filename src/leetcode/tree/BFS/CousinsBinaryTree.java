package leetcode.tree.BFS;

import leetcode.tree.TreeNode;

public class CousinsBinaryTree {

    public TreeNode findParent(TreeNode root, TreeNode parent, int z) {
        if (root == null) return null;

        if (root.val == z) return parent;

        TreeNode left = findParent(root.left, root, z);
        TreeNode right = findParent(root.right, root, z);

        if (left != null) return left;

        return right;
    }

    public int findLevel(TreeNode root, int level, int z) {
        if (root == null) return -1;

        if (root.val == z) return level;


        int left = findLevel(root.left, level + 1, z);
        int right = findLevel(root.right, level + 1, z);

        if (left != -1) return left;

        return right;
    }


    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode x_parent = findParent(root, null, x);
        TreeNode y_parent = findParent(root, null, y);

        if (x_parent == y_parent) return false;

        int x_level = findLevel(root, 0, x);
        int y_level = findLevel(root, 0, y);


        return x_level == y_level;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(new CousinsBinaryTree().isCousins(root, 5, 4));
    }


}

class CousinsBinaryTreeOptimized {
    TreeNode xParent = null;
    TreeNode yParent = null;
    int xDepth = -1, yDepth = -1;

    public boolean isCousins(TreeNode root, int x, int y) {
        getDepthAndParent(root, x, y, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }

    //get both the depth and parent for x and y
    public void getDepthAndParent(TreeNode root, int x, int y, int depth, TreeNode parent) {
        if (root == null) {
            return;
        }
        if (root.val == x) {
            xParent = parent;
            xDepth = depth;
        } else if (root.val == y) {
            yParent = parent;
            yDepth = depth;
        }

        getDepthAndParent(root.left, x, y, depth + 1, root);
        getDepthAndParent(root.right, x, y, depth + 1, root);
    }
}