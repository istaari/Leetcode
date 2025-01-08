package dsa.sorting;

import java.util.Arrays;

public class HeapSort {

    public static void heapSort(int[] array) {
        int n = array.length;

        // This loop builds the initial max heap.
        // It starts from the last non-leaf node (n/2 - 1) and calls heapifyDown for each node up to the root.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(array, n, i);
        }

        // Extract elements from the problems heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Swap the root (maximum element) with the last element
            // Largest element goes to the end
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            /* Call max heapify on the reduced problems heap */
            heapifyDown(array, i, 0);
        }
    }

    private static void heapifyDown(int[] array, int n, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        // Check if left child is larger than root
        if (leftChild < n && array[leftChild] > array[largest]) {
            largest = leftChild;
        }

        // Check if the right child is larger than the largest so far
        if (rightChild < n && array[rightChild] > array[largest]) {
            largest = rightChild;
        }

        // If largest is not the root, swap and heapify the affected subtree
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursively heapify the affected subtree
            heapifyDown(array, n, largest);
        }
    }


    public static void main(String[] args) {
        int[] array = {12, 11, 13, 5, 6, 7};
        System.out.println("Original problems.array:" + Arrays.toString(array));
        heapSort(array);
        System.out.println("Sorted problems.array using HeapSort:" + Arrays.toString(array));

    }
}

