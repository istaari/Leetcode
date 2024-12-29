package leetcode.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class SubarraysKDifferentIntegers {


    public static int countAtMostKSubarrays(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int result = 0;

        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.size() > k) {
                map.put(nums[left], map.get(nums[left]) - 1);

                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }

                left++;
            }

            // (right - left + 1) - It calculates the count of all possible subarrays ending at the current 'right' position.
            result += (right - left + 1);
        }

        return result;
    }


    public static int subarraysWithKDistinct(int[] nums, int k) {
        return countAtMostKSubarrays(nums, k) - countAtMostKSubarrays(nums, k - 1);
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;

        System.out.println(subarraysWithKDistinct(nums, k));
    }


}
