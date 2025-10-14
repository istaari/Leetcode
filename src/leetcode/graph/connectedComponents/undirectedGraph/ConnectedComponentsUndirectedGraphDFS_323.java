package leetcode.graph.connectedComponents.undirectedGraph;

import leetcode.graph.AdjacencyList;

public class ConnectedComponentsUndirectedGraphDFS_323 {

    AdjacencyList adjacencyList;

    public void setAdjacencyList(AdjacencyList adjacencyList) {
        this.adjacencyList = adjacencyList;
    }


    // Perform DFS to mark all nodes in the current component
    private void DFS(int node, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : adjacencyList.neighbor.get(node)) {
            if (!visited[neighbor]) {
                DFS(neighbor, visited);
            }
        }
    }

    // Count the number of connected components using DFS
    public int countComponents() {
        boolean[] visited = new boolean[adjacencyList.vertices];
        int count = 0;

        for (int i = 0; i < adjacencyList.vertices; i++) {
            if (!visited[i]) {
                DFS(i, visited);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        ConnectedComponentsUndirectedGraphDFS_323 graph = new ConnectedComponentsUndirectedGraphDFS_323();

        AdjacencyList adjacencyList1 = new AdjacencyList(5);
        adjacencyList1.addEdge(0, 1);
        adjacencyList1.addEdge(1, 2);
        adjacencyList1.addEdge(3, 4);
        graph.setAdjacencyList(adjacencyList1);
        System.out.println(graph.countComponents()); // Output: 2

        AdjacencyList adjacencyList2 = new AdjacencyList(5);
        adjacencyList2.addEdge(0, 1);
        adjacencyList2.addEdge(1, 2);
        adjacencyList2.addEdge(2, 3);
        adjacencyList2.addEdge(3, 4);
        graph.setAdjacencyList(adjacencyList2);
        System.out.println(graph.countComponents()); // Output: 1
    }

}
