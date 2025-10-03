package leetcode.array;

import java.util.ArrayList;
import java.util.List;


// LeetCode 229: Majority Element II
//
// Problem Statement:
// Given an integer array `nums` of size `n`, find all the elements that appear more than
// `⌊ n/3 ⌋` times.
//
// Note: The algorithm should run in linear time and in O(1) space.
//
// Example 1:
// Input: nums = [3,2,3]
// Output: [3]
//
// Example 2:
// Input: nums = [1]
// Output: [1]
//
// Example 3:
// Input: nums = [1,2]
// Output: [1,2]
public class MajorityElement {

    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        // --- PASS 1: Find the two potential candidates ---
        int count1 = 0;
        // Initialize with values that are guaranteed not to be in the array if possible,
        // but the logic handles this. Using 0 and 1 is fine as placeholders.
        int candidate1 = 0;

        int count2 = 0;
        int candidate2 = 1;

        // This is a slightly different but valid implementation of the voting logic.
        // A more common pattern is a single if-else-if cascade.
        for (int val : nums) {
            if (candidate1 == val) {
                count1++;
            } else if (candidate2 == val) {
                count2++;
            } else if (count1 == 0) {
                candidate1 = val;
                count1 = 1;
            } else if (count2 == 0) {
                candidate2 = val;
                count2 = 1;
            } else {
                // This is the "cancellation" step for a trio of distinct numbers.
                count1--;
                count2--;
            }
        }

        // --- PASS 2: Verify the candidates ---
        count1 = 0;
        count2 = 0;

        for (int val : nums) {
            if (val == candidate1) count1++;
            else if (val == candidate2) count2++;
        }

        // Check if the counts exceed n/3
        if (count1 > nums.length / 3) {
            result.add(candidate1);
        }

        // The 'candidate1 != candidate2' check is important for cases where only one
        // majority element exists and might end up in both candidate slots initially.
        if (candidate1 != candidate2 && count2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }


    public static void main(String[] args) {
        // Example 1: The user's original example
        int[] nums1 = {1, 2};
        System.out.println("For [1, 2], majority elements are: " + majorityElement(nums1));
        // Expected: [1, 2].
        // n=2, n/3 = 0. Both 1 and 2 appear 1 time, which is > 0.

        // Example 2: A clear winner
        int[] nums2 = {3, 2, 3};
        System.out.println("For [3, 2, 3], majority elements are: " + majorityElement(nums2));
        // Expected: [3].
        // n=3, n/3 = 1. '3' appears 2 times (> 1). '2' appears 1 time (not > 1).

        // Example 3: Two winners
        int[] nums3 = {2, 2, 1, 1, 1, 2, 2};
        System.out.println("For [2,2,1,1,1,2,2], majority elements are: " + majorityElement(nums3));
        // Expected: [1, 2] (or [2, 1]).
        // n=7, n/3 = 2. '1' appears 3 times (> 2). '2' appears 4 times (> 2).

        // Example 4: No winners
        int[] nums4 = {1, 2, 3};
        System.out.println("For [1, 2, 3], majority elements are: " + majorityElement(nums4));
        // Expected: [].
        // n=3, n/3 = 1. No element appears more than 1 time.
    }
}