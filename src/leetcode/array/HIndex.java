
package leetcode.array;

import java.util.Arrays;


// LeetCode 274: H-Index & LeetCode 275: H-Index II
//
// Problem Statement:
// Given an array of integers `citations` where `citations[i]` is the number of citations a
// researcher has for their i-th paper, compute the researcher's h-index.
//
// Definition of h-index:
// A scientist has an h-index of `h` if `h` of their `N` papers have **at least** `h` citations each,
// and the other `N - h` papers have **no more than** `h` citations each. The goal is to find the
// maximum possible value of `h`.
//
// Example: citations = [3, 0, 6, 1, 5] (N=5 papers)
// - Can h be 3? We need 3 papers with at least 3 citations. We have [3, 6, 5]. Yes.
// - Can h be 4? We need 4 papers with at least 4 citations. We only have [6, 5]. No.
// So, the maximum h-index is 3.
//
// Note: The two methods in this file solve two versions of the problem.
// - `hIndex()` solves H-Index I (unsorted input).
// - `hIndexB()` solves H-Index II (input is guaranteed to be sorted in ascending order).


public class HIndex {

    /**
     * ### Intuition Behind the Binary Search Solution (`hIndexB`)
     *
     * This method assumes the `citations` array is already sorted in ascending order.
     * This sorting creates a monotonic property that allows for binary search.
     *
     * 1.  **The Search Space:** We are not searching for a value *in* the array. We are searching
     * for the optimal value of `h`. The search space is the range of possible h-indices,
     * which can be mapped to the indices of the array.
     *
     * 2.  **The Monotonic Condition:** Let's define a condition for a given index `mid`:
     * - Let `h = n - mid`. This `h` is the number of papers from index `mid` to the end.
     * - Let `c = citations[mid]`. This `c` is the *minimum* number of citations for this group of `h` papers (since the array is sorted).
     * - The h-index condition is: "Do we have `h` papers with at least `h` citations?"
     * - This translates to the check: `c >= h`, or `citations[mid] >= n - mid`.
     *
     * 3.  **The Binary Search Logic:**
     * - `if (citations[mid] >= n - mid)`: This means an h-index of `(n - mid)` is possible. Since we want the *maximum* possible `h`, we should try to find an even better solution. A larger `h` corresponds to a smaller `mid`. So, we store this as a potential answer (`max = n - mid`) and search in the left half (`right = mid - 1`).
     * - `else`: The number of citations `citations[mid]` is too small for this many papers (`n - mid`). We need to consider fewer papers (a smaller `h`), which means moving to the right half of the array (`left = mid + 1`).
     *
     * This binary search efficiently narrows down the search space to find the optimal partition point that defines the maximum h-index.
     */
    public static int hIndexB(int[] citations) {
        int left = 0;
        int right = citations.length - 1;
        int n = citations.length;
        int max = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int h = n - mid; // The potential h-index value

            // Check if the h-index definition is met at this partition
            if (citations[mid] >= h) {
                // This is a valid h-index. Store it and try for a larger one.
                // A larger h means a smaller mid, so we search the left side.
                max = h;
                right = mid - 1;
            } else {
                // The number of citations is too low for this many papers.
                // We need a smaller h, which means a larger mid, so search the right side.
                left = mid + 1;
            }
        }
        return max;
    }

    /**
     * ### Intuition Behind the Sorting Solution (`hIndex`)
     *
     * This method works for an unsorted input array.
     *
     * 1.  **Sort the Data:** The definition of h-index depends on counts of papers relative to
     * citation numbers. Sorting makes this relationship much easier to analyze.
     *
     * 2.  **Sort Descending:** The code sorts ascending and then reverses. This is equivalent to
     * sorting in descending order. Let's consider the array `[6, 5, 3, 1, 0]`.
     *
     * 3.  **The Logic:** After sorting descending, the index `i` can represent the number of papers
     * we are considering (well, `i+1` does).
     * - At `i=0`, we are looking at the top `1` paper. It has `citations[0] = 6` citations. Is `1 <= 6`? Yes. So h-index is at least 1.
     * - At `i=1`, we are looking at the top `2` papers. The one with fewer citations has `citations[1] = 5`. Is `2 <= 5`? Yes. So h-index is at least 2.
     * - At `i=2`, we look at the top `3` papers. The minimum citations is `citations[2] = 3`. Is `3 <= 3`? Yes. So h-index is at least 3.
     * - At `i=3`, we look at the top `4` papers. The minimum citations is `citations[3] = 1`. Is `4 <= 1`? No. The condition breaks.
     *
     * The loop finds the largest `i+1` (which is our `h`) that satisfies the condition `h <= citations[h-1]`.
     */
    public static int hIndex(int[] citations) {
        Arrays.sort(citations); // Sort in ascending order: e.g., [0, 1, 3, 5, 6]

        int n = citations.length;
        // Reverse the array to get descending order: e.g., [6, 5, 3, 1, 0]
        for (int i = 0; i < n / 2; i++) {
            int temp = citations[i];
            citations[i] = citations[n - i - 1];
            citations[n - i - 1] = temp;
        }

        int h = 0;
        // Find the largest h such that the h-th paper has at least h citations
        for (int i = 0; i < n; i++) {
            // i+1 is the number of papers (our potential h)
            // citations[i] is the citation count for the i-th paper (0-indexed)
            if (i + 1 <= citations[i]) {
                h = i + 1;
            } else {
                // As soon as the condition fails, we can stop because the citation
                // counts will only get smaller from here.
                break;
            }
        }

        return h;
    }

    public static void main(String[] args) {
        // --- Testing the Binary Search Method (for sorted input) ---
        System.out.println("--- Binary Search Method (hIndexB) ---");

        int[] citations1 = {0, 1, 3, 5, 6};
        System.out.println("For [0,1,3,5,6], h-index is: " + hIndexB(citations1));
        // Expected: 3. There are 3 papers ([3,5,6]) with at least 3 citations each.

        int[] citations2 = {0, 1};
        System.out.println("For [0,1], h-index is: " + hIndexB(citations2));
        // Expected: 1. There is 1 paper ([1]) with at least 1 citation.

        int[] citations3 = {1, 2, 100};
        System.out.println("For [1,2,100], h-index is: " + hIndexB(citations3));
        // Expected: 2. There are 2 papers ([2,100]) with at least 2 citations each.

        int[] citations4 = {11, 15};
        System.out.println("For [11,15], h-index is: " + hIndexB(citations4));
        // Expected: 2. There are 2 papers with at least 2 citations each.
    }
}