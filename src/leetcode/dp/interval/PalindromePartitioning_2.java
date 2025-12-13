package leetcode.dp.interval;

import java.util.HashMap;
import java.util.Map;

public class PalindromePartitioning_2 {

    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static int backtrack(String s, int start, Map<Integer, Integer> memo) {
        if (start >= s.length()) return -1;

        if (memo.containsKey(start)) return memo.get(start);

        int minCut = Integer.MAX_VALUE;

        for (int i = start; i < s.length(); i++) {
            // can be optimized further
            if (isPalindrome(s.substring(start, i + 1))) {
                minCut = Math.min(minCut, 1 + backtrack(s, i + 1, memo));
            }
        }

        memo.put(start, minCut);
        
        return minCut;
    }

    public static int minCut(String s) {
        return backtrack(s, 0, new HashMap<>());
    }

    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(minCut(s));
    }

}
