package dsa.sorting;

import java.util.Arrays;

public class CyclicSort {

    /**
     * Used to sort number within bounded index range
     * Can be used to find all the duplicate items
     */
    public static void cyclicSort(int[] nums) {
        int i = 0;

        while (i < nums.length) {
            int correctIndex = nums[i] - 1;

            if (nums[i] != nums[correctIndex]) {
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }
    }

    public static boolean containsDuplicate(int[] nums) {
        int i = 0;

        while (i < nums.length) {
            int correctIndex = nums[i] - 1;

            if (nums[i] != nums[correctIndex]) {
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else if (i != correctIndex) { // Found a duplicate
                return true;
            } else {
                i++;
            }
        }

        return false; // No duplicates found
    }

    public static void main(String[] args) {
        int[] value = {4, 3, 2, 7, 8, 2, 3, 1};
        cyclicSort(value);
        System.out.println(Arrays.toString(value));
        System.out.println(containsDuplicate(value));
    }
}
