package leetcode.dp;

public class CanJump {

    public boolean canJump(int[] nums) {
        int far = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            if (i > far) return false;

            far = Math.max(i + nums[i], far);
        }

        return true;
    }

    public static boolean canJumpDP(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];

        // base case
        dp[n - 1] = true;

        for (int index = n - 2; index >= 0; index--) {

            int forwardJump = index + nums[index];

            for (int i = index + 1; i <= forwardJump; i++) {

                if (i < n && dp[i]) {
                    dp[index] = true;
                    break;
                }

            }
        }

        return dp[0];
    }


    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 1, 4};
        System.out.println(canJumpDP(nums));
    }


}
