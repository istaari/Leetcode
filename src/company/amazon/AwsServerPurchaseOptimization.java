package company.amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AwsServerPurchaseOptimization {

    public static int findMinimumCost(int[] efficiency, int[] cost, long k) {
        int n = efficiency.length;
        List<Integer> cost1 = new ArrayList<>();
        List<Integer> cost2 = new ArrayList<>();
        long totalEfficiency = 0;

        for (int i = 0; i < n; i++) {
            if (cost[i] == 1) {
                cost1.add(efficiency[i]);
            } else {
                cost2.add(efficiency[i]);
            }
            totalEfficiency += efficiency[i];
        }

        if (totalEfficiency < k) {
            return -1;
        }

        // Sort both cost1 and cost2 servers in descending order of efficiency
        cost1.sort(Comparator.reverseOrder());
        cost2.sort(Comparator.reverseOrder());

        int n1 = cost1.size(), n2 = cost2.size();
        int i1 = 0, i2 = 0, totalCost = 0;

        while (k > 0) {
            if (i1 < n1 &&
                    (i2 == n2 || cost1.get(i1) >= cost2.get(i2) || cost1.get(i1) >= k ||
                            (i1 + 1 < n1 && cost1.get(i1) + cost1.get(i1 + 1) >= cost2.get(i2)))) {

                k -= cost1.get(i1);
                i1++;
                totalCost += 1;
            } else {
                k -= cost2.get(i2);
                i2++;
                totalCost += 2;
            }
        }

        return totalCost;
    }

    // For quick testing
    public static void main(String[] args) {
        int[] efficiency1 = {4, 4, 6, 7};
        int[] cost1 = {1, 1, 2, 2};
        long k1 = 7;
        System.out.println(findMinimumCost(efficiency1, cost1, k1)); // Output: 2

        int[] efficiency2 = {75, 104, 72, 72, 8, 125};
        int[] cost2 = {1, 2, 2, 1, 2, 1};
        long k2 = 376;
        System.out.println(findMinimumCost(efficiency2, cost2, k2)); // Output: 5
    }

}
