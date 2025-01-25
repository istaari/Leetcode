package leetcode.string;

public class StringCompression {

    public static int compress(char[] chars) {
        StringBuilder result = new StringBuilder();
        int n = chars.length;
        int count = 1;

        for (int i = 0; i <= n; i++) {

            if (i + 1 < n && chars[i] == chars[i + 1]) {
                count++;
            } else {

                if (i < n) {

                    if (count > 1) {
                        result.append(chars[i]).append(count);
                        chars[i] = (char) (count + '0');
                        count = 1;
                    } else {
                        result.append(chars[i]);
                    }
                }
            }

        }

        return result.length();
    }

    public static void main(String[] args){
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        System.out.println(compress(chars));
    }
}
