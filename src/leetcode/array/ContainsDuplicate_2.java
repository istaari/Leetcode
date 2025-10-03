package leetcode.array;

import java.util.HashMap;
import java.util.Map;


// LeetCode 219: Contains Duplicate II
//
// Problem Statement:
// Given an integer array `nums` and an integer `k`, return `true` if there are two
// distinct indices `i` and `j` in the array such that `nums[i] == nums[j]` and
// the absolute difference `abs(i - j)` is at most `k`.
//
// Examples:
// - nums = [1,2,3,1], k = 3  -> true.  The 1s at index 0 and 3 have a difference of 3, which is <= k.
// - nums = [1,0,1,1], k = 1  -> true.  The 1s at index 2 and 3 have a difference of 1, which is <= k.
// - nums = [1,2,3,1,2,3], k = 2 -> false. The minimum distance for any duplicate pair is 3, which is > k.

public class ContainsDuplicate_2 {


    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        // The map stores the number and its most recently seen index.
        Map<Integer, Integer> indexMap = new HashMap<>();
        int difference;

        for (int i = 0; i < nums.length; i++) {
            // Check if we have seen this number before.
            if (indexMap.containsKey(nums[i])) {
                // Get the last index where we saw this number.
                int lastSeenIndex = indexMap.get(nums[i]);
                difference = i - lastSeenIndex;

                // Check if the difference is within our threshold 'k'.
                if (difference <= k) {
                    return true;
                }
            }

            // we always have the closest possible index for future checks.
            indexMap.put(nums[i], i);
        }

        // If we finish the loop, no such pair was found.
        return false;
    }


    public static void main(String[] args) {
        int[] nums;
        int k;

        // First case
        nums = new int[]{1, 0, 1, 1};
        k = 1;
        System.out.println(containsNearbyDuplicate(nums, k));

        // Third case
        nums = new int[]{1, 2, 3, 1, 2, 3};
        k = 2;
        System.out.println(containsNearbyDuplicate(nums, k));
    }
}
