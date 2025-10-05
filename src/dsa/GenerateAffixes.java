package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateAffixes {


    public static List<List<String>> generateAffixes(String s) {
        int n = s.length();
        List<String> prefixes = new ArrayList<>();
        List<String> suffixes = new ArrayList<>();

        if (n == 0) {
            // Return a list containing two empty lists
            return Arrays.asList(prefixes, suffixes);
        }

        // A brute-force loop from 1 up to the length of the string - 1.
        // The loop variable 'i' represents the length of the prefix/suffix.
        for (int i = 1; i < n; i++) {
            // Prefixes are substrings from the beginning of the string.
            // s.substring(0, i) creates a substring from index 0 up to (but not including) i.
            prefixes.add(s.substring(0, i));

            // Suffixes are substrings from the end of the string.
            // s.substring(n - i) creates a substring from index (n - i) to the end.
            // For "level" (n=5):
            // i=1 -> s.substring(4) -> "l"
            // i=2 -> s.substring(3) -> "el"
            // i=3 -> s.substring(2) -> "vel"
            // i=4 -> s.substring(1) -> "evel"
            suffixes.add(s.substring(n - i));
        }

        return Arrays.asList(prefixes, suffixes);
    }

    // --- Main execution method ---
    public static void main(String[] args) {
        String inputString = "level";

        // Call the method to get the lists
        List<List<String>> affixes = generateAffixes(inputString);
        List<String> listOfPrefixes = affixes.get(0);
        List<String> listOfSuffixes = affixes.get(1);

        // Print the results in a clear format
        System.out.println("Original String: \"" + inputString + "\"");
        System.out.println("-------------------------");
        System.out.println("Prefixes: " + listOfPrefixes);
        System.out.println("Suffixes: " + listOfSuffixes);

        System.out.println("\n=========================\n");

        // Another example
        String inputString2 = "banana";
        List<List<String>> affixes2 = generateAffixes(inputString2);
        List<String> listOfPrefixes2 = affixes2.get(0);
        List<String> listOfSuffixes2 = affixes2.get(1);

        System.out.println("Original String: \"" + inputString2 + "\"");
        System.out.println("-------------------------");
        System.out.println("Prefixes: " + listOfPrefixes2);
        System.out.println("Suffixes: " + listOfSuffixes2);
    }
}

