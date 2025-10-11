package leetcode.stack.monotonic;

import java.util.Arrays;
import java.util.Stack;


// Question: You are given an integer array `nums`. The range of a subarray is the
// difference between the largest and smallest element in the subarray.
// Return the sum of all subarray ranges.
//
// Example:
// Input: nums = [1, 2, 3]
// Output: 4
// Explanation:
// The subarrays are [1], [2], [3], [1,2], [2,3], [1,2,3].
// Their ranges are:
// [1]: max=1, min=1, range=0
// [2]: max=2, min=2, range=0
// [3]: max=3, min=3, range=0
// [1,2]: max=2, min=1, range=1
// [2,3]: max=3, min=2, range=1
// [1,2,3]: max=3, min=1, range=2
// Sum of ranges = 0 + 0 + 0 + 1 + 1 + 2 = 4
//
// The core idea of the optimized solution is to realize that:
// Sum of all Ranges = (Sum of all subarray maximums) - (Sum of all subarray minimums)
// We can calculate both sums using the contribution method with a monotonic stack.
public class SumOfSubarrayRanges {

    /**
     * Brute-force O(n^2) approach.
     * It iterates through all possible subarrays, finds the min and max of each,
     * and adds their difference to the result.
     * Note: I've corrected a small bug here; the inner loop should start from `j=i`
     * to include single-element subarrays (whose range is 0).
     */
    public static long subArrayRangesBruteForce(int[] nums) {
        long result = 0;
        int n = nums.length;

        // Outer loop picks the start of the subarray.
        for (int i = 0; i < n; i++) {
            int max = nums[i];
            int min = nums[i];
            // Inner loop expands the subarray to the right.
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[j]);
                min = Math.min(min, nums[j]);
                result += (long) max - min;
            }
        }
        return result;
    }

    /**
     * Optimized O(n) solution using a monotonic stack.
     * It calculates the sum of maximums and the sum of minimums separately and finds their difference.
     */
    public static long subArrayRangesStackOptimized(int[] nums) {
        int n = nums.length;

        // Helper arrays to store the boundaries for each element.
        int[] prevSmaller = new int[n];
        int[] prevGreater = new int[n];
        int[] nextSmaller = new int[n];
        int[] nextGreater = new int[n];

        // Initialize with default boundaries.
        Arrays.fill(prevSmaller, -1);
        Arrays.fill(prevGreater, -1);
        Arrays.fill(nextSmaller, n);
        Arrays.fill(nextGreater, n);

        Stack<Integer> stack = new Stack<>();

        // Pass 1: Calculate prevSmaller for all elements.
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                prevSmaller[i] = stack.peek();
            }
            stack.push(i);
        }

        // Pass 2: Calculate prevGreater for all elements.
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

        // Pass 3: Calculate nextSmaller for all elements (iterating from the right).
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            // Using strict inequality here to handle duplicates correctly with prevSmaller.
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                nextSmaller[i] = stack.peek();
            }
            stack.push(i);
        }

        // Pass 4: Calculate nextGreater for all elements (iterating from the right).
        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            // Using strict inequality here to handle duplicates correctly with prevGreater.
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                nextGreater[i] = stack.peek();
            }
            stack.push(i);
        }

        long sumOfMaxes = 0;
        long sumOfMins = 0;

        // Calculate the total contribution of each element to the sum of maxes and mins.
        for (int i = 0; i < n; i++) {
            // Number of subarrays where nums[i] is the MINIMUM.
            long numSubarraysAsMin = (long) (i - prevSmaller[i]) * (nextSmaller[i] - i);
            sumOfMins += numSubarraysAsMin * nums[i];

            // Number of subarrays where nums[i] is the MAXIMUM.
            long numSubarraysAsMax = (long) (i - prevGreater[i]) * (nextGreater[i] - i);
            sumOfMaxes += numSubarraysAsMax * nums[i];
        }

        /**
         * --- The Core Insight: Sum of Ranges = Sum of Maxes - Sum of Mins ---
         *
         * The problem is to find the sum of `(max(s) - min(s))` for every subarray `s`.
         * Using the distributive property of summation, we can re-group the terms:
         *
         * Sum(max(s) - min(s)) over all s
         * = (max(A) - min(A)) + (max(B) - min(B)) + ...
         * = (max(A) + max(B) + ...) - (min(A) + min(B) + ...)
         * = Sum(max(s)) - Sum(min(s))
         *
         * This allows us to solve for the sum of maximums and minimums separately.
         */
        return sumOfMaxes - sumOfMins;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(subArrayRangesStackOptimized(nums)); // Expected: 4
    }
}