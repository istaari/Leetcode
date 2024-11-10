public class Solution {


    public static String reverseWords(String s) {
        String trimmed = s.trim();
        String[] words = trimmed.split(" ");

        int left = 0;
        int right = words.length - 1;

        while (left < right) {

            if (words[left].equals(" ")) {
                left++;
            } else if (words[right].equals(" ")) {
                right--;
            } else {
                String temp = words[left];
                words[left] = words[right];
                words[right] = temp;
                left++;
                right--;

            }
        }

        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(word).append(" ");
            }
        }


        return result.toString().trim();
    }

    public static void main(String[] args) {
        String s = "a good   example";
        System.out.println(reverseWords(s));
    }

}
