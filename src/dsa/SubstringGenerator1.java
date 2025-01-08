package dsa;

import java.util.ArrayList;
import java.util.List;

public class SubstringGenerator1 {


    public static List<String> generateSubstrings(String s) {
        List<String> result = new ArrayList<>();
        generateSubstringsHelper(s, 0, s.length() - 1, result);
        return result;
    }

    private static void generateSubstringsHelper(String s, int start, int end, List<String> result) {
        // Base case: if start index exceeds end index, return
        if (start > end) {
            return;
        }

        // Add the current substring to the result
        result.add(s.substring(start, end + 1));

        // Divide and conquer
        // Generate substrings by reducing the range recursively
        generateSubstringsHelper(s, start + 1, end, result); // Move start forward
        generateSubstringsHelper(s, start, end - 1, result); // Move end backward
    }


    public static void main(String[] args) {
        String input = "abc";
        List<String> substrings = generateSubstrings(input);
        for (String substring : substrings) {
            System.out.println(substring);
        }
    }


}
