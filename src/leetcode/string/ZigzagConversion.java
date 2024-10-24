package leetcode.string;

public class ZigzagConversion {

    public static String convert(String s, int numRows) {

        StringBuilder[] stringBuilders = new StringBuilder[numRows];

        for (int i = 0; i < numRows; i++) {
            stringBuilders[i] = new StringBuilder();
        }

        int i = 0;
        int size = s.length();

        while (i < size) {

            for (int index = 0; index < numRows && i < size; index++) {
                stringBuilders[index].append(s.charAt(i));
                i++;
            }

            for (int index = numRows - 2; index >=1 && i < size; index--) {
                stringBuilders[index].append(s.charAt(i));
                i++;
            }

        }

        for (int k = 1; k < numRows; k++) {
            stringBuilders[0].append(stringBuilders[k]);
        }

        return stringBuilders[0].toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
        System.out.println(convert(s, numRows)); // PAHNAPLSIIGYIR
    }

}
