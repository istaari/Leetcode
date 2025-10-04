package dsa;

import java.util.*;

public class Tarjan {

    private int time = 0; // global timer for discovery times
    private int[] disc;   // discovery time of each node
    private int[] low;    // lowest discovery time reachable
    private boolean[] inStack; // whether a node is in the current DFS stack
    private Stack<Integer> stack; // current DFS path
    private List<List<Integer>> sccs; // list of SCCs
    private List<List<Integer>> graph; // adjacency list

    public List<List<Integer>> findSCCs(int n, List<List<Integer>> graph) {
        this.graph = graph;
        disc = new int[n];
        low = new int[n];
        inStack = new boolean[n];
        stack = new Stack<>();
        sccs = new ArrayList<>();

        Arrays.fill(disc, -1); // -1 means unvisited

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i);
            }
        }

        return sccs;
    }

    private void dfs(int u) {
        disc[u] = low[u] = time++;
        stack.push(u);
        inStack[u] = true;

        for (int v : graph.get(u)) {
            if (disc[v] == -1) {
                dfs(v);
                // Tells us when we first visited a node.
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                // There is back edge
                // What is the earliest ancestor reachable from this node or any of its descendants?
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (disc[u] == low[u]) {
            int v;
            List<Integer> component = new ArrayList<>();
            do {
                v = stack.pop();
                component.add(v);
                inStack[v] = false;
            } while (u != v);

            sccs.add(component);
        }

    }

    // Test Example
    public static void main(String[] args) {
        Tarjan tarjan = new Tarjan();

        int n = 5;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(1);
        graph.get(1).add(2);
        graph.get(2).add(3);
        graph.get(3).add(4);
        graph.get(4).add(1);

        System.out.println(tarjan.findSCCs(n, graph));
    }
}
