package leetcode.math;

public class FactorialTrailingZeroes {

    public static int trailingZeroes(int n) {
        int count = 0;

        for (int i = 5; n / i >= 1; i = i * 5) {
            count += n / i;
        }

        return count;
    }

    public static void main(String[] args) {
        int n = 50;
        System.out.println(trailingZeroes(n));
    }

}
