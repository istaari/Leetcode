package leetcode.math;

public class CatalanNumber {


    public long binomialCoefficient(int n, int k) {
        long res = 1;

        if (k > n - k) k = n - k;  // Using the property: C(n, k) = C(n, n-k)

        for (int i = 0; i < k; i++) {
            res *= (n - i);  // Multiply by decreasing numerator
            res /= (i + 1); //  Divide by increasing denominator

            // Using the property of the associativity of multiplication and division:
            // (a / b) × (c / d) = (a × c) / (b × d)
        }

        return res;
    }


    public int catalanNumber(int n) {
        return (int) (binomialCoefficient(2 * n, n) / (n + 1));
    }

    public static void main(String[] args) {
        CatalanNumber sol = new CatalanNumber();
        int n = 5; // Example input
        System.out.println("Catalan number C(" + n + ") = " + sol.catalanNumber(n));
    }

}
