package leetcode.graph.representations;

import java.util.ArrayList;
import java.util.List;

public class EdgeList {

    // A simple inner class to represent an edge
    static class Edge {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("(%d -- %d, w:%d)", source, destination, weight);
        }
    }

    private final List<Edge> edgeList;
    private final int numVertices;

    public EdgeList(int numVertices) {
        this.numVertices = numVertices;
        this.edgeList = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        if (source < 0 || source >= numVertices || destination < 0 || destination >= numVertices) {
            throw new IllegalArgumentException("Vertex out of bounds");
        }
        // For both directed and undirected graphs, we just add one edge object
        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);
    }


     static void main(String[] args) {
        int numVertices = 4;
         EdgeList graph = new EdgeList(numVertices);

        // Add the edges from our example
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 9);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 3);
    }
}
