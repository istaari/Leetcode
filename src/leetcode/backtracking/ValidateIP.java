package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class ValidateIP {

    public static void helper(String original, String ip, List<String> result, int dot) {
        if (dot > 4) return;

        if (dot == 4 && original.isEmpty()) {
            result.add(ip.substring(0, ip.length() - 1));
            return;
        }

        for (int i = 0; i < original.length(); i++) {

            String part = original.substring(0, i + 1);

            if (part.length() > 3 || Integer.parseInt(part) < 0 || Integer.parseInt(part) > 255) continue;

            if (part.length() > 1 && part.startsWith("0")) continue;

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
