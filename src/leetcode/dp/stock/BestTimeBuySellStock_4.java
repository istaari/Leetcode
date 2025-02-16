package leetcode.dp.stock;

import java.util.Arrays;

public class BestTimeBuySellStock_4 {

    public static int maxProfit(int k, int[] prices) {
        int[] buy = new int[k + 1], sell = new int[k + 1];

        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];

    }

    public static void main(String[] args) {
        // Test case 1
        int[] prices1 = {2, 4, 1};
        System.out.println(maxProfit(2, prices1));  // Output: 2

        // Test case 2
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        System.out.println(maxProfit(2, prices2));  // Output: 7
    }
}
