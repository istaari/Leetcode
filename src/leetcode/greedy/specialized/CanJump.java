package leetcode.greedy.specialized;

public class CanJump {

    public static boolean canJump(int[] nums) {
        int far = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > far)
                return false;
            far = Math.max(i + nums[i], far);
        }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = { 3, 2, 1, 1, 4 };
        System.out.println(canJump(nums));
    }

}
