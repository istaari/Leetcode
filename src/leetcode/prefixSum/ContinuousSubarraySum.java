package leetcode.prefixSum;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> modMap = new HashMap<>();

        modMap.put(0, -1);
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int mod = sum % k;

            if (modMap.containsKey(mod)) {
                if (i - modMap.get(mod) >= 2) return true;
            } else {
                modMap.put(mod, i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {23,2,4,6,7};
        int k = 6;
        System.out.println(checkSubarraySum(nums, k));
    }
}
