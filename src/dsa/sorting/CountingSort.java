package dsa.sorting;

import java.util.Arrays;

public class CountingSort {

    /**
     * 1. Find the max value, calculate length
     * 2. Count the occurrences
     * 3. Store the cumulative count
     * 4. From the last take the elements find the right index and place it in the output array
     */
    public static int[] countingSort(int[] nums) {
        int max = 0;

        for (int val : nums) {
            max = Math.max(max, val);
        }

        int[] count = new int[max + 1];
        for (int val : nums) {
            count[val]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }

        int[] result = new int[nums.length];
        for (int num : nums) {
            int index = count[num] - 1;
            result[index] = num;
            count[num]--;
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 8, 3, 3, 1};
        System.out.println(Arrays.toString(countingSort(nums)));
    }


}
