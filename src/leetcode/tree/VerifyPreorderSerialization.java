package leetcode.tree;

import java.util.LinkedList;
import java.util.Stack;

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
