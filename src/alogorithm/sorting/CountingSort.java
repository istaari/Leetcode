package alogorithm.sorting;

public class CountingSort {

    // 1. find the max value, calculate then length
    // 2. count the occurrences
    // 3. store the cumulative count
    // 4. from the last take the elements find the right index

    public static void countingSort(int[] nums) {
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

        for (int i = 0; i < nums.length; i++) {
            int index = count[nums[i]] - 1;
            result[index] = nums[i];
            count[nums[i]]--;
        }

    }


    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 8, 3, 3, 1};
        countingSort(nums);
    }


}
