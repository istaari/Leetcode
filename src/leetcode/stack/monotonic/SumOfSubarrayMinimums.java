package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;


// Question: Given an array of integers `arr`, find the sum of `min(b)` for every
// contiguous subarray `b` of `arr`. Since the answer may be large, return the answer modulo 10^9 + 7.
//
// Example:
// Input: arr = [3, 1, 2, 4]
// Output: 17
// Explanation:
// Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
// Minimums are   3,   1,   2,   4,    1,     1,     2,      1,       1,       1.
// The sum of the minimums is 3 + 1 + 2 + 4 + 1 + 1 + 2 + 1 + 1 + 1 = 17.
//
// The core idea of the optimized solution is to change our perspective. Instead of:
// "For each subarray, find its minimum and add it to the sum."
// We think:
// "For each number `arr[i]`, in how many subarrays is it the minimum?
//  Then, its total contribution to the sum is `arr[i] * (count of those subarrays)`."

public class SumOfSubarrayMinimums {

    private static final int MOD = 1_000_000_007;

    /**
     * Brute-force approach. This is simple to understand but too slow for large inputs.
     * It generates every possible subarray, finds the minimum of each, and sums them up.
     * Time Complexity: O(n^2)
     * Result: Time Limit Exceeded (TLE) on most platforms.
     */
    public static int sumSubarrayMinsBruteForce(int[] arr) {
        long result = 0;
        int n = arr.length;

        // The outer loop picks the starting point of the subarray.
        for (int i = 0; i < n; i++) {
            int min = arr[i];
            // The inner loop expands the subarray to the right.
            for (int j = i; j < n; j++) {
                // Keep track of the minimum value in the current subarray [i...j].
                min = Math.min(min, arr[j]);

                // Add this minimum to the total result, applying modulo at each step
                // to prevent overflow.
                result = (result + min) % MOD;
            }
        }
        return (int) result;
    }


    public static int sumSubarrayMinsOptimize(int[] nums) {
        int n = nums.length;

        // `prevSmaller[i]` = index of the first element to the left of i that is < nums[i].
        int[] prevSmaller = new int[n];
        // `nextSmaller[i]` = index of the first element to the right of i that is <= nums[i].
        int[] nextSmaller = new int[n];

        Arrays.fill(prevSmaller, -1); // Virtual boundary if no smaller element exists to the left, i+1
        Arrays.fill(nextSmaller, n);  // Virtual boundary if no smaller element exists to the right, n-i

        // A monotonic stack stores indices of elements in increasing order of their values.
        Stack<Integer> stack = new Stack<>();

        // A single pass to calculate both prevSmaller and nextSmaller.
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                nextSmaller[stack.pop()] = i;
            }

            if (!stack.isEmpty()) {
                prevSmaller[i] = stack.peek();
            }

            // Push the current index onto the stack.
            stack.push(i);
        }

        long result = 0;
        // Calculate the contribution of each element.
        for (int i = 0; i < n; i++) {
            // Number of subarrays starting at or after prevSmaller and ending at i.
            long leftBoundary = i - prevSmaller[i];

            // Number of subarrays starting at i and ending at or before nextSmaller.
            long rightBoundary = nextSmaller[i] - i;

            // Total number of subarrays where nums[i] is the minimum.
            long totalSubarrays = leftBoundary * rightBoundary;

            // Add the total contribution of nums[i] to the result.
            long contribution = (totalSubarrays * nums[i]) % MOD;
            result = (result + contribution) % MOD;
        }

        return (int) result;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        System.out.println(sumSubarrayMinsOptimize(nums)); // Expected: 17
    }
}