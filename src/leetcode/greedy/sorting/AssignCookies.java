package leetcode.greedy.sorting;

import java.util.Arrays;

public class AssignCookies {

    public static int findContentChildren(int[] greed, int[] cookie) {
        Arrays.sort(greed);
        int j = 0;
        int result = 0;

        for (int k : greed) {

            while (j < cookie.length && cookie[j] < k) {
                j++;
            }

            if (j < cookie.length) {
                result++;
                j++;
            }

        }
        return result;
    }


    public static void main(String[] args) {
        int[] greed = {10, 9, 8, 7};
        int[] cookie = {5, 6, 7, 8};

        System.out.println(findContentChildren(greed, cookie)); // 2
    }

}
