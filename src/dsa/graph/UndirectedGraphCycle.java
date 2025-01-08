package dsa.graph;

import java.util.ArrayList;
import java.util.List;

class UndirectedGraphCycle {

    final int vertices;
    final List<List<Integer>> adjacencyList;

    // Constructor
    UndirectedGraphCycle(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    // Add an edge from source to destination the graph
    void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);   // For undirected graph
    }


    // Utility method to detect a cycle in an undirected graph using DFS
    boolean hasCycleDFS(int currentNode, int parentNode, List<List<Integer>> adjacencyList, boolean[] visitedNodes) {
        visitedNodes[currentNode] = true;
        for (int adjacentNode : adjacencyList.get(currentNode)) {
            // If the adjacent node has not been visited, recursively check for cycles
            if (!visitedNodes[adjacentNode]) {
                if (hasCycleDFS(adjacentNode, currentNode, adjacencyList, visitedNodes)) {
                    return true;
                }
                // If the adjacent node is visited and is not the parent, a cycle is found
                // adjacentNode == parentNode when parent = 1, current = 2, then neighbor of 2 will be 1
            } else if (adjacentNode != parentNode) {
                return true;
            }
        }
        return false;
    }

    boolean containsCycle() {
        boolean[] visitedNodes = new boolean[this.vertices];
        for (int vertex = 0; vertex < this.vertices; vertex++) {
            // If the vertex is not visited, start DFS to detect a cycle
            if (!visitedNodes[vertex]) {
                if (hasCycleDFS(vertex, -1, this.adjacencyList, visitedNodes)) {
                    return true;
                }
            }
        }
        return false;
    }



    public static void main(String[] args) {
        UndirectedGraphCycle detectCycleUndirectedGraph = new UndirectedGraphCycle(3);
        detectCycleUndirectedGraph.addEdge(0, 1);
        detectCycleUndirectedGraph.addEdge(1, 2);
        detectCycleUndirectedGraph.addEdge(2, 0);

        System.out.println(detectCycleUndirectedGraph.containsCycle());
    }
}
