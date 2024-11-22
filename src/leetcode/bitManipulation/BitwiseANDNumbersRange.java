package leetcode.bitManipulation;

public class BitwiseANDNumbersRange {

    public static int rangeBitwiseAnd(int left, int right) {

        int shiftCount = 0;

        while (left != right) {
            left = left >> 1;
            right = right >> 1;
            shiftCount++;
        }

        left = left << shiftCount;

        return left;
    }


    public static void main(String[] args) {
        int left = 5;
        int right = 7;
        System.out.println(rangeBitwiseAnd(left, right));
    }


}
