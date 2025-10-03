package leetcode.array;

import java.util.Arrays;


// LeetCode 1122: Relative Sort Array
//
// Problem Statement:
// Given two arrays, `arr1` and `arr2`. The elements of `arr2` are distinct, and every
// element in `arr2` is also in `arr1`.
//
// Sort the elements of `arr1` such that the relative ordering of items in `arr1` is the
// same as the relative ordering of items in `arr2`. Elements that do not appear in `arr2`
// should be placed at the end of `arr1` in ascending order.
//
// Constraints: The values in the arrays will be between 0 and 1000.
//
// Example:
// Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
// Output: [2,2,2,1,4,3,3,9,6,7,19]

public class RelativeSortArray {

    /**
     * ### Intuition Behind the Solution (Counting Sort)
     *
     * 1.  **The Constraint is a Big Hint:** The problem states that the numbers in the arrays are
     * within a limited range (0 to 1000). This is a classic signal to use a non-comparison-based
     * sorting algorithm like **Counting Sort**, which can be much faster (linear time) than
     * `O(N log N)` algorithms like QuickSort or MergeSort.
     *
     * 2.  **The Core Idea:** We need to handle two groups of numbers separately:
     * - Group A: Numbers that are present in `arr2`. These must be ordered according to `arr2`.
     * - Group B: Numbers that are *not* in `arr2`. These must be sorted ascendingly at the end.
     *
     * 3.  **The Algorithm's Plan (A Three-Phase Approach):**
     * The code cleverly accomplishes this in three distinct phases.
     *
     * - **Phase 1: Frequency Counting.**
     * - We can't sort or rearrange anything until we know how many of each number we have in `arr1`.
     * - We create a "counting" array (or frequency map) of size 1001. `counting[x]` will store the
     * number of times `x` appears in `arr1`.
     * - We make a single pass through `arr1` to populate these counts.
     *
     * - **Phase 2: Enforce Relative Order.**
     * - Now, we build the first part of our result. We iterate through `arr2` (e.g., `[2, 1, 4, ...]`).
     * - For the first element `2`, we look up its count. Let's say it's 3. We then place three `2`s
     * at the beginning of `arr1`.
     * - For the next element `1`, we look up its count and place that many `1`s right after the `2`s.
     * - We continue this for all elements in `arr2`. A write-pointer `index` keeps track of where to place the next number.
     * - An important step here is to "consume" the counts. After placing three `2`s, we set `counting[2]` to `0` so they aren't processed again in the next phase.
     *
     * - **Phase 3: Append Remaining Elements.**
     * - At this point, the `counting` array only has non-zero counts for numbers that were in `arr1` but not `arr2`.
     * - The problem requires these to be sorted in ascending order.
     * - We can achieve this simply by iterating through our `counting` array from index `0` to `1000`.
     * - If `counting[i]` is greater than zero, we append `i` to our result array `arr1` that many times. Since we are iterating in ascending order of `i`, the remaining elements are automatically sorted.
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Phase 1: Count the frequency of each number in arr1.
        // The constraint `0 <= arr1[i] <= 1000` allows this efficient array-based counting.
        int[] counting = new int[1001];
        for (int val : arr1) {
            counting[val]++;
        }

        // `index` will be our write-pointer for modifying arr1 in-place.
        int index = 0;

        // Phase 2: Place all elements from arr2 into arr1 in the correct relative order.
        for (int val : arr2) {
            // Place the element 'val' as many times as it was counted.
            while (counting[val] > 0) {
                arr1[index] = val;
                index++;
                counting[val]--; // Decrement the count to mark it as "used".
            }
        }

        // Phase 3: Place all remaining elements (those not in arr2) at the end.
        // We iterate through the entire possible range of numbers (0 to 1000).
        for (int i = 0; i < counting.length; i++) {
            // If the count for number 'i' is still positive, it means it was not in arr2.
            while (counting[i] > 0) {
                arr1[index] = i;
                index++;
                counting[i]--;
            }
        }

        return arr1;
    }


    public static void main(String[] args) {
        // Example 1: The user's original example
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        System.out.println("Original arr1: " + Arrays.toString(arr1));
        System.out.println("Relative order arr2: " + Arrays.toString(arr2));
        System.out.println("Sorted arr1: " + Arrays.toString(relativeSortArray(arr1, arr2)));
        // Expected: [2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19]
        // Explanation:
        // - All three '2's come first, as 2 is first in arr2.
        // - Then the single '1', as 1 is second in arr2.
        // - And so on for 4, 3, 9, 6.
        // - The remaining numbers (7, 19) are placed at the end in ascending order.
    }
}