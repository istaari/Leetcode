package leetcode.graph.algo;

import java.util.*;

/**
 * Tarjan's Algorithm — Finding Strongly Connected Components (SCCs)
 *
 * PROBLEM IT SOLVES:
 *   Given a directed graph, find all Strongly Connected Components in a
 *   SINGLE DFS pass. An SCC is a maximal set of vertices where every
 *   vertex is reachable from every other vertex.
 *
 * WHEN TO USE:
 *   - Finding SCCs (same as Kosaraju, but single-pass and often preferred).
 *   - Finding bridges and articulation points (variant of Tarjan).
 *   - Problems: Critical Connections in a Network (LC 1192), 2-SAT, etc.
 *
 * KEY DATA STRUCTURES:
 *   disc[u]: Discovery time — when we first visit vertex u during DFS.
 *   low[u]:  The lowest discovery time reachable from u's subtree
 *            (including back edges to ancestors still on the stack).
 *   stack:   Holds vertices in the current DFS path / current SCC candidates.
 *   inStack[u]: Whether u is currently on the stack.
 *
 * ALGORITHM STEPS:
 *   Step 1: Initialize disc[] to -1 (unvisited), time = 0.
 *   Step 2: For each unvisited vertex, run DFS:
 *
 *   DFS(u):
 *     a) Set disc[u] = low[u] = time++. Push u onto stack. Mark inStack[u] = true.
 *     b) For each neighbour v of u:
 *        - If v is UNVISITED: recurse DFS(v), then low[u] = min(low[u], low[v]).
 *          (Propagate the lowest reachable ancestor back up.)
 *        - If v is ON THE STACK (back edge): low[u] = min(low[u], disc[v]).
 *          (v is an ancestor in the current DFS path — we can reach it.)
 *        - If v is VISITED but NOT on stack: ignore.
 *          (v belongs to an already-identified SCC.)
 *     c) After processing all neighbours, check: is disc[u] == low[u]?
 *        - YES: u is the ROOT of an SCC. Pop vertices from the stack until
 *          we pop u itself. All popped vertices form one SCC.
 *        - NO: u is not the root; its SCC root is higher in the DFS tree.
 *
 * INTUITION:
 *   - disc[u] == low[u] means u cannot reach any ancestor earlier than itself.
 *     It is the "highest" node in its SCC (the root).
 *   - The stack keeps all vertices that might still be part of the current SCC.
 *   - Back edges (to ancestors on the stack) tell us about cycles.
 *
 * TIME:  O(V + E) — single DFS pass.
 * SPACE: O(V)     — for disc[], low[], stack, inStack[].
 *
 * COMPARISON WITH KOSARAJU:
 *   - Tarjan:   1 DFS pass, slightly more complex logic. No transpose needed.
 *   - Kosaraju: 2 DFS passes + graph transpose. Conceptually simpler.
 */
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
        // Step 2a: Assign discovery time and low value. Push onto stack.
        disc[u] = low[u] = time++;
        stack.push(u);
        inStack[u] = true;

        for (int v : graph.get(u)) {
            if (disc[v] == -1) {
                // Case 1: v is UNVISITED — tree edge, recurse.
                dfs(v);
                // After DFS returns, propagate the lowest reachable time.
                // If v (or its descendants) can reach an earlier ancestor, so can u.
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                // Case 2: v is ON THE STACK — back edge (cycle detected).
                //   v is an ancestor of u in the current DFS path.
                //   Update low[u] to reflect that u can reach as far back as v.
                low[u] = Math.min(low[u], disc[v]);
            }
            // Case 3: v is visited but NOT on stack — belongs to an already
            //   completed SCC. Ignore it (cross edge to a finished component).
        }

        // Step 2c: If disc[u] == low[u], u is the ROOT of an SCC.
        //   This means u cannot reach any ancestor earlier than itself.
        //   Pop everything from the stack down to u — that's the SCC.
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
