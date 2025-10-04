package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;


//Purpose: Finds the shortest path from a single source to all other nodes.
//Use Case: You want the shortest distance from one specific node (like a starting point).
//Works with: Only non-negative weights.
//Time Complexity: O(V²) or O((V + E) log V) with a priority queue (like in Java with a min-heap).
public class Dijkstra {
    public List<List<Edge>> graph;
    public static int V;

    public Dijkstra(int v) {
        this.V = v;
        this.graph = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
    }


    public void addEdge(int src, int dest, int weight) {
        graph.get(src).add(new Edge(dest, weight));
    }

    public static class Edge {
        public int dest;
        public int weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    public int[] dijkstra(int source) {
        boolean[] visited = new boolean[V];
        // Distance matrix to hold the shortest distance from source to all the vertex
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];

            if (visited[u]) continue;
            visited[u] = true;

            for (Edge edge : graph.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;

                // relaxation formula
                if (dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        return dist;
    }


    public static void main(String[] args) {
        Dijkstra graph1 = new Dijkstra(6);
        graph1.addEdge(0, 1, 2);
        graph1.addEdge(0, 2, 4);
        graph1.addEdge(1, 2, 1);
        graph1.addEdge(1, 3, 7);
        graph1.addEdge(2, 4, 3);
        graph1.addEdge(3, 5, 1);
        graph1.addEdge(4, 3, 2);
        graph1.addEdge(4, 5, 5);

        int source1 = 0;
        int[] distances1 = graph1.dijkstra(source1);

        System.out.println("Shortest distances from vertex " + source1 + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + distances1[i]);
        }

        
    }
}




