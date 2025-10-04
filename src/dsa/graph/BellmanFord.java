package dsa.graph;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Bellman-Ford is a single-source shortest path algorithm that:
//Works on graphs with negative weights
//Detects negative weight cycles
//Returns the shortest distances from a source node to all other nodes
public class BellmanFord {

    public List<Edge> graph;
    public int V;

    public BellmanFord(int v) {
        graph = new ArrayList<>(v);
        V = v;
    }

    public void addEdge(int s, int d, int w) {
        graph.add(new Edge(s, d, w));
    }

    //1. Directed Weighted Edge
    public static class Edge {
        public int src, dest, weight;

        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }
    }

    public int[] bellmanFord(int source) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (Edge edge : graph) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.dest] > dist[edge.src] + edge.weight) {
                    dist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
        }

        for (Edge edge : graph) {
            if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.dest] > dist[edge.src] + edge.weight) {
                throw new IllegalStateException("Negative Weight Cycle Exist");
            }
        }

        return dist;

    }

    public static void main(String[] args) {
        int V = 5;
        BellmanFord bellmanFord = new BellmanFord(V);

        // Sample Graph
        bellmanFord.addEdge(0, 1, 6);
        bellmanFord.addEdge(0, 4, 7);
        bellmanFord.addEdge(1, 2, 5);
        bellmanFord.addEdge(1, 3, -4);
        bellmanFord.addEdge(1, 4, 8);
        bellmanFord.addEdge(2, 3, -2);
        bellmanFord.addEdge(3, 2, 7);
        bellmanFord.addEdge(4, 2, -3);
        bellmanFord.addEdge(4, 3, 9);

        int[] dist = bellmanFord.bellmanFord(0);
        for (int i = 0; i < V; i++) {
            System.out.println("Distance from 0 to " + i + " is " + dist[i]);
        }
    }


}
