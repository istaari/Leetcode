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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node(val: ").append(val).append(", neighbors: [");
        if (neighbors != null) {
            for (int i = 0; i < neighbors.size(); i++) {
                // We print neighbor.val to avoid infinite recursion in graphs with cycles
                sb.append(neighbors.get(i).val);
                if (i < neighbors.size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("])");
        return sb.toString();
    }

}


