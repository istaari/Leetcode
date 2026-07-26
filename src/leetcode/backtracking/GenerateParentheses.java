package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * LC 22 - Generate Parentheses
 *
 * Given n pairs of parentheses, generate all combinations of well-formed parentheses.
 *
 * Example 1:
 *   Input:  n=3
 *   Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 *   Input:  n=1
 *   Output: ["()"]
 *
 * Constraints:
 *   1 <= n <= 8
 *
 * Approach: backtracking — track open and close counts.
 *   Add '(' if open < n, add ')' if close < open.
 *   This guarantees only valid sequences are built (no invalid state ever explored).
 * Time: O(4^n / sqrt(n)) — nth Catalan number   Space: O(n) recursion depth
 */

public class GenerateParentheses {

    public static void main(String[] args) {
        int n = 10;
        System.out.println(new GenerateParentheses().generateParenthesis(n));
    }


    public List<String> helper(int n, int opening, int closing, List<String> result, String parenthesis) {
        if (parenthesis.length() == n * 2) {
            result.add(parenthesis);
        }

        if (opening != n) {
            helper(n, opening + 1, closing, result, parenthesis + "(");
        }

        if (closing != n && closing < opening) {
            helper(n, opening, closing + 1, result, parenthesis + ")");
        }

        return result;

    }

    public List<String> generateParenthesis(int n) {
        return helper(n, 0, 0, new ArrayList<>(), "");
    }


}
