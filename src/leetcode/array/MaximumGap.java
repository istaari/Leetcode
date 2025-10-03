

package leetcode.array;
import java.util.Arrays;


// LeetCode 164: Maximum Gap
//
// Problem Statement:
// Given an integer array `nums`, return the maximum difference between two successive
// elements in its sorted form. If the array contains less than two elements, return 0.
//
// You must write an algorithm that runs in linear time and uses linear extra space.
//
// Example 1:
// Input: nums = [3,6,9,1]
// Output: 3
// Explanation: The sorted form of the array is [1,3,6,9], and the maximum
// difference is between (3,6) or (6,9), which is 3.
//
// Example 2:
// Input: nums = [10]
// Output: 0
// Explanation: The array contains less than 2 elements.
//
// Constraint: The O(N) time complexity rules out a simple O(N log N) sorting approach.

public class MaximumGap {

    /**
     * ### Intuition Behind the Solution (Pigeonhole Principle & Bucket Sort)
     *
     * 1.  **The Constraint is Key:** The problem requires an O(N) solution. This immediately tells us we
     * cannot use a standard comparison-based sort (like `Arrays.sort()`), which takes O(N log N). We
     * must use a non-comparison-based sorting idea like Radix Sort or Bucket Sort. This solution
     * cleverly uses the principles of Bucket Sort.
     *
     * 2.  **The Pigeonhole Principle:**
     * - Imagine we have `N` numbers in the array. In their sorted form, there will be `N-1` gaps
     * between successive elements.
     * - The total range of values is `max - min`.
     * - The *average* gap size would be `(max - min) / (N - 1)`.
     * - By the pigeonhole principle, the *maximum gap* between any two successive sorted elements must
     * be **at least** this average gap size.
     *
     * 3.  **The Bucket Strategy (The "Aha!" Moment):**
     * - What if we create "buckets" to group our numbers, and we make the size of each bucket
     * smaller than the average gap? Let's say `bucketSize = (max - min) / (N - 1)`.
     * - Any two numbers that fall into the **same bucket** will have a difference between them that is
     * less than `bucketSize`.
     * - Since we know the true `maximumGap` must be greater than or equal to `bucketSize`, this means
     * the maximum gap **cannot possibly occur inside a single bucket**.
     * - Therefore, the maximum gap must occur **between** two numbers in **different buckets**.
     * Specifically, it must be the difference between the **maximum** value in some bucket `i`
     * and the **minimum** value in the *next non-empty* bucket `j`.
     *
     * 4.  **The Algorithm's Plan:**
     * - **Pass 1:** Find the `min` and `max` values in the array.
     * - **Calculate Bucket Info:** Determine the `bucketSize` and `bucketCount`.
     * - **Pass 2 (Distribution):** Create `bucketCount` buckets. For each bucket, we only need to
     * store the minimum and maximum number that falls into it. We can discard all other numbers
     * in between, because they can't form the maximum gap.
     * - **Pass 3 (Gap Calculation):** Iterate through the buckets. Ignore any empty buckets. The
     * maximum gap will be the largest difference found between the `min` of the current non-empty
     * bucket and the `max` of the previous non-empty bucket.
     */
    public static int maximumGap(int[] nums) {

        if (nums.length < 2) return 0;

        // Pass 1: Find min and max elements
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int val : nums) {
            max = Math.max(max, val);
            min = Math.min(min, val);
        }

        if (max == min) return 0; // All elements are the same

        // Calculate bucket size and count based on the Pigeonhole Principle
        int bucketSize = Math.max(1, (max - min) / (nums.length - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        // Create buckets, each storing only the min and max value within it
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // Pass 2: Distribute numbers into buckets
        for (int val : nums) {
            int bucketIndex = (val - min) / bucketSize;
            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], val);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], val);
        }

        // Pass 3: Calculate the maximum gap between non-empty buckets
        int prevMax = min; // Start with the global min
        int maxGap = 0;

        for (int i = 0; i < bucketCount; i++) {
            // Skip empty buckets
            if (bucketMin[i] == Integer.MAX_VALUE) {
                continue;
            }

            // The maximum gap must be between the max of a previous bucket and the min of a current one.
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            // Update the previous max for the next iteration
            prevMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        // Example 1: The user's original example
        int[] nums1 = {3, 6, 9, 1};
        System.out.println("For [3, 6, 9, 1], Maximum Gap is: " + maximumGap(nums1));
        // Expected: 3.
        // Sorted form is [1, 3, 6, 9].
        // Gaps are (3-1)=2, (6-3)=3, (9-6)=3. The max is 3.

        // Example 2: An array with an empty bucket in the middle
        int[] nums2 = {1, 10, 5};
        System.out.println("For [1, 10, 5], Maximum Gap is: " + maximumGap(nums2));
        // Expected: 5.
        // Sorted form is [1, 5, 10].
        // Gaps are (5-1)=4, (10-5)=5. The max is 5.
        // The algorithm will place 1, 5, and 10 in different buckets, and find the gap between them.

        // Example 3: Less than 2 elements
        int[] nums3 = {100};
        System.out.println("For [100], Maximum Gap is: " + maximumGap(nums3));
        // Expected: 0. The array has fewer than two elements.
    }
}