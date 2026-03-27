package leetcode.graph.connectedComponents.UG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 721. Accounts Merge
 * https://leetcode.com/problems/accounts-merge/
 *
 * Given a list of accounts where each element accounts[i] is a list of strings,
 * where the first element accounts[i][0] is a name, and the rest of the elements
 * are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts belong to the same
 * person if there is some common email to both accounts. After merging, return
 * the accounts in the format: the first element is the name, the rest are emails
 * in sorted order.
 *
 * Example:
 *   Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],
 *                      ["John","johnsmith@mail.com","john00@mail.com"],
 *                      ["Mary","mary@mail.com"],
 *                      ["John","johnnybravo@mail.com"]]
 *   Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 *            ["Mary","mary@mail.com"],
 *            ["John","johnnybravo@mail.com"]]
 *
 * Constraints:
 *   1 <= accounts.length <= 1000
 *   2 <= accounts[i].length <= 10
 *   1 <= accounts[i][j].length <= 30
 *
 * ---
 * Approach: DFS on undirected graph of emails
 *
 * 1. Build a graph: for each account, connect the first email to all other emails
 *    (they belong to the same person).
 * 2. DFS/BFS to find connected components — each component is one merged account.
 * 3. Sort the emails in each component and prepend the name.
 *
 * Time:  O(N * K * log(N * K)) where N = accounts, K = max emails per account
 * Space: O(N * K)
 */
public class AccountsMerge {

    /**
     * emailToName {
     * "johnsmith@mail.com" -> ["john_newyork@mail.com", "john00@mail.com"],
     * "john_newyork@mail.com" -> ["johnsmith@mail.com"],
     * "john00@mail.com" -> ["johnsmith@mail.com"],
     * "mary@mail.com" -> ["mary@mail.com"], // only one email, no edges
     * "johnnybravo@mail.com" -> ["johnnybravo@mail.com"] // same
     * }
     * 
     * graph {
     * "johnsmith@mail.com": ["johnsmith@mail.com", "john_newyork@mail.com",
     * "johnsmith@mail.com", "john00@mail.com"],
     * "john_newyork@mail.com": ["johnsmith@mail.com"],
     * "john00@mail.com": ["johnsmith@mail.com"],
     * "mary@mail.com": ["mary@mail.com"],
     * "johnnybravo@mail.com": ["johnnybravo@mail.com"]
     * }
     * 
     **/

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();

        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                // Map Email to Name
                emailToName.put(account.get(i), account.get(0));
                // Build Undirected Graph
                graph.computeIfAbsent(account.get(1), k -> new ArrayList<>()).add(account.get(i));
                graph.computeIfAbsent(account.get(i), k -> new ArrayList<>()).add(account.get(1));
            }
        }

        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String node : graph.keySet()) {
            List<String> component = new ArrayList<>();
            if (visited.contains(node))
                continue;

            dfsUtil(node, graph, visited, component);
            Collections.sort(component);
            component.add(0, emailToName.get(node));
            result.add(component);
        }

        return result;
    }

    public void dfsUtil(String node, Map<String, List<String>> graph, Set<String> visited, List<String> component) {
        visited.add(node);
        component.add(node);

        for (String neighbour : graph.get(node)) {
            if (!visited.contains(neighbour)) {
                dfsUtil(neighbour, graph, visited, component);
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"));

        System.out.println(new AccountsMerge().accountsMerge(accounts));
    }

}
