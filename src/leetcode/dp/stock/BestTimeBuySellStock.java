package leetcode.dp.stock;

/**
 * 121. Best Time to Buy and Sell Stock
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * You are given an array prices where prices[i] is the price of a given stock
 * on the i-th day. You want to maximize your profit by choosing a single day
 * to buy and a single day in the future to sell. Return the maximum profit.
 * If you cannot achieve any profit, return 0.
 *
 * Example 1: Input: prices = [7,1,5,3,6,4] -> Output: 5
 *   Explanation: Buy on day 2 (price=1) and sell on day 5 (price=6), profit = 5.
 *
 * Example 2: Input: prices = [7,6,4,3,1] -> Output: 0
 *
 * Constraints:
 *   1 <= prices.length <= 10^5
 *   0 <= prices[i] <= 10^4
 *
 * ---
 * Approach: Greedy / One-pass
 *
 * Track the minimum price seen so far. At each day, the max profit is
 * current price - minimum price seen. Keep a running maximum.
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class BestTimeBuySellStock {

    public static int maxProfit(int[] prices) {
        int minPrice = prices[0];  // Minimum buy price seen so far
        int maxProfit = 0;         // Best profit achievable

        for (int price : prices) {
            // Update the cheapest buy price
            minPrice = Math.min(minPrice, price);

            // Check if selling today gives a better profit
            if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = { 7, 1, 5, 3, 6, 4 };
        System.out.println(maxProfit(prices));
    }

}
