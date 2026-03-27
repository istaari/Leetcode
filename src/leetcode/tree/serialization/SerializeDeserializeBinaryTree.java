package leetcode.tree.serialization;

import leetcode.tree.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 297: Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Design an algorithm to serialize and deserialize a binary tree. Serialization
 * is the process of converting a data structure into a sequence of bits.
 * Deserialization is the reverse process.
 *
 * Example 1:
 *   Input: root = [1,2,3,null,null,4,5]
 *   Output: [1,2,3,null,null,4,5]
 *
 * Example 2:
 *   Input: root = []
 *   Output: []
 *
 * Constraints:
 *   - The number of nodes is in [0, 10^4]
 *   - -1000 <= Node.val <= 1000
 *
 * Approach: Preorder DFS
 *   - Serialize: Preorder traversal, use "null" for null nodes, comma-separated.
 *   - Deserialize: Split string by comma, use a Queue to consume tokens in
 *     preorder sequence, reconstructing the tree recursively.
 *
 * Time Complexity: O(n) for both serialize and deserialize
 * Space Complexity: O(n)
 */
public class SerializeDeserializeBinaryTree {

    // Encodes a tree to a single string
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("null,");
            return;
        }
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // Decodes your encoded data to tree
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String val = queue.poll();
        if (val == null || val.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        SerializeDeserializeBinaryTree codec = new SerializeDeserializeBinaryTree();
        String serialized = codec.serialize(root);
        System.out.println(serialized); // "1,2,null,null,3,4,null,null,5,null,null,"

        TreeNode deserialized = codec.deserialize(serialized);
        System.out.println(deserialized.val);       // 1
        System.out.println(deserialized.right.val); // 3
    }
}
