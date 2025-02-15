package leetcode.dp.stock;

public class BestTimeBuySellStock_3 {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int minPrice1 = Integer.MAX_VALUE;
        int minPrice2 = Integer.MAX_VALUE;
        int profit1 = 0;
        int profit2 = 0;

        for (int price : prices) {
            minPrice1 = Math.min(minPrice1, price);
            profit1 = Math.max(profit1, price - minPrice1);

            minPrice2 = Math.min(minPrice2, price - profit1);
            profit2 = Math.max(profit2, price - minPrice2);
        }

        return profit2;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices1));  // Output: 6

        // Test case 2
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices2));  // Output: 4

        // Test case 3
        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices3));  // Output: 0
    }
}
