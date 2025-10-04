package dsa;

import java.util.*;

/**
 * Implementation of Kosaraju's algorithm for finding Strongly Connected Components (SCCs)
 * in a directed graph.
 * <p>
 * Time Complexity: O(V+E) where V is the number of vertices, and E is the amount of edge
 * Space Complexity: O(V+E)
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

    private void fillOrder(int v, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;

        for (int neighbor : graph.get(v)) {
            if (!visited[neighbor]) {
                fillOrder(neighbor, visited, stack);
            }
        }

        stack.push(v); // Records Finish Time of Nodes (Topological Order)
    }

    // 2. Creates the transpose of the graph (reverses all edges).
    private void createTransposedGraph() {
        for (int v = 0; v < vertices; v++) {
            for (int neighbor : graph.get(v)) {
                transposedGraph.get(neighbor).add(v);
            }
        }
    }

    //3.  Second DFS pass to find strongly connected components.
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

        // First DFS to fill the stack, loop because if the graph is disconnected
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                fillOrder(i, visited, stack);
            }
        }

        // Create the transposed graph
        createTransposedGraph();

        Arrays.fill(visited, false);

        // List to store all SCCs
        List<List<Integer>> sccs = new ArrayList<>();

        // Second DFS to find SCCs
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