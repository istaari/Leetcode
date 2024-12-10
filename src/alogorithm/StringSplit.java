package alogorithm;

public class StringSplit {

    public static void main(String[] args) {
        // String with common wild characters, including '.'
        String input = "hello.world*java?is[awesome]today";

        // Split by '.', '*', '?', '[', or ']'
        // Using a character class: [\\.\\*\\?\\[\\]]
        String[] parts = input.split("[\\.\\*\\?\\[\\]]");

        // Print the resulting parts
        for (String part : parts) {
            System.out.println(part);
        }

    }
}
