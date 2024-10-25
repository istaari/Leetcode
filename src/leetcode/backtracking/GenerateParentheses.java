package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

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
