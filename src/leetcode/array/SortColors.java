package leetcode.array;

import java.util.Arrays;


// LeetCode 75: Sort Colors
//
// Problem Statement:
// Given an array `nums` with `n` objects colored red, white, or blue, sort them in-place
// so that objects of the same color are adjacent, with the order red, white, and blue.
//
// We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
// You must solve this problem without using the library's sort function.
//
// Example:
// Input: nums = [2,0,2,1,1,0]
// Output: [0,0,1,1,2,2]

public class SortColors {

    /**
     * ### Approach 1: The One-Pass Overwriting Method
     *
     * #### Intuition
     * This is a very clever, but less intuitive, single-pass algorithm that functions like an
     * in-place counting sort.
     *
     * 1.  **The Pointers as Boundaries:** The pointers `n0`, `n1`, `n2` keep track of the end
     * position (or the count) of the `0`, `1`, and `2` sections seen so far.
     *
     * 2.  **The Overwrite Cascade:** The core logic is a cascade of overwrites. When the loop at
     * index `i` finds a number, it assumes the space it now occupies might be needed by a
     * smaller number.
     * - If `nums[i] == 0`: It knows it needs to place a `0`. To make space, it first places a `2`
     * at the end of the known elements, then overwrites it with a `1`, and finally overwrites that
     * with the `0`. This effectively "shifts" the conceptual boundaries of the `1`s and `2`s
     * one step to the right.
     * - If `nums[i] == 1`: It does the same, but only for `2` and `1`.
     * - If `nums[i] == 2`: It just places the `2`.
     *
     * This method is fast but can be difficult to reason about during an interview.
     */
    public static void sortColors(int[] nums, int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                nums[++n2] = 2; // Make space for a 2
                nums[++n1] = 1; // Overwrite with a 1 to make space for a 1
                nums[++n0] = 0; // Place the 0 in its final spot
            } else if (nums[i] == 1) {
                nums[++n2] = 2; // Make space for a 2
                nums[++n1] = 1; // Place the 1
            } else if (nums[i] == 2) {
                nums[++n2] = 2; // Place the 2
            }
        }
    }

    /**
     * ### Approach 2: The Dutch National Flag Algorithm
     *
     * #### Intuition
     * This is the standard, more intuitive in-place swapping algorithm for this problem. It partitions
     * the array into three sections in one pass.
     *
     * 1.  **The Four Sections:** We imagine the array is divided into four sections:
     * - `[ all 0s | all 1s | unknown | all 2s ]`
     *
     * 2.  **The Three Pointers:**
     * - `red` (or `low`): Points to the boundary right after the last `0`.
     * - `white` (or `mid`): The current element being considered. It scans the `unknown` section.
     * - `blue` (or `high`): Points to the boundary right before the first `2`.
     *
     * 3.  **The Logic:** We iterate as long as `white <= blue` (i.e., as long as there is an `unknown` section).
     * - If `nums[white]` is `0`: The `0` is in the wrong place. We swap it with the element at `red`
     * and increment both `red` and `white`.
     * - If `nums[white]` is `1`: The `1` is already in its correct potential section. We just move on by
     * incrementing `white`.
     * - If `nums[white]` is `2`: The `2` is in the wrong place. We swap it with the element at `blue`
     * and decrement `blue`. **Crucially, we do not increment `white`** because the element we just
     * swapped into the `white` position is unknown and needs to be processed.
     */
    public static void sortColors(int[] nums) {
        int red = 0;   // `red` pointer tracks the boundary of the 0s section
        int white = 0; // `white` pointer is the current element under consideration
        int blue = nums.length - 1; // `blue` pointer tracks the boundary of the 2s section

        while (white <= blue) {
            int temp;
            if (nums[white] == 0) {
                // If it's a 0, swap it into the red section
                temp = nums[white];
                nums[white] = nums[red];
                nums[red] = temp;
                white++;
                red++;
            } else if (nums[white] == 1) {
                // If it's a 1, it's in the right place, just move on
                white++;
            } else { // nums[white] == 2
                // If it's a 2, swap it into the blue section
                temp = nums[white];
                nums[white] = nums[blue];
                nums[blue] = temp;
                blue--;
                // Do not increment white, as the new nums[white] needs to be processed
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Testing Both Sort Colors Methods ---");

        int[] originalArray = {2, 0, 2, 1, 1, 0};
        System.out.println("Original Array: " + Arrays.toString(originalArray) + "\n");

        // --- Test for Approach 1 (Overwriting Method) ---
        int[] numsForMethod1 = Arrays.copyOf(originalArray, originalArray.length);
        sortColors(numsForMethod1, numsForMethod1.length);
        System.out.println("Result from Method 1 (Overwriting): " + Arrays.toString(numsForMethod1));
        // Expected: [0, 0, 1, 1, 2, 2]

        // --- Test for Approach 2 (Dutch National Flag) ---
        int[] numsForMethod2 = Arrays.copyOf(originalArray, originalArray.length);
        sortColors(numsForMethod2);
        System.out.println("Result from Method 2 (Dutch Flag):  " + Arrays.toString(numsForMethod2));
        // Expected: [0, 0, 1, 1, 2, 2]
    }
}