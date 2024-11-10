package leetcode.graph;

import java.util.*;

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
        //return helperDFS(node);
        return helperBFS(node);
    }



    public static void main(String[] args) {
        int[][] adjList = {{2, 4}, // Node 1 neighbors
                {1, 3}, // Node 2 neighbors
                {2, 4}, // Node 3 neighbors
                {1, 3}  // Node 4 neighbors
        };

        //adjList = new int[0][0];

        Node node = new Graph().buildGraph(adjList);
        Node clone = new CloneGraph().cloneGraph(node);
        System.out.println();
    }


}
