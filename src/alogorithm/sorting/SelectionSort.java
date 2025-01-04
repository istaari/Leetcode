package alogorithm.sorting;

import java.util.Arrays;

class SelectionSort {

    static void selectionSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {

            int min_idx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min_idx]) {
                    min_idx = j;
                }
            }

            int temp = array[i];
            array[i] = array[min_idx];
            array[min_idx] = temp;
        }
    }

    public static void main(String[] args) {
        int[] data = {20, 12, 10, 15, 2};
        selectionSort(data);
        System.out.println(Arrays.toString(data));
    }
}
