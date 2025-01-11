package leetcode.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int result = 0;

        for (int value : nums) {
            sum += value;

            if (map.containsKey(sum - k)) {
                result += map.get(sum - k);
            }

            // store the prefix and count in map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;

        System.out.println(subarraySum(nums, k));
    }

}
