package leetcode.greedy.specialized;

public class CanJump_2 {

    public static int canJump(int[] nums) {
        int far = 0;
        int reached = 0;
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n - 1; i++) {
            far = Math.max(i + nums[i], far);

            if (i == reached) {
                count++;
                reached = far;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = { 2, 3, 1, 1, 4 };
        System.out.println(canJump(nums));
    }

}
