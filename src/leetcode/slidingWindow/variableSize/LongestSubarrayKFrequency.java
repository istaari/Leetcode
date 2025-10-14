package leetcode.slidingWindow.variableSize;

import java.util.HashMap;
import java.util.Map;

public class LongestSubarrayKFrequency {

    public static int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();

        int left = 0;
        int result = 0;

        for (int right = 0; right < nums.length; right++) {

            count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);

            while (count.get(nums[right]) > k) {
                count.put(nums[left], count.get(nums[left]) - 1);
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 3, 1, 2};
        int k = 2;
        System.out.println(maxSubarrayLength(nums, k));
    }
}
