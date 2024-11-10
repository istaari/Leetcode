package leetcode.graph;


import java.util.ArrayList;
import java.util.List;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


@SuppressWarnings("all")
public class Graph {

    public Node buildGraph(int[][] adjList) {

        if (adjList.length == 0 || adjList == null) {
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

