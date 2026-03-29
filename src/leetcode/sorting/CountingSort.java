package leetcode.sorting;

import java.util.Arrays;

public class CountingSort {

    /**
     * Sorts an array of non-negative integers using the Counting Sort algorithm.
     * This is an efficient, non-comparison based sorting algorithm best suited
     * when the range of input numbers (k) is not significantly larger than the
     * number of elements (n).
     *
     * Time Complexity: O(n + k)
     * Space Complexity: O(n + k)
     *
     * --------------------------------------------------------------------------------
     * WALKTHROUGH STEPS WITH EXAMPLE: nums = {4, 2, 2, 8, 3, 3, 1}
     * --------------------------------------------------------------------------------
     *
     * 1. Find Maximum Element:
     * - Action: Determine the maximum value in the input array to set the size of the 'count' array.
     * - Example: max = 8
     *
     * 2. Initialize and Populate Count Array (Frequency Count):
     * - Action: Create a 'count' array of size (max + 1) and store the frequency of each element.
     * - Example: count[9]
     * - Initial: count = [0, 1, 2, 2, 1, 0, 0, 0, 1]
     * (Index 0: 0, Index 1: 1, Index 2: 2, Index 3: 2, Index 4: 1, ..., Index 8: 1)
     *
     * 3. Modify Count Array (Cumulative Sum):
     * - Action: Convert the 'count' array into a cumulative sum array. Each element
     * now stores the final *position* (index + 1) of its corresponding value
     * in the sorted output array.
     * - Example:
     * - i=1: count[1] = 1 + 0 = 1 (Position of last '1' is index 0)
     * - i=2: count[2] = 2 + 1 = 3 (Position of last '2' is index 2)
     * - i=3: count[3] = 2 + 3 = 5 (Position of last '3' is index 4)
     * - i=4: count[4] = 1 + 5 = 6 (Position of last '4' is index 5)
     * - i=8: count[8] = 1 + 6 = 7 (Position of last '8' is index 6)
     * - Final Cumulative: count = [0, 1, 3, 5, 6, 6, 6, 6, 7]
     *
     * 4. Build Result Array (Stable Placement):
     * - Action: Iterate through the *original* 'nums' array.
     * a. Get the correct sorted position from the 'count' array: index = count[num] - 1.
     * b. Place the element in the 'result' array at that index.
     * c. Decrement count[num] to ensure the next identical element goes to the preceding position.
     * - Example:
     * - num=4: index=count[4]-1 = 6-1 = 5. result[5]=4. count[4]=5.
     * - num=2: index=count[2]-1 = 3-1 = 2. result[2]=2. count[2]=2.
     * - num=2: index=count[2]-1 = 2-1 = 1. result[1]=2. count[2]=1.
     * - ... and so on.
     *
     * 5. Return Result:
     * - Final: result = [1, 2, 2, 3, 3, 4, 8]
     */
    public static int[] countingSort(int[] nums) {
        int max = 0;

        // Step 1: Find Maximum Element
        for (int val : nums) {
            max = Math.max(max, val);
        }

        // Step 2: Initialize and Populate Count Array (Frequency Count)
        int[] count = new int[max + 1];
        for (int val : nums) {
            count[val]++;
        }

        // Step 3: Modify Count Array (Cumulative Sum)
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        // Step 4: Build Result Array (Stable Placement)
        int[] result = new int[nums.length];
        
        // Iterate backward or forward to maintain stability. Forward iteration requires a slight
        // change in the setup, but using the original forward loop as given in the prompt is fine
        // for stability if the loop is adjusted OR if we iterate backward through the input array.
        // To maintain the provided code structure (forward loop over nums), we rely on the
        // placement logic to ensure stability for equal elements:
        
        for (int i = nums.length - 1; i >= 0; i--) {
             int num = nums[i];
             int index = count[num] - 1; // Get the correct sorted position
             result[index] = num;        // Place the element
             count[num]--;               // Decrement the count for the next identical element
        }

        // Step 5: Return Result
        return result;
    }

    public static void main(String[] args) {
        int[] nums = { 4, 2, 2, 8, 3, 3, 1 };
        System.out.println("Original Array: " + Arrays.toString(nums));
        System.out.println("Sorted Array:   " + Arrays.toString(countingSort(nums)));
    }
}