package leetcode.math;

public class ReverseInteger {

    public static int reverse(int x) {

        int result = 0;

        while (x != 0) {
            int last = (x % 10);
            int newResult = (result * 10) + last;

            if (newResult / 10 != result) return 0;

            result = newResult;
            x = x / 10;
        }

        return result;
    }

    public static void main(String[] args) {
        int x = 127;
        System.out.println(reverse(x));
    }

}
