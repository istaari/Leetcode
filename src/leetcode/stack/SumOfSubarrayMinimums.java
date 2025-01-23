package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

public class SumOfSubarrayMinimums {

    private static final int MOD = 1_000_000_007;

    // TLE
    public static int sumSubarrayMinsBruteForce(int[] arr) {
        long result = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int min = arr[i];

            for (int j = i; j < n; j++) {
                min = Math.min(min, arr[j]);

                // Modulo Distributive property : (a + b) % MOD = ((a % MOD) + (b % MOD)) % MOD
                result = (result + min) % MOD;
            }
        }

        return (int) result;
    }

    public static int sumSubarrayMinsOptimize(int[] nums) {
        long result = 0;
        int n = nums.length;

        int[] prevSmaller = new int[n];
        int[] nextSmaller = new int[n];

        Arrays.fill(prevSmaller, -1);
        Arrays.fill(nextSmaller, n);


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
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                nextSmaller[i] = stack.peek();
            }

            stack.push(i);
        }


        for (int i = 0; i < n; i++) {
            // Calculates the distance finds total subarrays where the array contributes as min element
            long minContribution = (long) (i - prevSmaller[i]) * (nextSmaller[i] - i);
            result = (result + minContribution * nums[i]) % MOD;
        }

        return (int) result;
    }


    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        System.out.println(sumSubarrayMinsOptimize(nums));
    }
}
