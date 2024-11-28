package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementCircular {

    public static int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] nextGreater = new int[arr.length];
        Arrays.fill(nextGreater, -1);
        Stack<Integer> stack = new Stack<>();

        for (int k = 0; k < n * 2; k++) {

            while (!stack.isEmpty() && arr[stack.peek()] < arr[k % n]) {
                nextGreater[stack.pop()] = arr[k % n];
            }

            stack.push(k % n);
        }

        return nextGreater;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        System.out.println(Arrays.toString(nextGreaterElements(nums)));
    }

}
