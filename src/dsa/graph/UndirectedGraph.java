package dsa.graph;

import dsa.graph.edge.UndirectedEdge;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph {

    public final int totalVertices;
    public final List<List<UndirectedEdge>> adjacencyList;

    public UndirectedGraph(int totalVertices) {
        this.totalVertices = totalVertices;
        this.adjacencyList = new ArrayList<>();

        for (int i = 0; i < totalVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new UndirectedEdge(dest, weight));
        adjacencyList.get(dest).add(new UndirectedEdge(src, weight));
    }


}
