package leetcode.binarySearch;

import java.util.Arrays;

/**
 * LeetCode Problem 162: Find Peak Element
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If the
 * array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * This is a classic "Binary Search on a Monotonic Property" problem. We use the
 * "slope" of the array at the midpoint to decide which half to discard.
 *
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak is 2, or
 * index number 5 where the peak is 6.
 */
public class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        // We use the `low < high` template, which is ideal for converging
        // on a single element without extra checks after the loop.
        while (low < high) {
            int mid = low + (high - low) / 2;

            // Compare the middle element with its right neighbor to determine the "slope".
            if (nums[mid] < nums[mid + 1]) {
                // We are on an "uphill" slope. The peak must be to the right.
                // We can safely discard the left half, including mid.
                low = mid + 1;
            } else {
                // We are on a "downhill" slope. `mid` could be the peak, or the
                // peak is to its left. We can safely discard the right half.
                high = mid;
            }
        }

        // The loop terminates when low == high, which is the index of a peak element.
        // No post-loop processing is needed.
        return low;
    }

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 1 };
        System.out.println("Input: " + Arrays.toString(nums1));
        System.out.println("Output: " + findPeakElement(nums1)); // Expected: 2

        int[] nums2 = { 1, 2, 1, 3, 5, 6, 4 };
        System.out.println("\nInput: " + Arrays.toString(nums2));
        // Output can be 1 or 5, as both 2 and 6 are peaks.
        System.out.println("Output: " + findPeakElement(nums2));
    }
}
