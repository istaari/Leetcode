package dsa.sorting;

import java.util.Arrays;

/**
 * Heap Sort Algorithm
 *
 * ==================== CONCEPT ====================
 * Heap Sort is a comparison-based sorting algorithm that uses a Binary Heap data structure.
 * It works by first building a Max-Heap from the input array, then repeatedly extracting
 * the maximum element and placing it at the end of the array.
 *
 * ==================== ALGORITHM STEPS (In-Place) ====================
 *
 * Step 1: Build a Max-Heap from the unsorted array.
 *         - Start from the last non-leaf node (index n/2 - 1) down to 0.
 *         - Call heapify on each node to ensure the max-heap property:
 *           parent >= left child AND parent >= right child.
 *         - Why start from n/2 - 1? Because nodes from n/2 to n-1 are leaf nodes
 *           and already satisfy the heap property trivially.
 *
 * Step 2: Extract elements one by one from the heap.
 *         - The root (index 0) is always the largest element in the heap.
 *         - Swap arr[0] with arr[i] (last unsorted element).
 *         - Reduce the heap size by 1 (shrink the unsorted region).
 *         - Call heapify on the root to restore the max-heap property
 *           for the remaining unsorted portion.
 *         - Repeat until the heap size is 1.
 *
 * ==================== EXAMPLE WALKTHROUGH ====================
 *
 *  Input:     [4, 10, 3, 5, 1]
 *
 *  Step 1 — Build Max-Heap:
 *    Start heapify from index 1 (last non-leaf = 5/2 - 1 = 1)
 *      i=1: [4, 10, 3, 5, 1] → children of 10 are 5,1 → already max → no change
 *      i=0: [4, 10, 3, 5, 1] → children of 4 are 10,3 → swap 4 and 10
 *           [10, 4, 3, 5, 1] → recurse on i=1 → swap 4 and 5
 *           [10, 5, 3, 4, 1]  ← Max-Heap built
 *
 *  Step 2 — Extract max repeatedly:
 *    i=4: swap(0,4) → [1, 5, 3, 4, |10] → heapify(0, size=4) → [5, 4, 3, 1, |10]
 *    i=3: swap(0,3) → [1, 4, 3, |5, 10]  → heapify(0, size=3) → [4, 1, 3, |5, 10]
 *    i=2: swap(0,2) → [3, 1, |4, 5, 10]  → heapify(0, size=2) → [3, 1, |4, 5, 10]
 *    i=1: swap(0,1) → [1, |3, 4, 5, 10]  → heapify(0, size=1) → [1, 3, 4, 5, 10]
 *
 *  Output:    [1, 3, 4, 5, 10]  ✓ Sorted!
 *
 * ==================== TIME COMPLEXITY ====================
 *
 *  Approach 1: In-Place Heap Sort (heapSort)
 *  ┌──────────────────┬────────────────────────────────────────────────────┐
 *  │ Build Max-Heap   │ O(n) — tighter bound; each node heapifies at     │
 *  │                  │        most O(h) where h is its height. Sum of    │
 *  │                  │        heights across all nodes = O(n).           │
 *  ├──────────────────┼────────────────────────────────────────────────────┤
 *  │ Extract phase    │ O(n log n) — n extractions, each O(log n) heapify│
 *  ├──────────────────┼────────────────────────────────────────────────────┤
 *  │ Overall Time     │ O(n log n) — Best, Average, and Worst case       │
 *  ├──────────────────┼────────────────────────────────────────────────────┤
 *  │ Space            │ O(1) — in-place, no extra array needed            │
 *  └──────────────────┴────────────────────────────────────────────────────┘
 *
 *  Approach 2: Naive Heap Sort using separate MaxHeap (heapSortNaive)
 *  ┌──────────────────┬────────────────────────────────────────────────────┐
 *  │ Insert all       │ O(n log n) — n insertions, each O(log n) sift-up │
 *  ├──────────────────┼────────────────────────────────────────────────────┤
 *  │ Extract all      │ O(n log n) — n extractions, each O(log n)        │
 *  ├──────────────────┼────────────────────────────────────────────────────┤
 *  │ Overall Time     │ O(n log n) — Best, Average, and Worst case       │
 *  ├──────────────────┼────────────────────────────────────────────────────┤
 *  │ Space            │ O(n) — requires a separate heap of size n         │
 *  └──────────────────┴────────────────────────────────────────────────────┘
 *
 *  Key Difference: Both are O(n log n) in time, but the in-place approach
 *  uses O(1) extra space while the naive approach uses O(n) extra space.
 *  Also, building a heap via bottom-up heapify is O(n), faster than
 *  n individual insertions which cost O(n log n).
 *
 * ==================== PROPERTIES ====================
 *  - Not Stable    : Equal elements may change relative order.
 *  - In-Place      : Approach 1 sorts within the original array.
 *  - Not Adaptive  : Does not benefit from partially sorted input.
 */
