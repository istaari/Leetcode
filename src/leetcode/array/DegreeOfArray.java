package leetcode.array;

import java.util.HashMap;
import java.util.Map;


// LeetCode 697: Degree of an Array
//
// Problem Statement:
// Given a non-empty array of non-negative integers `nums`, the degree of this array is
// defined as the maximum frequency of any one of its elements.
//
// Your task is to find the length of the shortest, contiguous subarray of `nums` that
// has the same degree as `nums`.
//
// Example 1:
// Input: nums = [1,2,2,3,1]
// Output: 2
// Explanation:
// The degree of the array is 2 because both 1 and 2 appear twice.
// The subarrays that have the same degree are:
// [1,2,2,3,1] (contains all 1s, length 5)
// [2,2]       (contains all 2s, length 2)
// The shortest length is 2.
//
// Example 2:
// Input: nums = [1,2,2,3,1,4,2]
// Output: 6
// Explanation:
// The degree is 3 (element 2 appears 3 times).
// The subarray containing all 2s is [2,2,3,1,4,2], which starts at index 1 and ends at index 6.
// Its length is 6 - 1 + 1 = 6.

public class DegreeOfArray {


    public static int findShortestSubArray(int[] nums) {
        if (nums.length == 0) return 0;

        // The map's value is an array of: [count, first_index, last_index]
        Map<Integer, int[]> map = new HashMap<>();

        // === PASS 1: GATHER DATA ===
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                // First time seeing this number
                map.put(nums[i], new int[]{1, i, i});
            } else {
                // Already seen this number, update its count and last_index
                int[] temp = map.get(nums[i]);
                temp[0]++;       // Increment count
                temp[2] = i;     // Update last seen index
            }
        }

        // === PASS 2: ANALYZE DATA ===
        int result = Integer.MAX_VALUE;
        int maxCount = Integer.MIN_VALUE;

        // Iterate through the map's values to find the shortest subarray
        for (int[] temp : map.values()) {
            int count = temp[0];
            int firstIndex = temp[1];
            int lastIndex = temp[2];

            if (count > maxCount) {
                // This is the new highest frequency element found so far
                maxCount = count;
                result = lastIndex - firstIndex + 1;
            } else if (count == maxCount) {
                // There's a tie in frequency, so we check if this element
                // provides a shorter subarray length.
                result = Math.min(result, lastIndex - firstIndex + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1: The user's original example
        int[] nums1 = new int[]{1, 2, 2, 3, 1, 4, 2};
        System.out.println("For [1,2,2,3,1,4,2], shortest subarray length is: " + findShortestSubArray(nums1));
        // Expected: 6
        // Degree is 3 (the number 2 appears 3 times).
        // The first '2' is at index 1. The last '2' is at index 6.
        // The shortest subarray is [2,2,3,1,4,2], length = 6 - 1 + 1 = 6.
        // Your original comment had '7', which was likely a typo.

        // Example 2: The classic LeetCode example
        int[] nums2 = new int[]{1, 2, 2, 3, 1};
        System.out.println("For [1,2,2,3,1], shortest subarray length is: " + findShortestSubArray(nums2));
        // Expected: 2
        // Degree is 2. The candidates are 1 and 2.
        // Subarray for 1s: [1,2,2,3,1] -> length = 4 - 0 + 1 = 5.
        // Subarray for 2s: [2,2] -> length = 2 - 1 + 1 = 2.
        // The minimum length is 2.
    }
}