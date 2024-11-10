package leetcode.array;

import java.util.Arrays;

public class MaximumGap {

    public static int maximumGap(int[] nums) {

        if (nums.length < 2) return 0;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int val : nums) {
            max = Math.max(max, val);
            min = Math.min(min, val);
        }

        // creating range to distribute the elements in the buckets
        int bucketSize = Math.max(1, (max - min) / (nums.length - 1)); // n - 1 gaps
        int bucketCount = (max - min) / bucketSize + 1; // Correct bucket count

        // Create buckets to store min and max values for each bucket
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // Fill the buckets
        for (int val : nums) {
            int bucketIndex = (val - min) / bucketSize;
            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], val);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], val);
        }


        int i = 0;
        while (i < bucketCount && bucketMin[i] == Integer.MAX_VALUE) {
            i++;
        }

        int prevMax = bucketMax[i];
        int maxGap = 0;

        for (int j = i + 1; j < bucketCount; j++) {
            if (bucketMin[j] == Integer.MAX_VALUE) {
                continue;
            }

            maxGap = Math.max(maxGap, bucketMin[j] - prevMax);
            prevMax = bucketMax[j];
        }

        return maxGap;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 6, 9};
        System.out.println(maximumGap(nums));
    }


}
