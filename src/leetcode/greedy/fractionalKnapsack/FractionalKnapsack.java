package leetcode.greedy.fractionalKnapsack;

import java.util.Arrays;

/**
 * Fractional Knapsack Problem (Classic Greedy)
 *
 * Given weights and values of n items, put these items in a knapsack of
 * capacity W to get the maximum total value. You CAN break items (take fractions).
 *
 * This is the key difference from 0/1 Knapsack (which is DP):
 *   - Fractional Knapsack: greedy works (take highest value/weight ratio first)
 *   - 0/1 Knapsack: greedy does NOT work (need DP)
 *
 * Example:
 *   Input: values = [60, 100, 120], weights = [10, 20, 30], W = 50
 *   Output: 240.0
 *   Explanation:
 *     Item 0: value/weight = 6.0
 *     Item 1: value/weight = 5.0
 *     Item 2: value/weight = 4.0
 *     Take all of item 0 (10 kg, value 60), all of item 1 (20 kg, value 100),
 *     and 2/3 of item 2 (20 kg, value 80). Total = 60 + 100 + 80 = 240.
 *
 * ---
 * Approach: Greedy — sort by value/weight ratio descending
 *
 * 1. Compute value-to-weight ratio for each item.
 * 2. Sort items by ratio in descending order.
 * 3. Greedily take as much as possible of the highest-ratio item first.
 *    If the item fits entirely, take it all. Otherwise, take a fraction.
 *
 * Greedy choice property: taking the highest ratio item first always leads to
 * an optimal solution (provable by exchange argument).
 *
 * Time:  O(n log n) for sorting
 * Space: O(n) for the items array
 */
public class FractionalKnapsack {

    public static double fractionalKnapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;

        // Create items as [value, weight, ratio] and sort by ratio descending
        double[][] items = new double[n][3];
        for (int i = 0; i < n; i++) {
            items[i][0] = values[i];
            items[i][1] = weights[i];
            items[i][2] = (double) values[i] / weights[i];
        }

        // Sort by value/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b[2], a[2]));

        double totalValue = 0;
        int remainingCapacity = capacity;

        for (double[] item : items) {
            double weight = item[1];
            double value = item[0];

            if (remainingCapacity >= weight) {
                // Take the whole item
                totalValue += value;
                remainingCapacity -= (int) weight;
            } else {
                // Take fraction of the item that fits
                totalValue += value * ((double) remainingCapacity / weight);
                break; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int W = 50;
        System.out.println("Max value: " + fractionalKnapsack(values, weights, W)); // 240.0

        // Example 2: all items fit
        int[] v2 = {10, 20};
        int[] w2 = {5, 10};
        System.out.println("Max value: " + fractionalKnapsack(v2, w2, 20)); // 30.0
    }
}
