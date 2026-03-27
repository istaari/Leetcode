package leetcode.dp.interval;

import java.util.HashMap;
import java.util.Map;

/**
 * 132. Palindrome Partitioning II
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 *
 * Given a string s, partition s such that every substring of the partition
 * is a palindrome. Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example 1: Input: s = "aab" -> Output: 1
 *   Explanation: The palindrome partitioning ["aa","b"] needs 1 cut.
 *
 * Example 2: Input: s = "a" -> Output: 0
 *
 * Example 3: Input: s = "ab" -> Output: 1
 *
 * Constraints:
 *   1 <= s.length <= 2000
 *   s consists of lowercase English letters only.
 *
 * ---
 * Approach: DP with memoization (front partitioning)
 *
 * For each starting index, try all possible palindromic substrings and
 * recursively solve the remaining suffix. Track min cuts via memoization.
 *
 * STATE:      memo[start] = min cuts to partition s[start..end]
 * BASE:       start >= s.length() -> return -1 (no cut needed, we overcount by 1)
 * TRANSITION: For each i from start to end, if s[start..i] is palindrome:
 *             memo[start] = min(1 + solve(i+1))
 *
 * Time:  O(n^2) with palindrome precomputation, O(n^3) as-is
 * Space: O(n)
 */
public class PalindromePartitioning_2 {

    // Helper: check if a string is a palindrome
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

    // Top-down: find min cuts for s[start..end]
    // Returns -1 when start >= length (accounts for the extra +1 count)
    public static int backtrack(String s, int start, Map<Integer, Integer> memo) {
        if (start >= s.length()) return -1; // entire string consumed, no cut needed

        if (memo.containsKey(start)) return memo.get(start);

        int minCut = Integer.MAX_VALUE;

        // Try every possible partition point
        for (int i = start; i < s.length(); i++) {
            // If s[start..i] is a palindrome, make a cut here and solve the rest
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
