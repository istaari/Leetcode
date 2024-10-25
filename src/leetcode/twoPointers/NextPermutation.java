package leetcode.twoPointers;

import java.util.Arrays;

public class NextPermutation {

    public static void swap(int a, int b, int[] nums) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }

    public static void reverse(int left, int right, int[] nums) {
        while (left < right) {
            swap(left, right, nums);
            right--;
            left++;
        }
    }

    public static void nextPermutation(int[] nums) {
        int pi = -1;

        // check ascending from the last, 2,3,6,5,4,1
        // 3 is not in ascending order

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                pi = i;
                break;
            }
        }

        // if everything is in ascending from last 6,5,4,3,2,1
        // reverse then return
        if (pi == -1) {
            reverse(0, nums.length - 1, nums);
            return;
        }


        // find a greater element than 3 from the last which is 4
        int greater = -1;
        for (int i = nums.length - 1; i > pi; i--) {
            if (nums[i] > nums[pi]) {
                greater = i;
                break;
            }
        }

        // swap 3 and 4 → 2,4,6,5,3,1
        // reverse after 4 all the element 2,4,1,3,5,6
        swap(greater, pi, nums);
        reverse(pi + 1, nums.length - 1, nums);
    }

    public static void main(String[] args) {
        int[] nums = {2,3,6,5,4,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
