package leetcode.slidingWindow.variableSize;

public class MaxConsecutives3 {

    public static int longestOnes(int[] nums, int k) {
        int result = 0;

        int left = 0;
        int right = 0;

        while (right < nums.length) {

            if (nums[right] == 0) {
                k--;
            }

            if (k < 0) {
                while (k < 0) {
                    if (nums[left] == 0) {
                        k++;
                    }
                    left++;
                }

            }

            result = Math.max(result, right - left + 1);
            right++;

        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k = 3;

        System.out.println(longestOnes(nums, k));
    }
}
