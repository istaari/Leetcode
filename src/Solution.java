public class Solution {

    public static int getNumeric(String word) {

        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i))) {
                return word.length();
            }
        }

        return Integer.parseInt(word);
    }

    public static int maximumValue(String[] strs) {
        int max = 0;

        for (String word : strs) {
            max = Math.max(max, getNumeric(word));
        }

        return max;
    }

    public static void main(String[] args) {
        String[] strs = { "alic3", "bob", "3", "4", "00000" };
        System.out.println(maximumValue(strs));
    }

}
