package dsa;

import java.util.ArrayList;
import java.util.List;

public class SubstringGenerator {

    public List<String> generateAllSubstrings(String input) {
        List<String> substrings = new ArrayList<>();
        generateSubstrings(input, substrings);
        return substrings;
    }

    private void generateSubstrings(String remaining, List<String> substrings) {
        // Base case: if the remaining string is empty
        if (remaining.isEmpty()) {
            return;
        }

        // Generate all substrings starting from the current string
        for (int end = 1; end <= remaining.length(); end++) {
            String substring = remaining.substring(0, end);
            substrings.add(substring);
        }

        // Recursively generate substrings for the remaining part of the string
        generateSubstrings(remaining.substring(1), substrings);
    }

    public static void main(String[] args) {
        SubstringGenerator generator = new SubstringGenerator();
        String input = "abcd";
        List<String> substrings = generator.generateAllSubstrings(input);

        System.out.println("All substrings of \"" + input + "\":");
        System.out.println(substrings);
    }
}

