package leetcode.array;

@SuppressWarnings("all")
public class SumSubarrayMinimums {

    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int sum = 0;
        int mod = 1_000_000_007;

        for (int start = 0; start < n; start++) {
            int min = Integer.MAX_VALUE;

            for (int end = start; end < n; end++) {
                min = Math.min(min, arr[end]);
                sum = (sum + min) % mod;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {11, 81, 94, 43, 3};
        System.out.println(sumSubarrayMins(arr));
        arr = new int[]{3, 1, 2, 4};
        System.out.println(sumSubarrayMins(arr));
    }

}
