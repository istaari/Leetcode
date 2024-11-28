package leetcode.array;

public class OddLengthSubarrays {

    public int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int contribution = ((i + 1) * (n - i) + 1) / 2;
            sum += arr[i] * contribution;
        }

        return sum;
    }

}
