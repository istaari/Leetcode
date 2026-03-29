package leetcode.sorting;

import java.util.Arrays;

@SuppressWarnings("all")
public class QuickSort {

    // Quicksort algorithm
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Partition the problem array and get the index of the pivot element
            int pivotIndex = partition(array, low, high);
            // Recursively sort the arrays on both sides of the pivot
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    // Partitioning method to find the correct position of the pivot
    public static int partition(int[] array, int low, int high) {
        // Choose the last element element as the pivot
        int pivot = array[high];

        // 'boundary' tracks the rightmost index of elements smaller <= to the pivot.
        // array[low...boundary] <= pivot, array[boundary+1...j-1] > pivot.
        int boundary = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                boundary++;
                swap(array, boundary, j);
            }
        }
        swap(array, boundary + 1, high);
        return boundary + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Main method to test the QuickSort algorithm
    public static void main(String[] args) {
        int[] array = { 12, 4, 5, 6, 7, 3, 1, 9 };
        System.out.println("Original problems.array: " + Arrays.toString(array));
        quickSort(array, 0, array.length - 1);
        System.out.println("Sorted problems.array: " + Arrays.toString(array));
    }

}
