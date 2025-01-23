package neetcode150.backtracking;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning_131 {

    //  Can be improved with dp
    public static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) return false;

            start++;
            end--;
        }

        return true;
    }

    public static void helper(String remaining, List<String> partitions, List<List<String>> result) {
        if (remaining.isEmpty()) {
            result.add(new ArrayList<>(partitions));
            return;
        }

        for (int i = 0; i < remaining.length(); i++) {
            String include = remaining.substring(0, i + 1);
            if (isPalindrome(include)) {
                partitions.add(include);
                helper(remaining.substring(i + 1), partitions, result);
                partitions.removeLast(); // reset

            }
        }

    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        helper(s, new ArrayList<>(), result);
        return result;
    }


    public static void main(String[] args) {
        String str = "abcd";
        partition(str);
    }


}
