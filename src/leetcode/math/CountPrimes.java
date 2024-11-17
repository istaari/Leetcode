package leetcode.math;

import java.util.Arrays;

public class CountPrimes {

    public int countPrimes(int n) {

        if (n == 0 || n == 1) return 0;

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;
        int count = 0;

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {

                for (int j = i * i; j < n; j = j + i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }

        return count;
    }

}
