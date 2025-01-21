package leetcode.array;

import java.util.Arrays;

public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int first = 0; // Boundary to separate number and zeroes

        for (int second = 0; second < nums.length; second++) {
            if (nums[first] == 0 && nums[second] != 0) {
                int temp = nums[first];
                nums[first] = nums[second];
                nums[second] = temp;
            }

            if (nums[first] != 0) {
                first++;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }
}
