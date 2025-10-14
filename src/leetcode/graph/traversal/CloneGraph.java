package leetcode.graph.traversal;

import leetcode.graph.representations.Node;

import java.util.*;


@SuppressWarnings("ALL")
public class CloneGraph {
    Map<Integer, Node> map = new HashMap<>();


    public Node helperDFS(Node node) {
        map.put(node.val, new Node(node.val, new ArrayList<>()));

        for (Node neighbour : node.neighbors) {
            if (!map.containsKey(neighbour.val)) {
                helperDFS(neighbour);
            }

            neighbour = map.get(neighbour.val);
            map.get(node.val).neighbors.add(neighbour);
        }
        return map.get(node.val);
    }


    public Node helperBFS(Node node) {
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);
        map.put(node.val, new Node(node.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node neighbour : current.neighbors) {

                if (!map.containsKey(neighbour.val)) {
                    map.put(neighbour.val, new Node(neighbour.val, new ArrayList<>()));
                    queue.add(neighbour); // add in the queue
                }

                map.get(current.val).neighbors.add(map.get(neighbour.val));
            }
        }

        return map.get(node.val);
    }


    public Node cloneGraph(Node node) {
        if (node == null) return null;
        map.clear();
        return helperBFS(node);
    }


    private static Node buildGraph(int[][] adjList) {
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


    public static void main(String[] args) {
        int[][] adjList = {
                {2, 4}, // Node 1 neighbors
                {1, 3}, // Node 2 neighbors
                {2, 4}, // Node 3 neighbors
                {1, 3}  // Node 4 neighbors
        };

        Node node = buildGraph(adjList);
        Node clone = new CloneGraph().cloneGraph(node);
        System.out.println(clone);
    }

}
