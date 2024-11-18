package leetcode.math;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LargestNumber {

    public static String largestNumber(int[] nums) {

        String[] number = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            number[i] = String.valueOf(nums[i]);
        }

        Comparator<String> comparator = (a, b) -> (b + a).compareTo(a + b);
        Arrays.sort(number, comparator);

        if(number[0].equals("0")) return "0"; // If largest number is 0

        StringBuilder result = new StringBuilder();
        for (String value : number) {
            result.append(value);
        }

        return result.toString();
    }


    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};

        System.out.println(largestNumber(nums));
    }


}
