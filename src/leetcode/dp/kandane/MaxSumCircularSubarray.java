package leetcode.dp.kandane;

public class MaxSumCircularSubarray {

    public static int maxKadane(int[] nums) {
        int sum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static int minKadane(int[] nums) {
        int minSum = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            sum = Math.min(sum + nums[i], nums[i]);
            minSum = Math.min(minSum, sum);
        }
        return minSum;
    }

    public static int maxSubarraySumCircular(int[] nums) {
        int maxKadane = maxKadane(nums);

        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int maxAfterWrap = totalSum - minKadane(nums);
        return (maxAfterWrap == 0) ? maxKadane : Math.max(maxKadane, maxAfterWrap);
    }


    public static void main(String[] args) {
        int[] nums = {5, -3, 5};
        System.out.println(maxSubarraySumCircular(nums));
    }

}
