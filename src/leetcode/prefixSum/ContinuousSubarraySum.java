package leetcode.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {


    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // To calculate proper length
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = sum % k;

            if (map.containsKey(mod)) {

                if (i - map.get(mod) >= 2) {
                    return true;
                }

            } else {
                map.put(mod, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {23, 2, 4, 6, 7};
        int k = 6;
        System.out.println(checkSubarraySum(nums, k));
    }
}
