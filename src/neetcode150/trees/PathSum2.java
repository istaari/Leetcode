package neetcode150.trees;

import java.util.ArrayList;
import java.util.List;

public class PathSum2 {

    public void helper(List<List<Integer>> result, List<Integer> temp, TreeNode root, int targetSum) {

        if (root == null) return;

        temp.add(root.val);

        targetSum -= root.val;

        if (root.left == null && root.right == null && targetSum == 0) {
            result.add(new ArrayList<>(temp));
        }

        helper(result, temp, root.left, targetSum);
        helper(result, temp, root.right, targetSum);
        temp.removeLast();
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), root, targetSum);
        return result;
    }

}
