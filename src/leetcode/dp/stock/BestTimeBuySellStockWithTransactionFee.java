package leetcode.dp.stock;

/**
 * 714. Best Time to Buy and Sell Stock with Transaction Fee
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 * You are given an array prices where prices[i] is the price of a given stock
 * on the i-th day, and an integer fee representing a transaction fee.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions
 * as you like, but you need to pay the transaction fee for each transaction.
 * You may not engage in multiple transactions simultaneously (you must sell the
 * stock before you buy again).
 *
 * The transaction fee is only charged once for each stock purchase and sale.
 *
 * Example 1:
 *   Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 *   Output: 8
 *   Explanation: Buy at 1, sell at 8 (profit 8-1-2=5), buy at 4, sell at 9 (profit 9-4-2=3)
 *                Total = 5 + 3 = 8
 *
 * Example 2:
 *   Input: prices = [1, 3, 7, 5, 10, 3], fee = 3
 *   Output: 6
 *
 * Constraints:
 *   1 <= prices.length <= 5 * 10^4
 *   1 <= prices[i] < 5 * 10^4
 *   0 <= fee < 5 * 10^4
 *
 * ---
 * Approach: State Machine DP (same as Buy/Sell Stock II, but subtract fee on sell)
 *
 * Two states at each day:
 *   cash  = max profit when NOT holding stock
 *   hold  = max profit when HOLDING stock
 *
 * Transitions:
 *   cash = max(cash, hold + prices[i] - fee)   // rest or sell (pay fee)
 *   hold = max(hold, cash - prices[i])          // rest or buy
 *
 * Base: cash = 0, hold = -prices[0] (buy on day 0)
 *
 * This is identical to BestTimeBuySellStock_2 with unlimited transactions,
 * except we subtract 'fee' each time we sell.
 *
 * Trace: prices = [1, 3, 2, 8, 4, 9], fee = 2
 *
 *   Day 0 (price=1): cash=0,  hold=-1
 *   Day 1 (price=3): cash=max(0, -1+3-2)=0,   hold=max(-1, 0-3)=-1
 *   Day 2 (price=2): cash=max(0, -1+2-2)=0,    hold=max(-1, 0-2)=-1
 *   Day 3 (price=8): cash=max(0, -1+8-2)=5,    hold=max(-1, 5-8)=-1
 *   Day 4 (price=4): cash=max(5, -1+4-2)=5,    hold=max(-1, 5-4)=1
 *   Day 5 (price=9): cash=max(5, 1+9-2)=8,     hold=max(1, 8-9)=1
 *   Answer: cash = 8 ✓
 *
 * Time:  O(n)
 * Space: O(1)
 */
public class BestTimeBuySellStockWithTransactionFee {

    // -------------------- Optimal O(1) Space --------------------
    public static int maxProfit(int[] prices, int fee) {
        // cash: best profit ending in "not holding" state
        // hold: best profit ending in "holding" state
        int cash = 0;
        int hold = -prices[0]; // Buy on day 0

        for (int i = 1; i < prices.length; i++) {
            // Sell: transition from hold -> cash, pay fee
            int newCash = Math.max(cash, hold + prices[i] - fee);
            // Buy: transition from cash -> hold
            int newHold = Math.max(hold, cash - prices[i]);

            cash = newCash;
            hold = newHold;
        }

        // Must end in "not holding" state for maximum profit
        return cash;
    }

    // -------------------- Recursive + Memoization --------------------
    public static int maxProfitRecursive(int[] prices, int fee) {
        int[][] memo = new int[prices.length][2];
        for (int[] row : memo) java.util.Arrays.fill(row, -1);
        return solve(prices, fee, 0, 0, memo);
    }

    /**
     * @param idx   current day index
     * @param hold  0 = not holding, 1 = holding
     */
    private static int solve(int[] prices, int fee, int idx, int hold, int[][] memo) {
        if (idx == prices.length) return 0;
        if (memo[idx][hold] != -1) return memo[idx][hold];

        // Option 1: Do nothing (rest)
        int rest = solve(prices, fee, idx + 1, hold, memo);

        if (hold == 1) {
            // Option 2: Sell today (pay fee)
            int sell = prices[idx] - fee + solve(prices, fee, idx + 1, 0, memo);
            memo[idx][hold] = Math.max(rest, sell);
        } else {
            // Option 2: Buy today
            int buy = -prices[idx] + solve(prices, fee, idx + 1, 1, memo);
            memo[idx][hold] = Math.max(rest, buy);
        }

        return memo[idx][hold];
    }

    public static void main(String[] args) {
        int[] prices1 = {1, 3, 2, 8, 4, 9};
        System.out.println("Example 1: " + maxProfit(prices1, 2));          // 8
        System.out.println("Recursive: " + maxProfitRecursive(prices1, 2)); // 8

        int[] prices2 = {1, 3, 7, 5, 10, 3};
        System.out.println("Example 2: " + maxProfit(prices2, 3));          // 6
    }
}
