package dsa;

import java.util.ArrayList;
import java.util.List;

public class CartesianProduct {

    /**
     * Generates the Cartesian Product of multiple input strings.
     * <p>
     * This method combines each character from the input strings with all existing combinations.
     * <p>
     * 1. Initialization: Start with result = [""]
     * <p>
     * 2. Processing: For each input string, create a new list temp to store combinations.
     * <p>
     * 3. Combination Generation: Append each character of the current input string to each string in result.
     * Example:
     * <p>
     * With result = [""] and input "abc", temp becomes ["a", "b", "c"].
     * <p>
     * With result = ["a", "b", "c"] and input "def", temp becomes ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * <p>
     * The final result is a list of all possible combinations from the input strings.
     */
    public static List<String> generateCartesianProduct(String[] inputs) {
        List<String> combinations = new ArrayList<>();
        combinations.add("");

        // Loop over each input string
        for (String input : inputs) {
            List<String> currentCombinations = new ArrayList<>();

            for (String prefix : combinations) {
                for (char ch : input.toCharArray()) {
                    currentCombinations.add(prefix + ch);
                }
            }

            combinations = currentCombinations;
        }

        return combinations;
    }

    public static void main(String[] args) {
        String[] inputs = new String[]{"abc", "def", "ghi"};
        List<String> result = generateCartesianProduct(inputs);
        System.out.println(result);
    }


}
