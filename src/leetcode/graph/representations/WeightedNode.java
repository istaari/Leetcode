package leetcode.graph.representations;


import java.util.ArrayList;
import java.util.List;

/**
 * Defines a Node for a weighted graph using an object-oriented approach.
 * Each node contains a value and a list of its outgoing edges, where each
 * edge stores a reference to the destination node and the edge's weight.
 */
public class WeightedNode {

    /**
     * A helper class to represent a weighted edge. It encapsulates the
     * destination node and the weight of the connection.
     */
    public static class Edge {
        public WeightedNode destination;
        public int weight;

        /**
         * Constructs a new Edge.
         *
         * @param destination The node this edge connects to.
         * @param weight      The weight of this edge.
         */
        public Edge(WeightedNode destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // The value or label of the node.
    public int val;
    // A list of outgoing edges from this node.
    public List<Edge> neighbors;


    public WeightedNode() {
        this.val = 0;
        this.neighbors = new ArrayList<>();
    }

    public WeightedNode(int val) {
        this.val = val;
        this.neighbors = new ArrayList<>();
    }


    public void addEdge(WeightedNode destination, int weight) {
        Edge newEdge = new Edge(destination, weight);
        this.neighbors.add(newEdge);
    }


    // --- Main Method to Demonstrate Usage ---
    static void main(String[] args) {
        // Define the number of vertices in the graph
        int numVertices = 5;

        // Create an instance of the graph
        WeightedGraph graph = new WeightedGraph(numVertices);

        // Add weighted edges to the graph
        // This creates an undirected edge between the vertices
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 5);
        graph.addEdge(1, 2, 7);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 6);
        graph.addEdge(3, 4, 9);

        System.out.println("Successfully created a weighted graph with " + numVertices + " vertices.\n");
    }

}
