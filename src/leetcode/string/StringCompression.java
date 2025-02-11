package leetcode.string;

public class StringCompression {

    public static int compress(char[] chars) {
        StringBuilder result = new StringBuilder();
        int n = chars.length;
        int count = 1;

        for (int i = n - 1; i >= 0; i--) {
            if (i - 1 < 0 || chars[i - 1] != chars[i]) {
                String charCount = count == 1 ? String.valueOf(chars[i]) : String.valueOf(chars[i]) + count;
                result.insert(0, charCount);
                count = 1;
                continue;
            }

            count++;
        }

        int len = result.length();
        int index = 0;
        for (int i = 0; i < len; i++) {
            chars[index++] = result.charAt(i);
        }

        return result.length();
    }


    public static int compressOptimized(char[] chars) {
        int index = 0;
        int i = 0;

        while (i < chars.length) {
            char currentChar = chars[i];
            int count = 0;

            // This loop counts the character with current character while traversing forward
            while (i < chars.length && chars[i] == currentChar) {
                count++;
                i++;
            }

            // Store the character
            chars[index++] = currentChar;

            // If count > 1, store the digits of count
            if (count > 1) {
                String countString = String.valueOf(count);

                for (int j = 0; j < countString.length(); j++) {
                    chars[index++] = countString.charAt(j);
                }
            }
        }

        return index;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        System.out.println(compressOptimized(chars));
    }
}
