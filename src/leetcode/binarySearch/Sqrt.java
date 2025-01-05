package leetcode.binarySearch;

public class Sqrt {

    public static int mySqrt(int x) {
        if (x == 0) return 0;

        int low = 1;
        int high = x;
        int result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            // Optimization : mid <= x / mid
            if (mid * mid <= x) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }

        }

        return result;
    }

    public static void main(String[] args) {
        int x = 5;
        System.out.println(mySqrt(x));
    }

}
