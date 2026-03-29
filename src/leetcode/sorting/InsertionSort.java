package leetcode.sorting;

import java.util.Arrays;

/**
 * Insertion Sort Algorithm
 *
 * ==================== CONCEPT ====================
 * Builds the sorted array one element at a time by picking each unsorted element
 * and inserting it into its correct position within the already-sorted portion.
 * Similar to how you sort playing cards in your hand.
 *
 * ==================== ALGORITHM STEPS ====================
 *
 * 1. Start from the second element (index 1); the first element is trivially sorted.
 * 2. Store the current element as 'key'.
 * 3. Compare 'key' with each element in the sorted portion (right to left).
 * 4. Shift every element that is greater than 'key' one position to the right.
 * 5. Insert 'key' into the gap created by the shifts.
 * 6. Repeat steps 2–5 for all remaining unsorted elements.
 *
 * ==================== EXAMPLE WALKTHROUGH ====================
 *
 *  Input: [9, 5, 1, 4, 3]
 *                          sorted | unsorted
 *  Initial:               [9]    | [5, 1, 4, 3]
 *  i=1, key=5:  5 < 9 → shift 9 → insert 5   → [5, 9]    | [1, 4, 3]
 *  i=2, key=1:  1 < 9 → shift 9
 *               1 < 5 → shift 5 → insert 1    → [1, 5, 9] | [4, 3]
 *  i=3, key=4:  4 < 9 → shift 9
 *               4 < 5 → shift 5 → insert 4    → [1, 4, 5, 9] | [3]
 *  i=4, key=3:  3 < 9 → shift 9
 *               3 < 5 → shift 5
 *               3 < 4 → shift 4 → insert 3    → [1, 3, 4, 5, 9]
 *
 *  Output: [1, 3, 4, 5, 9]  ✓ Sorted!
 *
 * ==================== COMPLEXITY ====================
 *  Time  — Best:    O(n)      (already sorted, no shifts needed)
 *        — Average: O(n²)     (random order)
 *        — Worst:   O(n²)     (reverse sorted, every element shifts fully)
 *  Space — O(1)               (in-place, only one temp variable)
 *
 * ==================== PROPERTIES ====================
 *  - Stable     : Equal elements retain their relative order.
 *  - In-Place   : Sorts within the original array.
 *  - Adaptive   : Runs in O(n) when the array is nearly sorted.
 *  - Online     : Can sort elements as they are received.
 */
class InsertionSort {

    /**
     * Sorts the array in ascending order using insertion sort.
     *
     * @param array the array to sort
     */
    static void insertionSort(int[] array) {
        int n = array.length;

        // Iterate over each unsorted element starting from index 1
        for (int i = 1; i < n; i++) {
            int key = array[i];   // Element to be inserted into the sorted portion
            int j = i - 1;        // Pointer to the end of the sorted portion

            // Shift elements in the sorted portion that are greater than key
            // one position to the right to make room for key
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            // Insert key into its correct sorted position
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] data = {9, 5, 1, 4, 3};
        System.out.println("Before: " + Arrays.toString(data));
        insertionSort(data);
        System.out.println("After:  " + Arrays.toString(data));
    }
}