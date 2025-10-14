package leetcode.tree.BFS;

import leetcode.tree.TreeNode;

import java.util.*;

public class BottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                assert current != null;
                result = current.val;

                if (current.right != null) queue.add(current.right);
                if (current.left != null) queue.add(current.left);
            }
        }

        return result;
    }

}
