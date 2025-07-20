package leetcode.graph.connectedComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountsMerge {

   /** 
    emailToName {
                    "johnsmith@mail.com"      -> ["john_newyork@mail.com", "john00@mail.com"],
                    "john_newyork@mail.com"   -> ["johnsmith@mail.com"],
                    "john00@mail.com"         -> ["johnsmith@mail.com"],
                    "mary@mail.com"           -> ["mary@mail.com"],   // only one email, no edges
                    "johnnybravo@mail.com"    -> ["johnnybravo@mail.com"] // same
                }

    graph   {
                "johnsmith@mail.com": ["johnsmith@mail.com", "john_newyork@mail.com", "johnsmith@mail.com", "john00@mail.com"],
                "john_newyork@mail.com": ["johnsmith@mail.com"],
                "john00@mail.com": ["johnsmith@mail.com"],
                "mary@mail.com": ["mary@mail.com"],
                "johnnybravo@mail.com": ["johnnybravo@mail.com"]
            }           

    **/
   
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();

        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                // Map Email to name
                emailToName.put(account.get(i), account.get(0));

                // Build undirected graph
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
