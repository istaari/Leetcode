package leetcode.dp.stock;

public class BuySellStock {

    public static int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int price : prices) {
            minPrice = Math.min(minPrice, price);

            if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

    public static int maxProfitDP(int[] prices) {
        int minPrice = prices[0];

        int[] dp = new int[prices.length + 1];
        dp[0] = 0;

        for (int i = 1; i <= prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i - 1]);

            dp[i] = Math.max(dp[i - 1], prices[i - 1] - minPrice);

        }

        return dp[prices.length];
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        //prices = new int[]{7, 6, 4, 3, 1};
        System.out.println(maxProfitDP(prices));
    }
}
