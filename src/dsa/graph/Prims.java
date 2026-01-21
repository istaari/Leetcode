package dsa.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// Prims requires adjacency list, its a greedy algorithm
@SuppressWarnings("all")
public class Prims {
    public List<List<Edge>> graph;
    public int V;

    public Prims(int v) {
        this.V = v;
        this.graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static class Edge {
        public int dest;
        public int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public void addEdge(int src, int dest, int weight) {
        graph.get(src).add(new Edge(dest, weight));
    }

    public List<Edge> findMST() {
        boolean[] visited = new boolean[V];
        // Create a priority queue, ascending order
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        // Start with Vertex 0 (conceptual edge with weight 0).
        pq.add(new Edge(0, 0));
        List<Edge> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            // Skip if the destination vertex is already in the MST (prevents cycles).
            if (visited[edge.dest]) continue;

            // Add the new vertex to the MST set.
            visited[edge.dest] = true;
            
            // Record the edge (except for the initial start edge).
            result.add(new Edge(edge.dest, edge.weight));
            
            // Expore the neighbors
            for (Edge neighbor : graph.get(edge.dest)) {
                // If the neighbor is not yet in the MST, add it as a new candidate edge.
                if (!visited[neighbor.dest]) {
                    pq.add(new Edge(neighbor.dest, neighbor.weight));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Prims prim = new Prims(3);
        // A=0, B=1, C=2
        prim.addEdge(0, 1, 2);
        prim.addEdge(1, 0, 2);

        prim.addEdge(1, 2, 1);
        prim.addEdge(2, 1, 1);

        prim.addEdge(0, 2, 3);
        prim.addEdge(2, 0, 3);

        List<Edge> mst = prim.findMST();
        int totalWeight = 0;
        for (Edge e : mst) {
            System.out.println("Connected to: " + e.dest + ", Weight: " + e.weight);
            totalWeight += e.weight;
        }

        System.out.println("Total Weight of MST: " + totalWeight);
    }
}
