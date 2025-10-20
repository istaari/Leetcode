package leetcode.bit;

public class PrimeNumberOfSetBits {

    public static boolean isPrime(int number) {
        // Check for numbers less than 2
        if (number <= 1) {
            return false;
        }

        // Check for factors from 2 to the square root of num
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static int countPrimeSetBits(int left, int right) {
        int result = 0;

        for (int i = left; i <= right; i++) {
            int count = 0;

            for (int j = 0; j < 32; j++) {
                if (((i >> j) & 1) != 0) {
                    count++;
                }
            }

            if (isPrime(count)) result++;
        }

        return result;
    }

    public static void main(String[] args) {
        int left = 10;
        int right = 15;
        System.out.println(countPrimeSetBits(left, right));
    }
}
