package neetcode150.trees;

import java.util.*;

public class ZigZagTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        boolean isLeft = false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> subList = new ArrayList<>();

            int level = queue.size();

            for (int i = 0; i < level; i++) {
                TreeNode treeNode = queue.poll();
                subList.add(treeNode.val);

                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }

            if (isLeft) {
                Collections.reverse(subList);
                result.add(new ArrayList<>(subList));
                isLeft = false;

            } else {
                result.add(new ArrayList<>(subList));
                isLeft = true;
            }

        }

        return result;
    }
}
