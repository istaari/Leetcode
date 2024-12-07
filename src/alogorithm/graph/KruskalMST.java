package alogorithm.graph;

import alogorithm.graph.edge.DirectedEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/*
 * Kruskal's Algorithm is used to find the Minimum Spanning Tree (MST)
 * in a weighted, undirected graph.
 *
 * Kruskal's algorithm builds the MST by selecting edges with the smallest
 * weight first, as long as they don't create a cycle.
 *
 * Analogy:
 * Imagine you building a road network connecting several cities.
 * You want to use the least amount of road material (minimize total road length)
 * while ensuring all cities are connected. Kruskal's algorithm would start
 * by building the shortest roads first, gradually connecting all cities.
 */
public class KruskalMST {

    public static List<DirectedEdge> kruskalMST(List<DirectedEdge> edges, int V) {
        edges.sort((edge1, edge2) -> edge1.weight - edge2.weight);

        List<DirectedEdge> minSpanningTree = new ArrayList<>();
        DisjointSet disjointSet = new DisjointSet(V);

        for (DirectedEdge edge : edges) {
            int srcRoot = disjointSet.find(edge.src);
            int destRoot = disjointSet.find(edge.dest);

            if (srcRoot != destRoot) {
                minSpanningTree.add(edge);
                disjointSet.union(srcRoot, destRoot);
            }
        }

        return minSpanningTree;
    }

    public static void main(String[] args) {
        int V = 4;
        List<DirectedEdge> edges = Arrays.asList(
                new DirectedEdge(0, 1, 10),
                new DirectedEdge(0, 2, 6),
                new DirectedEdge(0, 3, 5),
                new DirectedEdge(1, 3, 15),
                new DirectedEdge(2, 3, 4)
        );

        List<DirectedEdge> mst = kruskalMST(edges, V);

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (DirectedEdge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }


}
