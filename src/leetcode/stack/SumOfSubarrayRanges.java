package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class SumOfSubarrayRanges {


    public static long subArrayRangesBruteForce(int[] nums) {
        long result = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int max = nums[i]; // for single number it will zero
            int min = nums[i];

            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);

                result += max - min;
            }
        }

        return result;
    }

    public static long subArrayRangesStackOptimized(int[] nums) {
        long result = 0;
        int n = nums.length;

        int[] prevSmaller = new int[n];
        int[] prevGreater = new int[n];
        int[] nextSmaller = new int[n];
        int[] nextGreater = new int[n];

        Arrays.fill(prevSmaller, -1);
        Arrays.fill(prevGreater, -1);
        Arrays.fill(nextSmaller, n);
        Arrays.fill(nextGreater, n);

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                prevSmaller[i] = stack.peek();
            }

            stack.push(i);
        }

        stack.clear();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                prevGreater[i] = stack.peek();
            }

            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                nextSmaller[i] = stack.peek();
            }

            stack.push(i);
        }


        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                nextGreater[i] = stack.peek();
            }

            stack.push(i);
        }


        for (int i = 0; i < n; i++) {
            long minContribution = (long) (i - prevSmaller[i]) * (nextSmaller[i] - i);
            long maxContribution = (long) (i - prevGreater[i]) * (nextGreater[i] - i);
            result += (maxContribution - minContribution) * nums[i];
        }

        return result;
    }


    public static void main(String[] args){
        int[] nums = { 1,2,3 };
        System.out.println(subArrayRangesStackOptimized(nums));
    }
}
