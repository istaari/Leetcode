package leetcode.stack;

import java.util.Stack;

public class DecodeString {


    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        String result = "";

        for (int i = 0; i < s.length(); ) {

            if (Character.isDigit(s.charAt(i))) {

                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = count * 10 + (s.charAt(i) - '0');
                    i++;
                }
                countStack.push(count);


            } else if (s.charAt(i) == '[') {
                stringStack.push(result);
                result = "";

                i++;

            } else if (s.charAt(i) == ']') {

                StringBuilder temp = new StringBuilder(stringStack.pop());
                int repeatTimes = countStack.pop();
                temp.append(result.repeat(repeatTimes));

                result = temp.toString();
                i++;

            } else {
                result = result + s.charAt(i);
                i++;
            }

        }

        return result;
    }

    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        //s = "2[bc]";
        System.out.println(decodeString(s));
    }
}
