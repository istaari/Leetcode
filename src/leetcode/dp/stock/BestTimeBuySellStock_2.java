package leetcode.dp.stock;

/**
 * 122. Best Time to Buy and Sell Stock II
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * You are given an integer array prices where prices[i] is the price of a
 * given stock on the i-th day. On each day, you may decide to buy and/or
 * sell the stock. You can hold at most one share at a time. You can buy and
 * sell on the same day. Find the maximum profit you can achieve.
 *
 * Example 1: Input: prices = [7,1,5,3,6,4] -> Output: 7
 *   Explanation: Buy day 2, sell day 3 (profit 4). Buy day 4, sell day 5 (profit 3). Total = 7.
 *
 * Example 2: Input: prices = [1,2,3,4,5] -> Output: 4
 * Example 3: Input: prices = [7,6,4,3,1] -> Output: 0
 *
 * Constraints:
 *   1 <= prices.length <= 3 * 10^4
 *   0 <= prices[i] <= 10^4
 *
 * ---
 * Approach: Greedy
 *
 * Collect every upward price movement. Whenever tomorrow's price is higher
 * than today's, add the difference as profit. This is equivalent to buying
 * and selling on every consecutive profitable pair.
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class BestTimeBuySellStock_2 {

    public static int maxProfit(int[] prices) {
        int profit = 0;

        // Accumulate profit from every upward price movement
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = { 7, 1, 5, 3, 6, 4 };
        System.out.println(maxProfit(prices1)); // Output: 7

        // Test case 2
        int[] prices2 = { 1, 2, 3, 4, 5 };
        System.out.println(maxProfit(prices2)); // Output: 4

        // Test case 3
        int[] prices3 = { 7, 6, 4, 3, 1 };
        System.out.println(maxProfit(prices3)); // Output: 0
    }
}
