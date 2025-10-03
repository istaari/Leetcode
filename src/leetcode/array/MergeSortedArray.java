package leetcode.array;

import java.util.Arrays;


// LeetCode 88: Merge Sorted Array
//
// Problem Statement:
// You are given two integer arrays, `nums1` and `nums2`, sorted in non-decreasing order,
// and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2`
// respectively.
//
// Merge `nums2` into `nums1` as a single sorted array.
//
// The final sorted array should not be returned by the function, but instead be stored
// inside the array `nums1`. To accommodate this, `nums1` has a length of `m + n`,
// where the first `m` elements denote the elements that should be merged, and the last `n`
// elements are set to 0 and should be ignored.
//
// Example:
// Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
// Output: [1,2,2,3,5,6]

public class MergeSortedArray {


    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointer for the last valid element in the initial part of nums1
        int i = m - 1;
        // Pointer for the last element in nums2
        int j = n - 1;
        // Pointer for the last position in the merged array (the end of nums1)
        int k = m + n - 1;

        // Loop as long as there are elements in nums2 to merge
        while (j >= 0) {
            // Check if there are still elements in nums1 to compare and if the
            // element in nums1 is larger than the element in nums2.
            if (i >= 0 && nums1[i] > nums2[j]) {
                // Place the larger element from nums1 at the end
                nums1[k] = nums1[i];
                i--;
            } else {
                // Place the element from nums2 (it's either larger or we're out of nums1 elements)
                nums1[k] = nums2[j];
                j--;
            }
            // Move the write pointer to the left
            k--;
        }
    }

    public static void main(String[] args) {
        // Example 1: Standard case
        int[] nums1_case1 = {1, 2, 3, 0, 0, 0};
        int m1 = 3;
        int[] nums2_case1 = {2, 5, 6};
        int n1 = 3;
        merge(nums1_case1, m1, nums2_case1, n1);
        System.out.println("Case 1 Merged Array: " + Arrays.toString(nums1_case1));
        // Expected: [1, 2, 2, 3, 5, 6]
    }
}