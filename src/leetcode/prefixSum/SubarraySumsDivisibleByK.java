package leetcode.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {

    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        int result = 0;
        for (int num : nums) {
            sum += num;
            int remainder = sum % k;

            // Handle negative remainders
            if (remainder < 0) {
                remainder += k;
            }

            result += map.getOrDefault(remainder, 0);

            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }

        return result;
    }


}
