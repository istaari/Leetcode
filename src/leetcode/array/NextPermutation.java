package leetcode.array;

import java.util.Arrays;


// LeetCode 31: Next Permutation
//
// Problem Statement:
// Implement `next permutation`, which rearranges numbers into the lexicographically next
// greater permutation of numbers.
//
// If such an arrangement is not possible (i.e., the array is already in descending order),
// the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
//
// The replacement must be in-place and use only constant extra memory.
//
// Example 1:
// Input: nums = [1,2,3]
// Output: [1,3,2]
//
// Example 2:
// Input: nums = [3,2,1]
// Output: [1,2,3]
//
// Example 3:
// Input: nums = [1,1,5]
// Output: [1,5,1]

public class NextPermutation {

    /**
     * ### Intuition Behind the Algorithm
     *
     * The problem asks us to find the very next "larger" number we can form by rearranging the
     * digits, just like finding the next word in a dictionary. Let's think about how we would
     * do this manually for a number like `236541`.
     *
     * 1.  **Goal:** To make the number larger, but only by the smallest possible amount. This means we
     * should try to change the rightmost digits first.
     *
     * 2.  **Find the "Pivot":** We scan the number from right to left.
     * - `1`
     * - `41`
     * - `541`
     * - `6541`
     * The sequence `6, 5, 4, 1` is in descending order. We cannot rearrange just this part to make
     * a larger number. The first digit we encounter that is *smaller* than its right neighbor is `3`
     * (because `3 < 6`). This is our **pivot**. This is the digit we need to increase to get the
     * next permutation. Your code finds this pivot and stores its index in `pi`.
     *
     * 3.  **Find the Successor:** We need to replace the pivot (`3`) with the smallest possible digit
     * from the suffix `[6, 5, 4, 1]` that is still *larger* than `3`. Scanning the suffix from
     * right to left, the first number we find that is greater than `3` is `4`. This is our successor.
     * Your code finds this and stores its index in `greater`.
     *
     * 4.  **Swap:** We swap the pivot and its successor.
     * - `2, 3, 6, 5, 4, 1`  --> swap(3, 4) -->  `2, 4, 6, 5, 3, 1`
     *
     * 5.  **Sort the Suffix:** We've now made the prefix of the number larger (`23...` became `24...`).
     * To make the *overall* number the smallest possible next permutation, the suffix that comes
     * after the swapped pivot position must be in its smallest possible order (i.e., sorted ascendingly).
     * - The suffix is `[6, 5, 3, 1]`.
     * - A key observation is that this suffix is *always* in descending order. Therefore, the most
     * efficient way to sort it ascendingly is to simply **reverse** it.
     * - Reversing `[6, 5, 3, 1]` gives `[1, 3, 5, 6]`.
     *
     * 6.  **Final Result:**
     * - `2, 4` + `1, 3, 5, 6`  -->  `[2, 4, 1, 3, 5, 6]`
     *
     * This is the exact sequence of steps your code implements.
     *
     * **Edge Case:** If the entire array is in descending order (e.g., `[3, 2, 1]`), no pivot `pi`
     * will be found (it remains -1). This means we are at the largest possible permutation. The "next"
     * one is the smallest, which is the array sorted in ascending order. Reversing the entire
     * array achieves this.
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;

        // Step 1: Find the first element from the right that is smaller than its next element.
        // This is our "pivot".
        int pi = -1; // pivot index
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pi = i;
                break;
            }
        }

        // Edge Case: If no such element is found, the array is in descending order (e.g., [3,2,1]).
        // This is the largest permutation. The "next" is the smallest (sorted ascending).
        if (pi == -1) {
            reverse(0, nums.length - 1, nums);
            return;
        }

        // Step 2: Find the smallest element in the suffix (to the right of the pivot)
        // that is just larger than the pivot.
        for (int i = nums.length - 1; i > pi; i--) {
            if (nums[i] > nums[pi]) {
                // Step 3: Swap the pivot with this successor element.
                swap(pi, i, nums);
                break;
            }
        }

        // Step 4: Reverse the suffix to make it the smallest possible sequence.
        reverse(pi + 1, nums.length - 1, nums);
    }

    public static void swap(int a, int b, int[] nums) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }

    public static void reverse(int left, int right, int[] nums) {
        while (left < right) {
            swap(left, right, nums);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        // --- Test Cases ---

        // Example 1: Standard case (from the user)
        int[] nums1 = {2, 3, 6, 5, 4, 1};
        System.out.println("Original: " + Arrays.toString(nums1));
        nextPermutation(nums1);
        System.out.println("Next Permutation: " + Arrays.toString(nums1));
        // Expected: [2, 4, 1, 3, 5, 6]

        // Example 2: Simple increasing sequence
        int[] nums2 = {1, 2, 3};
        System.out.println("\nOriginal: " + Arrays.toString(nums2));
        nextPermutation(nums2);
        System.out.println("Next Permutation: " + Arrays.toString(nums2));
        // Expected: [1, 3, 2]

        // Example 3: Descending sequence (the largest permutation)
        int[] nums3 = {3, 2, 1};
        System.out.println("\nOriginal: " + Arrays.toString(nums3));
        nextPermutation(nums3);
        System.out.println("Next Permutation: " + Arrays.toString(nums3));
        // Expected: [1, 2, 3]

        // Example 4: Array with duplicates
        int[] nums4 = {1, 1, 5};
        System.out.println("\nOriginal: " + Arrays.toString(nums4));
        nextPermutation(nums4);
        System.out.println("Next Permutation: " + Arrays.toString(nums4));
        // Expected: [1, 5, 1]
    }
}