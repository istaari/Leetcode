package leetcode.greedy;


// You need to build an array nums of length n such that:

// 1. Each nums[i] > 0 (positive integers).
// 2. Adjacent elements differ by at most 1:
// 3. That is, |nums[i] - nums[i+1]| <= 1.
// 4. The total sum of nums is at most maxSum.
// 5. You want to maximize nums[index] (the element at the given position).

public class MaxValueGivenIndexBoundedArray {

    public int maxValue(int n, int index, int maxSum) {
        int l = 1;
        int r = maxSum;
        int ans = 0;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (isValid(n, index, maxSum, mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }

    public boolean isValid(int n, int index, int maxSum, int peak) {
        long leftSum = calculateSum0(peak - 1, index);
        long rightSum = calculateSum0(peak - 1, n - index - 1);
        return (leftSum + rightSum + peak) <= maxSum;
    }

    // Using loop
    public long calculateSum(int peak, int length) {
        long sum = 0;
        for (int i = 0; i < length; i++) {
            if (peak > 0) {
                sum += peak;
                peak--;
            } else {
                sum += 1;
            }
        }

        return sum;
    }

    // Using Formula
    public long calculateSum0(int peak, int length) {
        if (length == 0) return 0;

        // If peak is greater than length, we have a decreasing sequence from peak to (peak-length+1)
        if (peak >= length) {
            // Sum of arithmetic progression: n(first + last)/2
            long first = peak;
            long last = peak - length + 1;
            return length * (first + last) / 2;
        } else {
            // We have some decreasing values (from peak to 1) followed by 1s
            // Sum of decreasing part: peak*(peak+1)/2
            // Sum of 1s part: (length-peak) * 1
            return (long) peak * (peak + 1) / 2 + (length - peak);
        }
    }

    public static void main(String[] args) {
        MaxValueGivenIndexBoundedArray solution = new MaxValueGivenIndexBoundedArray();
        System.out.println(solution.maxValue(4, 2, 6)); // Output: 2
        System.out.println(solution.maxValue(6, 1, 10)); // Output: 3
        System.out.println(solution.maxValue(6, 2, 931384943)); // Output: 155230825
    }

}