package leetcode.dp.kandane;

public class MaxSubarray {

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            max = Math.max(sum, max);
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10, -5, 10};
        System.out.println(maxSubArray(nums));
    }

}
