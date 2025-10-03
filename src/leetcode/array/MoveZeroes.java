package leetcode.array;

import java.util.Arrays;


// LeetCode 283: Move Zeroes
//
// Problem Statement:
// Given an integer array `nums`, move all 0's to the end of it while maintaining the
// relative order of the non-zero elements.
//
// Note that you must do this in-place without making a copy of the array.
//
// Example:
// Input: nums = [0,1,0,3,12]
// Output: [1,3,12,0,0]

public class MoveZeroes {

    /**
     * Moves all zeroes to the end of the array in-place, maintaining the relative
     * order of non-zero elements.
     *
     * This refactored solution uses the "Snowplow" or "Write Pointer" method.
     *
     * 1. A pointer `insertPosition` tracks the next valid spot for a non-zero element.
     * 2. We iterate through the array. When we find a non-zero element, we place it
     * at `insertPosition` and increment `insertPosition`.
     * 3. After this first pass, all non-zero elements are at the front.
     * 4. A second pass fills the rest of the array with zeroes.
     *
     * This approach is clearer and minimizes the number of write operations.
     *
     * @param nums The array to be modified.
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int insertPosition = 0;

        // Pass 1: Move all non-zero elements to the front of the array.
        for (int num : nums) {
            if (num != 0) {
                nums[insertPosition] = num;
                insertPosition++;
            }
        }

        // Pass 2: Fill the remaining part of the array with zeroes.
        while (insertPosition < nums.length) {
            nums[insertPosition] = 0;
            insertPosition++;
        }
    }

    public static void main(String[] args) {
        // Your original test case
        int[] nums1 = {0, 1, 0, 3, 12};
        moveZeroes(nums1);
        System.out.println("For [0, 1, 0, 3, 12], result is: " + Arrays.toString(nums1));
        // Expected: [1, 3, 12, 0, 0]
    }
}