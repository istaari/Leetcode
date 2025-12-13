package leetcode.dp.stock;

public class BestTimeBuySellStock_2 {

    public static int maxProfit(int[] prices) {
        int profit = 0;

        // Loop through the array and add profits when the next day's price is higher
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
