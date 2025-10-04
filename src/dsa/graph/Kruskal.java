package dsa.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Kruskal requires global edge list
public class Kruskal {

    public List<Edge> graph;
    public int V;

    public Kruskal(int v) {
        graph = new ArrayList<>(v);
        V = v;
    }

    public void addEdge(int s, int d, int w) {
        graph.add(new Edge(s, d, w));
    }

    //1. Directed Edge
    public static class Edge implements Comparable<Edge> {
        public int src, dest, weight;

        public Edge(int s, int d, int w) {
            src = s;
            dest = d;
            weight = w;
        }

        //2. Sort edges by weight increasing order
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }

        @Override
        public String toString() {
            return "Edge{" + "src=" + src + ", dest=" + dest + ", weight=" + weight + '}';
        }
    }

    //3. Union find algorithm to check cycle in graph
    public static class UnionFind {

        private final int[] parent;
        private final int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        // Find operation with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Union (in broad terms, this method adds an edge between two nodes)
        public boolean union(int x, int y) {
            int xRoot = find(x);
            int yRoot = find(y);

            if (xRoot == yRoot) return false;

            // Attach the smaller rank tree under the root of the larger rank tree
            if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }

            return true;
        }

    }

    public List<Edge> findMST() {
        List<Edge> result = new ArrayList<>();
        UnionFind unionFind = new UnionFind(V);
        Collections.sort(graph);

        for (Edge edge : graph) {
            //4. If there is no cycle, then add in a result
            if (unionFind.union(edge.src, edge.dest)) {
                result.add(edge);
            }
            // when total edges is V-1
            if (result.size() == V - 1) break;
        }

        return result;
    }


    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal(4);
        kruskal.addEdge(0, 1, 10);
        kruskal.addEdge(0, 2, 6);
        kruskal.addEdge(0, 3, 5);
        kruskal.addEdge(1, 3, 15);
        kruskal.addEdge(2, 3, 4);

        System.out.println(kruskal.findMST().toString());// Total weight = 4 + 5 + 10 = 19
        
    }


}
