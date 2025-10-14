package leetcode.graph.representations;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a Node for an unweighted graph.
 * Each node contains a value and a list of references to its neighbors.
 * This is a common structure for graph problems where the graph is
 * built dynamically or provided as a network of objects.
 */
public class Node {
    // The value or label of the node.
    public int val;
    // A list of direct references to neighboring nodes.
    public List<Node> neighbors;

    /**
     * Default constructor. Initializes an empty node with value 0.
     */
    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    /**
     * Constructor to create a node with a specific value.
     *
     * @param _val The integer value for the node.
     */
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    /**
     * Constructor to create a node with a specific value and a predefined list of neighbors.
     *
     * @param _val       The integer value for the node.
     * @param _neighbors A list of nodes that are neighbors to this node.
     */
    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    /**
     * Adds a directed edge from this node to a neighbor node.
     * To create an undirected graph, you must call this method on both nodes.
     * For example: nodeA.addEdge(nodeB) and nodeB.addEdge(nodeA).
     *
     * @param neighbor The node to connect to.
     */
    public void addEdge(Node neighbor) {
        this.neighbors.add(neighbor);
    }


    public static void main(String[] args) {
        // 1. Create the nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        // 2. Create an undirected edge between node 1 and 2
        // An edge must be added in both directions for it to be undirected.
        node1.addEdge(node2);
        node2.addEdge(node1);

        // 3. Create a directed edge from node 1 to 3 (1 -> 3)
        node1.addEdge(node3);

        // Print neighbors of node 1 to verify
        System.out.print("Node 1 is connected to: ");
        for (Node neighbor : node1.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        // Expected Output: Node 1 is connected to: 2 3
    }

}


