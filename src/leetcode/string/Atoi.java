package leetcode.string;

public class Atoi {

    public static int myAtoi(String s) {
        s = s.trim();
        int i = 0;

        char sign = '+';
        if (s.charAt(i) == '-') {
            sign = '-';
            i++;
        }

        int result = 0;

        for (int k = i; k < s.length(); k++) {

            if (!Character.isDigit(s.charAt(k))) break;

            int digit = s.charAt(k) - '0';

            if (Integer.MAX_VALUE / 10 < result || Integer.MAX_VALUE / 10 == result && Integer.MAX_VALUE % 10 < digit) {
                return sign == '+' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = (result * 10) + digit;
        }


        return (sign == '+') ? result : -result;
    }

    public static void main(String[] args) {
        String s = " -042";
        // s = "0-1";
        //s = "words and 987";
        //s = "1337c0d3";
        //s = "4193 with words";
        s = "-91283472332";
        System.out.println(myAtoi(s));
    }
}
