package dsa.graph.edge;

// Concrete class for Edge without a source node (adjacency list style)
public class UndirectedEdge {
    public int dest;
    public int weight;

    public UndirectedEdge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}
