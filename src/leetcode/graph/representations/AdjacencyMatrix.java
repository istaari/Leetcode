package leetcode.graph.representations;


public class AdjacencyMatrix {

    private final int[][] matrix;
    private final int numVertices;
    private static final int INFINITY = Integer.MAX_VALUE;


    public AdjacencyMatrix(int numVertices) {
        this.numVertices = numVertices;
        this.matrix = new int[numVertices][numVertices];

        // Initialize all connections to infinity and diagonals to 0.
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = INFINITY;
                }
            }
        }
    }

    /**
     * Adds an edge to the graph. For an undirected graph, it adds
     * the edge in both directions.
     */
    public void addEdge(int source, int destination, int weight) {
        if (source < 0 || source >= numVertices || destination < 0 || destination >= numVertices) {
            throw new IllegalArgumentException("Vertex out of bounds");
        }
        matrix[source][destination] = weight;
        matrix[destination][source] = weight; // For undirected graph
    }


    static void main(String[] args) {
        int numVertices = 4;
        AdjacencyMatrix graph = new AdjacencyMatrix(numVertices);

        // Add the edges from our example
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 9);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 3);
    }
}
