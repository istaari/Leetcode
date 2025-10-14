package leetcode.graph.representations;


import java.util.ArrayList;
import java.util.List;

public class UnweightedGraph {

    // The total number of vertices in the graph.
    public final int vertices;
    public final List<List<Integer>> adjacencyList;

    public UnweightedGraph(int vertices) {
        this.vertices = vertices;
        this.adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }


    public void addEdge(int source, int destination) {
        // Ensure the vertices are within the valid range.
        if (source < 0 || source >= vertices || destination < 0 || destination >= vertices) {
            throw new IllegalArgumentException("Vertex out of bounds");
        }

        // Add an edge from source to destination.
        adjacencyList.get(source).add(destination);

        // Since this is an undirected graph, add an edge from destination to source as well.
        // For a directed graph, you would remove the line below.
        adjacencyList.get(destination).add(source);
    }

}