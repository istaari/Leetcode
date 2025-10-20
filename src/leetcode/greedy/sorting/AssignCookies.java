package leetcode.greedy.sorting;

import java.util.Arrays;

/**
 * LeetCode Problem: 455. Assign Cookies
 * <p>
 * Question:
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.
 * Each child `i` has a greed factor `g[i]`, which is the minimum size of a cookie that the child will be content with;
 * and each cookie `j` has a size `s[j]`. If `s[j] >= g[i]`, we can assign the cookie `j` to the child `i`, and the child `i` will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 * <p>
 * Example 1:
 * Input: g = [1, 2, 3], s = [1, 1]
 * Output: 1
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * You can only satisfy the child with greed factor 1. So you should output 1.
 * <p>
 * Constraints:
 * - 1 <= g.length <= 3 * 10^4
 * - 0 <= s.length <= 3 * 10^4
 * - 1 <= g[i], s[j] <= 2^31 - 1
 */
public class AssignCookies {

    /**
     * Finds the maximum number of content children using a greedy approach.
     *
     * @param greed  An array representing the greed factor of each child.
     * @param cookie An array representing the size of each cookie.
     * @return The maximum number of content children.
     */
    public static int findContentChildren(int[] greed, int[] cookie) {
        // --- The Greedy Strategy ---
        // To maximize the number of content children, we should be efficient with our cookies.
        // The best approach is to give the smallest cookie that can satisfy a child to the least greedy child.
        // This strategy saves larger cookies for greedier children who need them.
        // To implement this, we sort both arrays first.

        // Sort the greed factors and cookie sizes in ascending order.
        Arrays.sort(greed);
        Arrays.sort(cookie);

        int j = 0; // Pointer for the cookie array.
        int result = 0; // The count of content children.

        // Iterate through each child's greed factor.
        for (int k : greed) {

            // Find the smallest available cookie that can satisfy the current child.
            // We skip over all cookies that are too small.
            while (j < cookie.length && cookie[j] < k) {
                j++;
            }

            // If we found a suitable cookie (i.e., we haven't run out of cookies)
            if (j < cookie.length) {
                result++; // This child is now content.
                j++;      // Move to the next cookie, as this one has been used.
            }
        }
        return result;
    }


    static void main(String[] args) {
        // Test case 1:
        int[] greed1 = {10, 9, 8, 7};
        int[] cookie1 = {5, 6, 7, 8};
        // After sorting: greed = [7, 8, 9, 10], cookie = [5, 6, 7, 8]
        // Child with greed 7 gets cookie 7.
        // Child with greed 8 gets cookie 8.
        // No cookies left for children with greed 9 and 10.
        System.out.println(findContentChildren(greed1, cookie1)); // Expected: 2

        // Test case 2:
        int[] greed2 = {1, 2};
        int[] cookie2 = {1, 2, 3};
        // After sorting: greed = [1, 2], cookie = [1, 2, 3]
        // Child with greed 1 gets cookie 1.
        // Child with greed 2 gets cookie 2.
        System.out.println(findContentChildren(greed2, cookie2)); // Expected: 2
    }

}