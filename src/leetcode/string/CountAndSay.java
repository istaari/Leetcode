package leetcode.string;

public class CountAndSay {

    public static String consecutiveOccurrences(String seq) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < seq.length(); i++) {
            int count = 1;
            char ch = seq.charAt(i);

            while (i + 1 < seq.length() && seq.charAt(i + 1) == ch) {
                count++;
                i++;
            }

            result.append(count).append(ch);
        }

        return result.toString();
    }

    public static String countAndSay(int n) {
        if (n == 1) return "1"; // base case

        String seq = countAndSay(n - 1);

        return consecutiveOccurrences(seq);
    }


    public static String countAndSayIterative(int n) {
        String result = "1";

        if (n == 1) return result;

        for (int i = 0; i < n - 1; i++) {
            result = consecutiveOccurrences(result);
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 4;

        System.out.println(countAndSay(n));  //  Output: "1211"
        System.out.println(countAndSayIterative(n));  //  Output: "1211"

    }

}
