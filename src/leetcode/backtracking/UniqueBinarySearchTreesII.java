package leetcode.backtracking;

import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class UniqueBinarySearchTreesII {

    public static void main(String[] args) {
        int n = 3;
        UniqueBinarySearchTreesII uniqueBinarySearchTreesII = new UniqueBinarySearchTreesII();
        System.out.println(uniqueBinarySearchTreesII.generateTrees(n));
    }

    public List<TreeNode> generateTrees(int n) {
        return constructBST(1, n);
    }

    public List<TreeNode> constructBST(int start, int end) {
        List<TreeNode> result = new LinkedList<>();

        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {

            List<TreeNode> leftSubtree = constructBST(start, i - 1);
            List<TreeNode> rightSubtree = constructBST(i + 1, end);

            constructBST(start, i - 1);
            constructBST(i + 1, end);

            for (TreeNode left : leftSubtree) {
                for (TreeNode right : rightSubtree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }


        return result;
    }

}
