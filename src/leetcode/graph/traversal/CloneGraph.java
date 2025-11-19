package leetcode.graph.traversal;

import leetcode.graph.representations.Node;

import java.util.*;

/**
 * LeetCode Problem: 133. Clone Graph
 *
 * Question:
 * Given a reference of a node in a connected undirected graph.
 * Return a deep copy (clone) of the graph.
 * Each node in the graph contains an integer `val` and a list `neighbors` of its neighbors.
 *
 * Example:
 * Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
 * Output: [[2,4],[1,3],[2,4],[1,3]]
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 *
 * Constraints:
 * - The number of nodes in the graph is in the range [0, 100].
 * - 1 <= Node.val <= 100
 * - Node.val is unique for each node.
 * - There are no self-loops in the graph.
 * - There are no repeated edges in the graph.
 * - The graph is connected and all nodes can be visited starting from the given node.
 */
@SuppressWarnings("ALL")
public class CloneGraph {

    Map<Integer, Node> map = new HashMap<>();

    public Node helperDFS(Node node) {
        // 1. Create the clone for the current node.
        // We create it and add it to the map *before* traversing its neighbors.
        // This acts as our "visited" mark.
        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node.val, clone);

        // 2. Iterate through all neighbors of the *original* node.
        for (Node neighbour : node.neighbors) {
            // 3. Check if this neighbor has already been cloned.
            if (!map.containsKey(neighbour.val)) {
                // If not, recursively call DFS to clone it.
                helperDFS(neighbour);
            }

            // 4. Add the *cloned neighbor* (fetched from the map) to the *cloned node's* neighbor list.
            // This is the step that builds the connections in the new graph.
            clone.neighbors.add(map.get(neighbour.val));
        }

        // Return the clone of the starting node.
        return clone;
    }


    public Node helperBFS(Node node) {
        // Map to store visited/cloned nodes. This is local to the BFS.
        Map<Integer, Node> map = new HashMap<>();
        // Queue for BFS traversal, storing *original* nodes to visit.
        Queue<Node> queue = new LinkedList<>();

        // 1. Add the starting node to the queue and create its clone in the map.
        queue.add(node);
        map.put(node.val, new Node(node.val, new ArrayList<>()));

        // 2. Process nodes until the queue is empty.
        while (!queue.isEmpty()) {
            Node current = queue.poll(); // Get the next *original* node to process.

            // 3. Iterate through all neighbors of the *original* node.
            for (Node neighbour : current.neighbors) {

                // 4. Check if this neighbor has been cloned yet.
                if (!map.containsKey(neighbour.val)) {
                    // If not, create its clone and add it to the map.
                    map.put(neighbour.val, new Node(neighbour.val, new ArrayList<>()));
                    // Add the *original* neighbor to the queue to be processed later.
                    queue.add(neighbour);
                }

                // 5. Connect the clones.
                // Add the *cloned neighbor* to the *cloned current node's* neighbor list.
                map.get(current.val).neighbors.add(map.get(neighbour.val));
            }
        }

        // 6. Return the clone of the starting node from the map.
        return map.get(node.val);
    }


    public Node cloneGraph(Node node) {
        if (node == null) return null;
        return helperBFS(node);
    }


    public static void main(String[] args) {
        // 1. Create all the nodes in the graph
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        // Node 1 neighbors: {2, 4}
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        // Node 2 neighbors: {1, 3}
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        // Node 3 neighbors: {2, 4}
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        // Node 4 neighbors: {1, 3}
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        // Create an instance of CloneGraph and call the clone method
        Node clone = new CloneGraph().cloneGraph(node1);
        // Print the result (this will just show the clone's value and neighbors' values)
        System.out.println("Original node: " + node1);
        System.out.println("Cloned node:   " + clone);
    }

}