package leetcode.slidingWindow.atmostk;

@SuppressWarnings("all")
public class NiceSubarrays {

    public static int subarrayAtMostK(int[] nums, int K) {
        int result = 0;
        int left = 0;
        int right = 0;
        int count = 0;

        while (right < nums.length) {
            if (nums[right] % 2 == 1) {
                count++;
            }

            while (left <= right && count > K) {
                if (nums[left] % 2 == 1) {
                    count--;
                }

                left++;
            }

            result += right - left + 1;
            right++;
        }

        return result;
    }


    public static int numberOfSubarrays(int[] nums, int k) {
        return subarrayAtMostK(nums, k) - subarrayAtMostK(nums, k - 1);
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 1, 1};
        int K = 3;

        System.out.println(numberOfSubarrays(nums, K));
    }

}
