package leetcode.dp.trees;

import java.util.*;

/**
 * 3531. Maximum Good Subtree Score
 * https://leetcode.com/problems/maximum-good-subtree-score/
 *
 * You are given an undirected tree rooted at node 0 with n nodes numbered
 * from 0 to n - 1; vals[i] is the value of the i-th node, and par[i] is
 * the parent of the i-th node (par[0] == -1 for the root).
 *
 * A subtree of node i is "good" if every digit (0-9) that appears in the
 * values of nodes in the subtree appears at most once. Return the maximum
 * sum of values in any good subtree. Since the answer may be large, return
 * it modulo 10^9 + 7.
 *
 * Constraints:
 *   1 <= n <= 10^4
 *   1 <= vals[i] <= 10^9
 *
 * ---
 * Approach: Tree DP with bitmask (digits 0-9 as 10-bit mask)
 *
 * Each digit 0-9 is mapped to a bit (10 bits total, 2^10 = 1024 states).
 * For a node's value, compute which digits appear and how many times.
 * If any digit repeats, that node alone is invalid (mask = -1).
 *
 * dp[mask] at each node = maximum sum achievable by selecting a subset of
 * nodes (from the subtree rooted at this node) whose combined digit-mask
 * is exactly `mask`. We merge children one by one using a knapsack-like
 * combination of non-overlapping masks.
 *
 * After processing all nodes, the answer is max(dp[mask]) at any node
 * for any mask, taken modulo 10^9 + 7.
 *
 * Time:  O(n * 3^10) — for each node, merging masks with each child is O(3^10)
 *        because we iterate over all pairs of submasks that don't overlap.
 * Space: O(n * 2^10) for storing dp at each node, simplified to O(2^10) per DFS frame.
 */
public class MaxGoodSubtreeScore {

    static final int MOD = 1_000_000_007;
    static final int FULL = 1 << 10; // 1024 states for 10 digits

    public int goodSubtreeSum(int[] vals, int[] par) {
        int n = vals.length;

        // Build adjacency list (children only, since it's a rooted tree)
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            children.get(par[i]).add(i);
        }

        // Precompute the digit bitmask for each node's value.
        // digitMask[i] = bitmask of digits in vals[i], or -1 if any digit repeats.
        int[] digitMask = new int[n];
        for (int i = 0; i < n; i++) {
            digitMask[i] = computeDigitMask(vals[i]);
        }

        // DFS iteratively (to avoid stack overflow on large trees)
        // dp[node][mask] = max sum of a "good" subset from node's subtree with digit-mask = mask
        // We use long to avoid overflow before taking mod at the end.
        // -1 means this mask state is unreachable.
        long[][] dp = new long[n][FULL];
        for (long[] row : dp) Arrays.fill(row, -1);

        // Topological order via iterative DFS (post-order)
        int[] order = new int[n];
        int idx = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            order[idx++] = node;
            for (int child : children.get(node)) {
                stack.push(child);
            }
        }

        // Process nodes in reverse order (leaves first, root last)
        for (int i = n - 1; i >= 0; i--) {
            int node = order[i];

            // Base case: the empty subset (select no nodes) has sum 0 and mask 0
            dp[node][0] = 0;

            // If this node's value has no repeated digits, we can select it alone
            if (digitMask[node] != -1) {
                dp[node][digitMask[node]] = vals[node];
            }

            // Merge each child's dp into this node's dp (knapsack-style)
            for (int child : children.get(node)) {
                // We need to merge dp[node] with dp[child].
                // To avoid using a child's result twice, iterate over current dp[node]
                // and combine with dp[child] where masks don't overlap.

                // Take a snapshot of current dp[node] to avoid mid-iteration mutation issues
                long[] snapshot = dp[node].clone();

                // For each reachable mask in the child
                for (int cmask = 0; cmask < FULL; cmask++) {
                    if (dp[child][cmask] < 0) continue; // unreachable in child

                    // Combine with each reachable mask in current node's dp
                    // that doesn't overlap with cmask.
                    // Iterate over submasks of the complement of cmask.
                    int complement = (FULL - 1) ^ cmask;
                    for (int pmask = complement; pmask >= 0; pmask = (pmask - 1) & complement) {
                        if (snapshot[pmask] >= 0) {
                            int combined = pmask | cmask;
                            dp[node][combined] = Math.max(dp[node][combined],
                                    snapshot[pmask] + dp[child][cmask]);
                        }
                        if (pmask == 0) break; // important: avoid infinite loop when pmask wraps
                    }
                }
            }
        }

        // The answer is the max across ALL nodes' subtrees, for any non-zero mask.
        // Each node is the root of its own subtree, so check dp[every node].
        long ans = 0;
        for (int node = 0; node < n; node++) {
            for (int mask = 1; mask < FULL; mask++) {
                if (dp[node][mask] > ans) {
                    ans = dp[node][mask];
                }
            }
        }

        return (int) (ans % MOD);
    }

    /**
     * Computes the digit bitmask for a number.
     * Returns a bitmask where bit i is set if digit i appears in the number.
     * Returns -1 if any digit appears more than once (invalid for a "good" subtree).
     */
    private int computeDigitMask(int num) {
        int mask = 0;
        while (num > 0) {
            int digit = num % 10;
            int bit = 1 << digit;
            if ((mask & bit) != 0) {
                return -1; // Repeated digit
            }
            mask |= bit;
            num /= 10;
        }
        return mask;
    }

    public static void main(String[] args) {
        MaxGoodSubtreeScore solver = new MaxGoodSubtreeScore();

        // Test Case 1: vals = [2,3], par = [-1,0]
        // Node 0 (val=2), Node 1 (val=3, child of 0)
        // Best good subtree: {0,1} with sum = 5, digits {2,3} no repeats
        int[] vals1 = {2, 3};
        int[] par1 = {-1, 0};
        System.out.println("Test 1: " + solver.goodSubtreeSum(vals1, par1)); // Expected: 5

        // Test Case 2: vals = [1,5,2], par = [-1,0,0]
        // Tree: 0 -> {1, 2}. vals = {1, 5, 2}
        // All digit masks: 1->{1}, 5->{5}, 2->{2}. No overlaps.
        // Best: pick all 3 nodes, sum = 8
        int[] vals2 = {1, 5, 2};
        int[] par2 = {-1, 0, 0};
        System.out.println("Test 2: " + solver.goodSubtreeSum(vals2, par2)); // Expected: 8

        // Test Case 3: vals = [11,22,33], par = [-1,0,0]
        // All nodes have repeated digits (11->digit 1 twice, etc.)
        // No single node is valid, so answer = 0
        int[] vals3 = {11, 22, 33};
        int[] par3 = {-1, 0, 0};
        System.out.println("Test 3: " + solver.goodSubtreeSum(vals3, par3)); // Expected: 0
    }
}
