package leetcode.graph.representations;


import java.util.ArrayList;
import java.util.List;


public class WeightedGraph {

    public static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" + destination + ", w:" + weight + ")";
        }
    }

    // The total number of vertices in the graph.
    public final int vertices;
    // The adjacency list stores a list of Edges for each vertex.
    public final List<List<Edge>> adjacencyList;


    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination, int weight) {
        if (source < 0 || source >= vertices || destination < 0 || destination >= vertices) {
            throw new IllegalArgumentException("Vertex out of bounds");
        }

        // Create a new edge from source to destination.
        Edge edge = new Edge(destination, weight);
        adjacencyList.get(source).add(edge);

        // For an undirected graph, add the reverse edge.
        // For a directed graph, you would remove the lines below.
        Edge reverseEdge = new Edge(source, weight);
        adjacencyList.get(destination).add(reverseEdge);
    }


}