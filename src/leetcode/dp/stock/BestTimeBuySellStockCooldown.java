package leetcode.dp.stock;

/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * You are given an array prices where prices[i] is the price on the i-th day.
 * Find the maximum profit with as many transactions as you like, but after
 * selling you must wait one day before buying again (cooldown of 1 day).
 *
 * Example 1: Input: prices = [1,2,3,0,2] -> Output: 3
 *   Explanation: [buy, sell, cooldown, buy, sell]
 *
 * Example 2: Input: prices = [1] -> Output: 0
 *
 * Constraints:
 *   1 <= prices.length <= 5000
 *   0 <= prices[i] <= 1000
 *
 * ---
 * Approach: State Machine DP with 3 states
 *
 * On each day, you are in one of three states:
 *   hold:     holding a stock (bought previously or today)
 *   sold:     just sold a stock today
 *   cooldown: not holding, did NOT sell today (either idle or post-cooldown)
 *
 * Transitions:
 *   hold     = max(hold, cooldown - price)  // keep holding OR buy today from cooldown
 *   sold     = hold + price                 // sell the stock we hold
 *   cooldown = max(cooldown, sold)           // stay idle OR transition from yesterday's sold
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class BestTimeBuySellStockCooldown {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int n = prices.length;

        int hold = -prices[0];  // Bought on day 0
        int sold = 0;           // Haven't sold anything yet
        int cooldown = 0;       // Haven't done anything yet

        for (int i = 1; i < n; i++) {
            int prevHold = hold;

            // hold: keep holding OR buy today (must come from cooldown state)
            hold = Math.max(hold, cooldown - prices[i]);
            // cooldown: stay in cooldown OR move from yesterday's sold
            cooldown = Math.max(cooldown, sold);
            // sold: sell the stock we're holding today
            sold = prevHold + prices[i];
        }

        // Answer is max of sold or cooldown (never profitable to end holding)
        return Math.max(sold, cooldown);
    }

    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = { 1, 2, 3, 0, 2 };
        System.out.println(maxProfit(prices1)); // Output: 3

        // Test case 2
        int[] prices2 = { 1 };
        System.out.println(maxProfit(prices2)); // Output: 0
    }

}
