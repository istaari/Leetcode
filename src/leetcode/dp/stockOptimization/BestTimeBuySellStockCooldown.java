package leetcode.dp.stockOptimization;

public class BestTimeBuySellStockCooldown {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;

        int hold = -prices[0];
        int sold = 0;
        int cooldown = 0;

        for (int i = 1; i < n; i++) {
            int prevHold = hold;

            hold = Math.max(hold, cooldown - prices[i]); // Total profit when holding a stock or moving from cooldown
            cooldown = Math.max(cooldown, sold); // Total profit when in cooldown
            sold = prevHold + prices[i]; // Total profit when you sold the stock
        }

        return Math.max(sold, cooldown);
    }

    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices1));  // Output: 3

        // Test case 2
        int[] prices2 = {1};
        System.out.println(maxProfit(prices2));  // Output: 0
    }


}
