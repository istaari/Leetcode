package leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class DegreeOfArray {

    // https://leetcode.com/problems/degree-of-an-array/solutions/124317/java-c-python-one-pass-solution/
    // Calculates first and last index of a repeated element in an array

    public static int findShortestSubArray(int[] nums) {

        if (nums.length == 0) return 0;

        Map<Integer, int[]> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{1, i, i});
            } else {
                int[] temp = map.get(nums[i]);
                temp[0]++;
                temp[2] = i;
            }
        }

        int result = Integer.MAX_VALUE;
        int maxCount = Integer.MIN_VALUE;

        for (int num : nums) {
            int[] temp = map.get(num);

            if (temp[0] > maxCount) {
                maxCount = temp[0];
                result =  temp[2] - temp[1] + 1;
            }
            else if (temp[0] == maxCount) {
                result = Math.min(result, temp[2] - temp[1] + 1);
            }

        }


        return result;
    }


    public static void main(String[] args) {
        int[] a = new int[]{1,2,2,3,1,4,2};
        //[1,2,2,3,1]
        System.out.println(findShortestSubArray(a)); // 7
    }
}
