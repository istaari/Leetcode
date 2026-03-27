package leetcode.tree.serialization;

import java.util.Stack;

/**
 * LeetCode 331: Verify Preorder Serialization of a Binary Tree
 * https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/
 *
 * One way to serialize a binary tree is to use preorder traversal. When we
 * encounter a non-null node, we record the node's value. If it is a null node,
 * we record '#'. Given a string of comma-separated values, verify whether it
 * is a correct preorder serialization of a binary tree (without reconstructing).
 *
 * Example 1:
 *   Input: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 *   Output: true
 *
 * Example 2:
 *   Input: preorder = "1,#"
 *   Output: false
 *
 * Constraints:
 *   - 1 <= preorder.length <= 10^4
 *   - preorder consists of integers in [0, 100] and '#' separated by commas
 *
 * Approach 1: Slot-based (Indegree/Outdegree)
 *   - Start with 1 slot. Each non-null consumes 1 slot, adds 2. Each '#' consumes 1.
 *   - If slots go negative at any point, invalid. Must end with 0 slots.
 *
 * Approach 2: Stack-based
 *   - When top 3 elements are [val, #, #], replace them with '#' (subtree fully consumed).
 *   - Valid if stack ends with a single '#'.
 *
 * Time Complexity: O(n)
 * Space Complexity: O(n) stack-based, O(1) slot-based
 */
public class VerifyPreorderSerialization {

    public boolean slotBased(String preorder) {
        int slot = 1;
        String[] str = preorder.split(",");

        for (String value : str) {
            slot--;

            if (slot < 0) return false;

            if (!value.equals("#")) {
                slot += 2;
            }
        }

        return slot == 0;
    }

    @SuppressWarnings("all")
    public boolean stackBased(String preorder){
        String[] str = preorder.split(",");
        Stack<String> stack = new Stack<>();

        for (String s : str) {
            stack.push(s);

            while (stack.size() >= 3
                    && stack.get(stack.size() - 1).equals("#")
                    && stack.get(stack.size() - 2).equals("#")
                    && !stack.get(stack.size() - 3).equals("#")) {

                stack.pop();
                stack.pop();
                stack.pop();

                stack.push("#");
            }
        }

        return stack.size() == 1 && stack.peek().equals("#");
    }

    public boolean isValidSerialization(String preorder) {
      //return slotBased(preorder);
        return stackBased(preorder);
    }

    public static void main(String[] args){
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        //preorder = "9,#,#,1";
        System.out.println(new VerifyPreorderSerialization().isValidSerialization(preorder));
    }
}
