package leetcode.binarySearch;

import java.util.Arrays;

/**
 * LeetCode Problem 34: Find First and Last Position of Element in Sorted Array
 *
 * Given an array of integers `nums` sorted in non-decreasing order, find the
 * starting and ending position of a given `target` value.
 *
 * If `target` is not found in the array, return `[-1, -1]`.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FirstLastOccurrences {

    /**
     * Finds the first occurrence (leftmost boundary) of a target using the "round down" template.
     * This template finds the lower_bound: the first index whose value is >= target.
     */
    private static int findFirstPosition(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2; // Standard mid, rounds down
            if (nums[mid] >= target) {
                // If mid is a potential answer, search in the left half, including mid.
                high = mid;
            } else {
                // Mid is too small, the answer MUST be to the right of mid.
                low = mid + 1;
            }
        }

        // After the loop, `low` and `high` converge to a single candidate.
        // We must verify if this candidate is actually the target.
        return nums[low] == target ? low : -1;
    }

    /**
     * Finds the last occurrence (rightmost boundary) of a target using the "round up" template.
     * This template finds the rightmost index whose value is <= target.
     */
    private static int findLastPosition(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            // CRITICAL: Mid calculation rounds UP to prevent infinite loops when low = mid.
            int mid = low + (high - low + 1) / 2;
            if (nums[mid] <= target) {
                // If mid is a potential answer, search in the right half, including mid.
                low = mid;
            } else {
                // Mid is too large, the answer MUST be to the left of mid.
                high = mid - 1;
            }
        }

        // After the loop, `low` and `high` converge.
        // We must verify if this candidate is the target.
        return nums[low] == target ? low : -1;
    }

     
    public static int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        
        int first = findFirstPosition(nums, target);
        int last = findLastPosition(nums, target);
        return new int[]{first, last};
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println("Searching for " + target + " in " + Arrays.toString(nums));
        System.out.println("Result: " + Arrays.toString(searchRange(nums, target))); // Expected: [3, 4]
    }

}

