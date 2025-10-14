package leetcode.tree.DFS;

import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {



    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            if (left == null && right == null) continue;
            if (left == null || right == null || left.val != right.val) return false;
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);

        }
        return true;
    }

    //----------------------------------------------------------------------------------//

    public static boolean isSymmetricRecursion(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }

    // Comparing two subtree parallels
    public static boolean helper(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        return (p.val == q.val) && helper(p.left, q.right) && helper(p.right, q.left);
    }

    public static void main(String[] args) {
        // Creating nodes
        TreeNode node3Left = new TreeNode(3);
        TreeNode node4Left = new TreeNode(4);
        TreeNode node4Right = new TreeNode(4);
        TreeNode node3Right = new TreeNode(3);

        TreeNode node2Left = new TreeNode(2, node3Left, node4Left);
        TreeNode node2Right = new TreeNode(2, node4Right, node3Right);

        TreeNode root = new TreeNode(1, node2Left, node2Right);

        System.out.println(isSymmetric(root));

    }
}
