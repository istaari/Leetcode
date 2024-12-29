package leetcode.stack;

import java.util.Stack;

public class BaseballGame {

    public static int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for (String operation : operations) {
            char op = operation.charAt(0);

            if (op == 'C') {
                stack.pop();
            } else if (op == 'D') {
                stack.push(stack.peek() * 2);
            } else if (op == '+') {
                int first = stack.pop();
                int second = stack.peek();
                int sum = first + second;

                stack.push(first);
                stack.push(sum);
            } else {
                stack.push(Integer.parseInt(operation));
            }

        }

        int result = 0;

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }


    public static void main(String[] args) {
        String[] ops = {"5", "2", "C", "D", "+"};
        ops = new String[]{"5","-2","4","C","D","9","+","+"};

        System.out.println(calPoints(ops));

    }

}
