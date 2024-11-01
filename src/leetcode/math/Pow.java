package leetcode.math;

public class Pow {

    public static void main(String[] args) {
        double x = 2.00000;
        int n = 10;

        System.out.println(myPow(x, n));
    }

    public static double myPow(double x, int n) {
        double result = 1.0;
        boolean isNegative = n < 0;

        if (isNegative) {
            n = -(n); //change to positive
        }

        while (n != 0) {

            if (n % 2 != 0) {
                result = result * x;
            }

            x = x * x;
            n = n / 2;
        }

        return isNegative ? 1 / result : result;
    }
}