package leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement {

    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        int count1 = 0;
        int candidate1 = 0;

        int count2 = 0;
        int candidate2 = 0;


        for (int val : nums) {

            if (count1 == 0 && candidate2 != val) {
                candidate1 = val;
            } else if (count2 == 0 && candidate1 != val) {
                candidate2 = val;
            }


            if (candidate1 == val) {
                count1++;
            } else if (candidate2 == val) {
                count2++;
            } else {
                count1--;
                count2--;
            }

        }

        int newCount1 = 0;
        int newCount2 = 0;

        for (int val : nums) {
            if (val == candidate1) newCount1++;
            if (val == candidate2) newCount2++;
        }

        if (newCount1 > nums.length / 3) result.add(candidate1);

        if (candidate1 != candidate2 && newCount2 > nums.length / 3) {
            result.add(candidate2);
        }


        return result;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2};
        System.out.println(majorityElement(nums));
    }


}
