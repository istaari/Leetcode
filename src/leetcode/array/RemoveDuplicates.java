package leetcode.array;

import java.util.Arrays;

public class RemoveDuplicates {

    /**
     * ### LeetCode 26: Remove Duplicates from Sorted Array
     *
     * Problem:
     * Given an integer array `nums` sorted in non-decreasing order, remove the duplicates
     * in-place such that each unique element appears only once. The relative order of the
     * elements should be kept the same.
     *
     * Return `k`, the number of unique elements. The first `k` elements of `nums` should
     * hold the final result.
     *
     * ### Intuition Behind the Solution
     *
     * This is a classic "two-pointer" problem, specifically the "fast-runner" or "read/write" pattern.
     *
     * 1.  **The Pointers:**
     * - `j` (the "read" pointer): This pointer iterates through the entire array from beginning to end to inspect every element.
     * - `i` (the "write" pointer): This pointer marks the end of the valid, de-duplicated subarray. It only moves forward when we find a new unique element to add.
     *
     * 2.  **The Invariant:**
     * - At any point during the iteration, the subarray `nums[0...i-1]` contains the processed, unique elements. `nums[i-1]` is the last unique element we have accepted.
     *
     * 3.  **The Core Logic:**
     * - We start `i` and `j` at 1.
     * - We compare the current element `nums[j]` with the last accepted unique element `nums[i-1]`.
     * - If `nums[j]` is **different** from `nums[i-1]`, it means we have found a new unique element. We should add it to our valid subarray.
     * - We do this by placing it at the `i`-th position (`nums[i] = nums[j]`) and then advancing our write pointer (`i++`).
     * - If `nums[j]` is the **same** as `nums[i-1]`, we do nothing but advance `j` to find the next potential unique element.
     *
     * The final value of `i` will be the length of the de-duplicated array.
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        // 'i' is the write pointer. It marks the position for the next unique element.
        int i = 1;
        // 'j' is the read pointer. It scans the array.
        for (int j = 1; j < nums.length; j++) {
            // If we find an element that is different from the last unique element...
            if (nums[j] != nums[i - 1]) {
                // ...place it at the write position and advance the write pointer.
                nums[i] = nums[j];
                i++;
            }
        }

        // 'i' is the length of the unique elements array.
        return i;
    }


    /**
     * ### LeetCode 80: Remove Duplicates from Sorted Array II
     *
     * Problem:
     * Same as before, but this time, remove the duplicates in-place such that each unique
     * element appears **at most twice**.
     *
     * ### Intuition Behind the Solution
     *
     * This is a brilliant generalization of the first problem. The core two-pointer idea remains the same,
     * but the condition for accepting a new element changes.
     *
     * 1.  **The Invariant:**
     * - The subarray `nums[0...i-1]` holds the valid result.
     *
     * 2.  **The Core Logic (The "Look Back by 2" Trick):**
     * - How do we know if we can accept `nums[j]`? We can accept it if it doesn't create a sequence of three or more identical numbers.
     * - The write pointer `i` is where we would place the new element. The valid elements before it are at `i-1` and `i-2`.
     * - If `nums[j]` is the same as both `nums[i-1]` and `nums[i-2]`, it means we already have a pair, and this would be the third duplicate. We should skip it.
     * - Therefore, we can accept `nums[j]` as long as it's **not** the same as the element at `nums[i-2]`.
     * - The condition `nums[j] != nums[i-2]` elegantly covers this. If the subarray is `[..., 5, 5]` and `i` points to the next slot, `i-2` points to the first `5`. If `nums[j]` is also `5`, the condition `5 != 5` is false, and we correctly skip it. If `nums[j]` is `8`, the condition `8 != 5` is true, and we accept it.
     *
     * 3.  **Generalization:** This pattern can be extended. To allow at most `k` duplicates, the condition would be `if (i < k || nums[j] != nums[i - k])`.
     */
    public static int removeDuplicates_2(int[] nums) {
        // If the array has 2 or fewer elements, no duplicates need to be removed.
        if (nums.length <= 2) return nums.length;

        // 'i' is the write pointer. We start at 2 because the first two elements
        // are always valid (even if they are duplicates).
        int i = 2;
        // 'j' is the read pointer, also starting from the third element.
        for (int j = 2; j < nums.length; j++) {
            // Compare the current element `nums[j]` with the element two positions
            // before the current write position `nums[i-2]`.
            // If they are different, it's safe to add `nums[j]` without creating
            // a sequence of more than two duplicates.
            if (nums[j] != nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        // Using a more comprehensive test case
        int[] originalNums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println("Original Array: " + Arrays.toString(originalNums) + "\n");

        // --- Test for Part I (at most ONE occurrence) ---
        int[] numsForPart1 = Arrays.copyOf(originalNums, originalNums.length);
        int k1 = removeDuplicates(numsForPart1);
        System.out.println("Part I (Remove Duplicates):");
        System.out.println("New length: " + k1); // Expected: 4
        System.out.print("Modified Array (first " + k1 + " elements): ");
        for (int i = 0; i < k1; i++) {
            System.out.print(numsForPart1[i] + " "); // Expected: 0 1 2 3
        }
        System.out.println("\n");

        // --- Test for Part II (at most TWO occurrences) ---
        int[] numsForPart2 = Arrays.copyOf(originalNums, originalNums.length);
        int k2 = removeDuplicates_2(numsForPart2);
        System.out.println("Part II (Remove Duplicates II):");
        System.out.println("New length: " + k2); // Expected: 7
        System.out.print("Modified Array (first " + k2 + " elements): ");
        for (int i = 0; i < k2; i++) {
            System.out.print(numsForPart2[i] + " "); // Expected: 0 0 1 1 2 3 3
        }
        System.out.println();
    }
}