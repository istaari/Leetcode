package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * LC 93 - Restore IP Addresses
 *
 * A valid IP address consists of exactly 4 integers separated by dots, where
 * each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
 *
 * Given a string s containing only digits, return all possible valid IP addresses
 * that can be formed by inserting dots into s. Do not reorder or remove any digits.
 *
 * Example 1:
 *   Input:  s="25525511135"
 *   Output: ["255.255.11.135","255.255.111.35"]
 *
 * Example 2:
 *   Input:  s="0000"
 *   Output: ["0.0.0.0"]
 *
 * Example 3:
 *   Input:  s="101023"
 *   Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * Constraints:
 *   1 <= s.length <= 20
 *   s consists of digits only.
 *
 * Approach: backtracking — try 1-3 digit segments per octet (dot), prune if
 *   value > 255 or has a leading zero. Stop when 4 octets used and string exhausted.
 * Time: O(1) — at most 3^4 = 81 combinations   Space: O(1)
 */

public class ValidateIP {

    public static void helper(String original, String ip, List<String> result, int dot) {
        if (dot > 4)
            return;

        if (dot == 4 && original.isEmpty()) {
            result.add(ip.substring(0, ip.length() - 1));
            return;
        }

        for (int i = 0; i < original.length(); i++) {
            String part = original.substring(0, i + 1);

            if (part.length() > 3 || Integer.parseInt(part) < 0 || Integer.parseInt(part) > 255)
                continue;

            if (part.length() > 1 && part.startsWith("0"))
                continue;

            String remaining = original.substring(i + 1);

            helper(remaining, ip + part + ".", result, dot + 1);
        }

    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        helper(s, "", result, 0);

        return result;
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }

}
