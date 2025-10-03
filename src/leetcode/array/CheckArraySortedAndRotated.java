package leetcode.array;

// LeetCode 1752: Check if Array Is Sorted and Rotated
//
// Problem Statement:
// Given an array of integers `nums`, return `true` if the array was originally sorted in
// non-decreasing order, then rotated some number of positions (including zero).
// Otherwise, return `false`.
//
// An array A rotated by x positions results in an array B of the same length such
// that B[i] = A[(i+x) % A.length], where % is the modulo operation.
// Note that there can be duplicate elements in the array.
//
// Examples:
// - nums = [3,4,5,1,2] -> true. The original sorted array was [1,2,3,4,5] rotated by 3 positions.
// - nums = [2,1,3,4] -> false. This array cannot be obtained by rotating a sorted array.
// - nums = [1,2,3] -> true. This is a sorted array rotated by 0 positions.

public class CheckArraySortedAndRotated {

    public static boolean check(int[] nums) {
        int deviations = 0;
        for (int i = 0; i < nums.length; i++) {

            // If rotated and sorted array [3,4,5,1,2] there will be only one deviation
            // If not rotated and sorted array [1,2,3,4,5] there will be no deviation
            // If not sorted array [2,1,3,4] there will be two deviations
            if (nums[i] > nums[(i + 1) % nums.length]) {
                deviations++;
            }
        }

        return deviations < 2;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2};
        System.out.println(check(nums));
    }
}
