package dsa;

import java.util.ArrayList;
import java.util.List;

public class SubstringGenerator0 {

    public static List<String> generateSubstrings(String str) {
        List<String> result = new ArrayList<>();

        for (int start = 0; start < str.length(); start++) {
            for (int end = start + 1; end <= str.length(); end++) {
                result.add(str.substring(start, end));
            }
        }

        return result;
    }
    

    public static void main(String[] args) {
        String str = "abcd";
        List<String> substrings = generateSubstrings(str);

        System.out.println("All substrings of \"" + str + "\":");
        for (String substring : substrings) {
            System.out.println(substring);
        }

        System.out.println("\nTotal number of substrings: " + substrings.size());
    }
}
