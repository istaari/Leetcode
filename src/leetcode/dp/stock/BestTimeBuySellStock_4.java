package leetcode.dp.stock;

import java.util.Arrays;

/**
 * 188. Best Time to Buy and Sell Stock IV
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * You are given an integer array prices where prices[i] is the price of a
 * given stock on the i-th day, and an integer k.
 * Find the maximum profit you can achieve with at most k transactions.
 * You may not hold more than one share at a time.
 *
 * Example 1: Input: k = 2, prices = [2,4,1] -> Output: 2
 *   Explanation: Buy day 1 (price=2), sell day 2 (price=4), profit = 2.
 *
 * Example 2: Input: k = 2, prices = [3,2,6,5,0,3] -> Output: 7
 *   Explanation: Buy day 2, sell day 3 (profit 4). Buy day 5, sell day 6 (profit 3). Total=7.
 *
 * Constraints:
 *   1 <= k <= 100
 *   1 <= prices.length <= 1000
 *   0 <= prices[i] <= 1000
 *
 * ---
 * Approach: DP with buy[] and sell[] arrays
 *
 * Generalization of Stock III. Maintain k buy/sell states:
 *   buy[i]  = max profit when holding stock after i-th buy
 *   sell[i] = max profit when NOT holding stock after i-th sell
 *
 * For each price, update all k transactions:
 *   buy[i]  = max(buy[i], sell[i-1] - price)  // buy using profit from previous sell
 *   sell[i] = max(sell[i], buy[i] + price)     // sell the stock we hold
 *
 * Time:  O(n * k)
 * Space: O(k)
 */
public class BestTimeBuySellStock_4 {

    public static int maxProfit(int k, int[] prices) {
        int[] buy = new int[k + 1];   // buy[i] = max profit in "holding" state for i-th transaction
        int[] sell = new int[k + 1];  // sell[i] = max profit in "sold" state for i-th transaction

        // Initialize buy states to -infinity (haven't bought yet)
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                // Either keep holding, or buy today using previous sell's profit
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                // Either keep waiting, or sell today
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];

    }

    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = { 2, 4, 1 };
        System.out.println(maxProfit(2, prices1)); // Output: 2

        // Test case 2
        int[] prices2 = { 3, 2, 6, 5, 0, 3 };
        System.out.println(maxProfit(2, prices2)); // Output: 7
    }
}
