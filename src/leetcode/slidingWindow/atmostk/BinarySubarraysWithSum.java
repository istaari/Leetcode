package leetcode.slidingWindow.atmostk;

@SuppressWarnings("all")
public class BinarySubarraysWithSum {

    public static int subarrayAtMostK(int[] nums, int K) {
        int result = 0;
        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < nums.length) {
            sum += nums[right];

            while (left <= right && sum > K) {
                sum -= nums[left];
                left++;
            }

            result += right - left + 1;
            right++;
        }

        return result;
    }


    public static int numSubarraysWithSum(int[] nums, int goal) {
        return subarrayAtMostK(nums, goal) - subarrayAtMostK(nums, goal - 1);
    }


    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;

        System.out.println(numSubarraysWithSum(nums, goal));
    }
}
