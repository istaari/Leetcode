package leetcode.sampling;

import java.util.Arrays;
import java.util.Random;

public class ShuffleArray {

    int[] nums;
    int[] clone;

    public ShuffleArray(int[] nums) {
        this.nums = nums;
        this.clone = nums.clone(); // deep clone
        //this.clone = Arrays.copyOf(nums, nums.length); // deep clone
    }

    public int[] reset() {
        System.arraycopy(clone, 0, nums, 0, clone.length);
        return nums;
    }

    public int[] shuffle() {
        Random rand = new Random();

        for (int i = nums.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }


}