@SuppressWarnings("all")
public class HeapSort {

    // ======================== APPROACH 1: In-Place Heap Sort ========================

    /**
     * Sorts the array in ascending order using in-place heap sort.
     *
     * Algorithm:
     *   1. Build a max-heap from the array (bottom-up).
     *   2. Repeatedly swap the root (max) with the last unsorted element,
     *      shrink the heap, and heapify the root.
     *
     * @param arr the array to sort
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Step 1: Build Max-Heap (bottom-up, starting from last non-leaf node)
        // Last non-leaf node index = n/2 - 1
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Step 2: Extract elements from heap one by one
        // After each swap, the largest element is at the end (sorted position)
        for (int i = n - 1; i > 0; i--) {
            // Swap root (maximum) with the last unsorted element
            swap(arr, 0, i);

            // Heapify the reduced heap (size = i, excludes sorted tail)
            heapify(arr, i, 0);
        }
    }

    /**
     * Maintains the max-heap property for a subtree rooted at index i.
     *
     * Compares the node at index i with its left and right children.
     * If a child is larger, swaps and recurses down.
     *
     * @param arr  the array representing the heap
     * @param size the current size of the heap (elements beyond this are sorted)
     * @param i    the index of the root of the subtree to heapify
     */
    private static void heapify(int[] arr, int size, int i) {
        int largest = i;            // Assume root is the largest
        int left = 2 * i + 1;      // Left child index
        int right = 2 * i + 2;     // Right child index

        // If left child exists and is greater than current largest
        if (left < size && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child exists and is greater than current largest
        if (right < size && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not the root, swap and continue heapifying
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, size, largest);    // Recursively heapify the affected subtree
        }
    }

    /**
     * Swaps two elements in the array.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // ======================== APPROACH 2: Naive Heap Sort ========================

    /**
     * Sorts the array using a separate max-heap (naive approach).
     *
     * Algorithm:
     *   1. Insert all elements into an auxiliary max-heap one by one.
     *   2. Extract max repeatedly and fill the array from right to left.
     *
     * This is simpler to understand but uses O(n) extra space.
     *
     * @param arr the array to sort
     */
    public static void heapSortNaive(int[] arr) {
        int n = arr.length;
        int[] heap = new int[n];
        int heapSize = 0;

        // Step 1: Insert all elements into the max-heap
        for (int val : arr) {
            heap[heapSize] = val;
            siftUp(heap, heapSize);
            heapSize++;
        }

        // Step 2: Extract max one by one and place at the end of array
        for (int i = n - 1; i >= 0; i--) {
            arr[i] = heap[0];                       // Root is the max
            heap[0] = heap[heapSize - 1];           // Move last element to root
            heapSize--;
            siftDown(heap, heapSize, 0);            // Restore heap property
        }
    }

    /**
     * Sifts the element at index i up to restore the max-heap property.
     * Used after inserting a new element at the bottom of the heap.
     */
    private static void siftUp(int[] heap, int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[i] > heap[parent]) {
                swap(heap, i, parent);
                i = parent;
            } else {
                break;
            }
        }
    }

    /**
     * Sifts the element at index i down to restore the max-heap property.
     * Used after replacing the root during extraction.
     */
    private static void siftDown(int[] heap, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(heap, i, largest);
            siftDown(heap, size, largest);
        }
    }


    // ======================== MAIN (TEST) ========================

    public static void main(String[] args) {
        int[] arr1 = {4, 10, 3, 5, 1};
        System.out.println("Original array:  " + Arrays.toString(arr1));
        heapSort(arr1);
        System.out.println("Sorted (in-place): " + Arrays.toString(arr1));

        System.out.println();

        int[] arr2 = {4, 10, 3, 5, 1};
        System.out.println("Original array:  " + Arrays.toString(arr2));
        heapSortNaive(arr2);
        System.out.println("Sorted (naive):  " + Arrays.toString(arr2));
    }
}
