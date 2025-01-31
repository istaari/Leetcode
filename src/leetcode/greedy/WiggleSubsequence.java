package leetcode.greedy;

public class WiggleSubsequence {

    public static int wiggleMaxLength(int[] nums) {
        int size = nums.length;
        int peak = 1;
        int valley = 1;
        for (int i = 1; i < size; ++i) {
            if (nums[i] > nums[i - 1]) {
                peak = valley + 1;
            }
            else if (nums[i] < nums[i - 1]) {
                valley = peak + 1;
            }
        }
        return Math.max(peak, valley);
    }


    public static void main(String[] args) {
        int[] nums = {1, 7, 4, 9, 2, 5};
        System.out.println(wiggleMaxLength(nums));
    }
}
