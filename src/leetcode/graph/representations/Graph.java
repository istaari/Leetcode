package leetcode.graph.representations;

import java.util.ArrayList;

public class Graph {

    public Node buildGraph(int[][] adjList) {

        if (adjList.length == 0) {
            return null;
        }

        Node[] nodes = new Node[adjList.length + 1];
        for (int i = 1; i <= adjList.length; i++) {
            nodes[i] = new Node(i, new ArrayList<>());
        }

        for (int i = 1; i <= adjList.length; i++) {
            for (int neighbor : adjList[i - 1]) {
                nodes[i].neighbors.add(nodes[neighbor]);
            }
        }

        return nodes[1];
    }

}
