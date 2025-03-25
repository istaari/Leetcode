package dsa;

import java.util.Arrays;

@SuppressWarnings("ALL")
public class MergeSort {

    void merge(int[] arr, int left, int mid, int right) {
        // If there are two elements, left = 0, mid = 0, right = 1
        int leftSubArraySize = mid - left + 1;  // leftSubArraySize = 0 - 0 + 1
        int rightSubArraySize = right - mid; // leftSubArraySize = 1 - 0

        // Create temp arrays
        int[] leftSubArray = new int[leftSubArraySize];
        int[] rightSubArray = new int[rightSubArraySize];

        //copy the left subarray elements from array, start from left
        for (int i = 0; i < leftSubArraySize; i++)
            leftSubArray[i] = arr[left + i];

        //copy the right subarray elements from array, start from right
        for (int j = 0; j < rightSubArraySize; j++)
            rightSubArray[j] = arr[(mid + 1) + j];

        // Merge the temp arrays
        // Initial indexes of first and second sub array
        int i = 0, j = 0;

        // Initial index of merged sub array problems
        int k = left;
        while (i < leftSubArraySize && j < rightSubArraySize) {
            if (leftSubArray[i] <= rightSubArray[j]) {
                arr[k] = leftSubArray[i];
                i++;
            } else {
                arr[k] = rightSubArray[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < leftSubArraySize) {
            arr[k] = leftSubArray[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < rightSubArraySize) {
            arr[k] = rightSubArray[j];
            j++;
            k++;
        }
    }

    void mergesort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergesort(arr, left, mid);
            mergesort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {12, 11};

        System.out.println("Given Array");
        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));

        mergeSort.mergesort(arr, 0, arr.length - 1);

        System.out.println("\nSorted problems.array");
        Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
    }
}

