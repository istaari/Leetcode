package leetcode.dp.stock;

/**
 * 123. Best Time to Buy and Sell Stock III
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * You are given an array prices where prices[i] is the price of a given stock
 * on the i-th day. Find the maximum profit you can achieve. You may complete
 * at most two transactions. You may not engage in multiple transactions
 * simultaneously (you must sell the stock before you buy again).
 *
 * Example 1: Input: prices = [3,3,5,0,0,3,1,4] -> Output: 6
 *   Explanation: Buy day 4 (price=0), sell day 6 (price=3), profit=3.
 *                Buy day 7 (price=1), sell day 8 (price=4), profit=3. Total=6.
 *
 * Example 2: Input: prices = [1,2,3,4,5] -> Output: 4
 * Example 3: Input: prices = [7,6,4,3,1] -> Output: 0
 *
 * Constraints:
 *   1 <= prices.length <= 10^5
 *   0 <= prices[i] <= 10^5
 *
 * ---
 * Approach: State Machine / DP in O(1) space
 *
 * Track 4 variables across one pass:
 *   minPrice1: cheapest buy for transaction 1
 *   profit1:   max profit after selling transaction 1
 *   minPrice2: effective cheapest buy for transaction 2 (price - profit1)
 *   profit2:   max profit after selling transaction 2
 *
 * The trick: minPrice2 = min(price - profit1) accounts for the profit
 * already earned from the first transaction, so profit2 captures the
 * combined profit of both transactions.
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class BestTimeBuySellStock_3 {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int minPrice1 = Integer.MAX_VALUE;  // Min buy price for 1st transaction
        int minPrice2 = Integer.MAX_VALUE;  // Effective min buy price for 2nd transaction
        int profit1 = 0;                     // Max profit from 1st transaction
        int profit2 = 0;                     // Max profit from both transactions

        for (int price : prices) {
            // Transaction 1: find best single buy-sell
            minPrice1 = Math.min(minPrice1, price);
            profit1 = Math.max(profit1, price - minPrice1);

            // Transaction 2: effective buy price = price - profit1
            // This "reinvests" profit1 so profit2 captures total of both
            minPrice2 = Math.min(minPrice2, price - profit1);
            profit2 = Math.max(profit2, price - minPrice2);
        }

        return profit2;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = { 3, 3, 5, 0, 0, 3, 1, 4 };
        System.out.println(maxProfit(prices1)); // Output: 6

        // Test case 2
        int[] prices2 = { 1, 2, 3, 4, 5 };
        System.out.println(maxProfit(prices2)); // Output: 4

        // Test case 3
        int[] prices3 = { 7, 6, 4, 3, 1 };
        System.out.println(maxProfit(prices3)); // Output: 0
    }
}
