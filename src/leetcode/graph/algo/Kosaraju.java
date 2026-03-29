package leetcode.graph.algo;

import java.util.*;

/**
 * Kosaraju's Algorithm — Finding Strongly Connected Components (SCCs)
 *
 * PROBLEM IT SOLVES:
 *   Given a directed graph, find all Strongly Connected Components.
 *   An SCC is a maximal set of vertices where every vertex is reachable
 *   from every other vertex in the set.
 *
 * WHEN TO USE:
 *   - Detect SCCs in a directed graph.
 *   - Simplify a directed graph into a DAG of its components.
 *   - Problems: 2-SAT, checking if a digraph is strongly connected, etc.
 *
 * ALGORITHM STEPS (3 passes):
 *   Step 1 — DFS on the ORIGINAL graph to get finish order.
 *       - Run DFS from every unvisited vertex.
 *       - After a vertex finishes (all descendants explored), push it onto a stack.
 *       - The stack now holds vertices in reverse topological order (by finish time).
 *       - WHY? Vertices that finish last have the "highest reach" and should be
 *         processed first when we reverse edges.
 *
 *   Step 2 — Build the TRANSPOSED graph (reverse all edge directions).
 *       - For every edge u -> v in the original, add v -> u in the transposed graph.
 *
 *   Step 3 — DFS on the TRANSPOSED graph in stack order.
 *       - Pop vertices from the stack (highest finish time first).
 *       - Each DFS from an unvisited vertex in the transposed graph discovers
 *         one complete SCC.
 *       - WHY? In the transposed graph, if u can reach v AND v can reach u
 *         (original graph), they are in the same SCC. The finish-time ordering
 *         ensures we don't "leak" into other components.
 *
 * INTUITION:
 *   If A can reach B in the original graph, and B can reach A in the
 *   transposed graph (which means A can reach B in the original too),
 *   then A and B are in the same SCC.
 *
 * TIME:  O(V + E) — two DFS passes + transpose construction.
 * SPACE: O(V + E) — for both graphs + stack + visited array.
 *
 * COMPARISON WITH TARJAN:
 *   - Kosaraju: 2 DFS passes + transpose. Conceptually simpler.
 *   - Tarjan:   1 DFS pass, uses disc/low arrays. More efficient in practice.
 */
public class Kosaraju {

    private final int vertices;
    private final List<List<Integer>> graph;
    private final List<List<Integer>> transposedGraph;


    // Constructs a graph with the specified number of vertices.
    public Kosaraju(int vertices) {
        this.vertices = vertices;
        this.graph = new ArrayList<>(vertices);
        this.transposedGraph = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
            transposedGraph.add(new ArrayList<>());
        }
    }


    public void addEdge(int src, int dest) {
        graph.get(src).add(dest);
    }

    // Step 1: DFS on original graph — records finish order.
    //   When all neighbours of v are fully explored, push v onto the stack.
    //   This means v finishes AFTER all vertices reachable from it.
    //   Result: stack top = vertex with the latest finish time.
    private void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        for (int neighbor : graph.get(v)) {
            if (!visited[neighbor]) {
                fillOrder(neighbor, visited, stack);
            }
        }

        stack.push(v); // Records Finish Time of Nodes (Topological Order)
    }

    // Step 2: Build the transposed graph (reverse every edge direction).
    //   Original: u -> v  becomes  Transposed: v -> u
    //   This lets us check "can v reach u?" by doing DFS from u in the transposed graph.
    private void createTransposedGraph() {
        for (int v = 0; v < vertices; v++) {
            for (int neighbor : graph.get(v)) {
                transposedGraph.get(neighbor).add(v);
            }
        }
    }

    // Step 3: DFS on the transposed graph to collect one SCC.
    //   All vertices reachable from v in the transposed graph (that haven't been
    //   assigned to an SCC yet) form a single strongly connected component.
    private void dfsUtil(int v, boolean[] visited, List<Integer> component) {
        visited[v] = true;
        component.add(v);

        for (int neighbor : transposedGraph.get(v)) {
            if (!visited[neighbor]) {
                dfsUtil(neighbor, visited, component);
            }
        }
    }


    public List<List<Integer>> findSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];

        // --- Step 1: First DFS on original graph to fill the stack with finish order ---
        //   Loop ensures we cover disconnected components too.
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        // --- Step 2: Create the transposed graph (reverse all edges) ---
        createTransposedGraph();

        Arrays.fill(visited, false);

        List<List<Integer>> sccs = new ArrayList<>();

        // --- Step 3: Second DFS on transposed graph in stack order ---
        //   Pop vertices by decreasing finish time.
        //   Each DFS from an unvisited vertex discovers one complete SCC.
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                List<Integer> component = new ArrayList<>();
                dfsUtil(v, visited, component);
                sccs.add(component);
            }
        }

        return sccs;
    }


    public static void main(String[] args) {
        Kosaraju g = new Kosaraju(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println(g.findSCCs());
    }

}