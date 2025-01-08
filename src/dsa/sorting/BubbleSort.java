package dsa.sorting;

import java.util.Arrays;

@SuppressWarnings("all")
public class BubbleSort {

    //In a single pass of the bubble sort algorithm, the largest unsorted element is guaranteed to move to its correct position at the end of the array.
    static void bubbleSort(int[] array) {
        int len = array.length - 1;
        for (int i = 0; i < len; i++) {
            // (size - 1) - i to reduce the comparison space, at each iteration last element moves to sorted postion
            for (int j = 0; j < len - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {-2, 45, 0, 11, -9, 8, 1, 90};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
