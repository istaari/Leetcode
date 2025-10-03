package leetcode.array;

import java.util.Arrays;


// LeetCode 189: Rotate Array
//
// Problem Statement:
// Given an integer array `nums`, rotate the array to the right by `k` steps, where `k` is non-negative.
// The rotation should be done in-place with O(1) extra space.
//
// Example 1:
// Input: nums = [1,2,3,4,5,6,7], k = 3
// Output: [5,6,7,1,2,3,4]
// Explanation:
// rotate 1 step: [7,1,2,3,4,5,6]
// rotate 2 steps: [6,7,1,2,3,4,5]
// rotate 3 steps: [5,6,7,1,2,3,4]
//
// Example 2:
// Input: nums = [-1,-100,3,99], k = 2
// Output: [3,99,-1,-100]

public class RotateArrayKTimes {

    /**
     * Rotates the array `nums` to the right by `k` steps in-place.
     *
     * #### Concrete Example: `nums = [1,2,3,4,5,6,7]`, `k = 3`
     * - `A = [1,2,3,4]`, `B = [5,6,7]`
     * - **Initial:** `[1, 2, 3, 4, | 5, 6, 7]`
     * - **Step 1 (Reverse All):** `[7, 6, 5, | 4, 3, 2, 1]`
     * - **Step 2 (Reverse First k=3):** `[5, 6, 7, | 4, 3, 2, 1]`
     * - **Step 3 (Reverse Remaining):** `[5, 6, 7, | 1, 2, 3, 4]`
     * - **Final Result:** `[5, 6, 7, 1, 2, 3, 4]`
     */
    public static void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return;
        }

        // Ensure k is within array bounds. Rotating by length is the same as no rotation.
        k %= nums.length;

        // Step 1: Reverse the entire array
        reverse(nums, 0, nums.length - 1);

        // Step 2: Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Step 3: Reverse the remaining n-k elements
        reverse(nums, k, nums.length - 1);
    }


    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {

        // Example 1: Standard case
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;
        rotate(nums1, k1);
        System.out.println("Rotating [1,2,3,4,5,6,7] by 3 gives: " + Arrays.toString(nums1));
        // Expected: [5, 6, 7, 1, 2, 3, 4]
    }
}