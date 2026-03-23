package dsa.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Euler Tour of a Tree — flatten a tree into an array so that subtree queries
 * become range queries, solvable with a Segment Tree or BIT.
 *
 * KEY IDEA:
 *   Perform a DFS on the tree. Record the "entry time" (tin) when you first
 *   visit a node, and the "exit time" (tout) when you leave it after visiting
 *   all its descendants. Every node in u's subtree will have entry times in
 *   the contiguous range [tin[u], tout[u]].
 *
 *   This transforms subtree operations into range operations on a flat array.
 *
 * EXAMPLE:
 *
 *   Tree (rooted at 0):
 *
 *           0
 *          / \
 *         1   2
 *        / \   \
 *       3   4   5
 *
 *   DFS traversal order:  0 → 1 → 3 → (back) → 4 → (back) → (back) → 2 → 5
 *
 *   Node:  0  1  2  3  4  5
 *   tin:   0  1  4  2  3  5
 *   tout:  5  3  5  2  3  5
 *
 *   Euler order array (nodes ordered by tin):
 *     index: 0  1  2  3  4  5
 *     node:  0  1  3  4  2  5
 *
 *   Subtree of node 1 → range [tin[1], tout[1]] = [1, 3] → nodes {1, 3, 4} ✓
 *   Subtree of node 2 → range [tin[2], tout[2]] = [4, 5] → nodes {2, 5}    ✓
 *   Subtree of node 0 → range [tin[0], tout[0]] = [0, 5] → all nodes       ✓
 *
 * USE CASES:
 *   1. Subtree sum/min/max queries → Euler Tour + Segment Tree
 *   2. Subtree update (add value to all nodes in subtree) → range update
 *   3. LCA queries (with extensions)
 *   4. Path queries on trees (combined with techniques like HLD)
 *
 * TIME:  O(n) to compute the tour
 * SPACE: O(n) for tin[], tout[], and order[]
 */
public class EulerTour {

    private final List<List<Integer>> adj; // adjacency list of the tree
    private final int n;                   // number of nodes

    private int[] tin;      // tin[u]  = entry time of node u (when DFS first visits u)
    private int[] tout;     // tout[u] = exit time of node u (when DFS finishes u's subtree)
    private int[] order;    // order[t] = which node has entry time t (the flat Euler array)
    private int timer;      // global clock, incremented at each new node visit

    public EulerTour(int n) {
        this.n = n;
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Add an undirected edge (tree edge)
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    // ─────────────────────────────────────────────────────────────
    // Compute the Euler Tour starting from the given root.
    //
    // After this call:
    //   tin[u]   = the position in the flat array where node u appears
    //   tout[u]  = the last position belonging to u's subtree
    //   order[i] = the node at position i in the flat array
    //
    // Subtree of u → contiguous range [tin[u], tout[u]] in order[].
    // ─────────────────────────────────────────────────────────────
    public void computeTour(int root) {
        tin = new int[n];
        tout = new int[n];
        order = new int[n];
        timer = 0;

        dfs(root, -1);
    }

    private void dfs(int u, int parent) {
        // Record entry time: u is the (timer)-th node we visit
        tin[u] = timer;
        order[timer] = u;
        timer++;

        // Visit all children (skip parent to avoid going back up)
        for (int v : adj.get(u)) {
            if (v != parent) {
                dfs(v, u);
            }
        }

        // Record exit time: all descendants of u have been visited.
        // tout[u] = timer - 1 means the last index belonging to u's subtree.
        tout[u] = timer - 1;
    }

    // ─────────────────────────────────────────────────────────────
    // Getters — used to wire up with a Segment Tree for queries.
    // ─────────────────────────────────────────────────────────────

    /** Entry time of node u (= index in the flat array) */
    public int tin(int u) { return tin[u]; }

    /** Exit time of node u (= last index of u's subtree) */
    public int tout(int u) { return tout[u]; }

    /** The flat array: order[i] = node at position i */
    public int[] getOrder() { return order; }

    /** Number of nodes in u's subtree = tout[u] - tin[u] + 1 */
    public int subtreeSize(int u) { return tout[u] - tin[u] + 1; }

    // ─────────────────────────────────────────────────────────────
    // EXAMPLE: Euler Tour + Segment Tree for subtree sum queries.
    //
    // Given node values val[], answer:
    //   1. subtreeSum(u) — sum of all values in u's subtree
    //   2. updateNode(u, newVal) — change val[u] and update the tree
    //
    // Approach:
    //   - Compute Euler Tour to get tin[], tout[], order[].
    //   - Build a flat array where flat[tin[u]] = val[u].
    //   - Build a Segment Tree over the flat array.
    //   - subtreeSum(u) = segTree.query(tin[u], tout[u])
    //   - updateNode(u, newVal) = segTree.update(tin[u], newVal)
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        /*
         * Tree structure (rooted at 0):
         *
         *         0 (val=1)
         *        / \
         *   (val=2)1  2(val=3)
         *      / \     \
         * (val=4)3 4(val=5) 5(val=6)
         */
        int n = 6;
        int[] val = {1, 2, 3, 4, 5, 6}; // value of each node

        // Build the tree
        EulerTour et = new EulerTour(n);
        et.addEdge(0, 1);
        et.addEdge(0, 2);
        et.addEdge(1, 3);
        et.addEdge(1, 4);
        et.addEdge(2, 5);

        // Compute Euler Tour from root = 0
        et.computeTour(0);

        // Print the tour info
        System.out.println("Node:  0  1  2  3  4  5");
        System.out.println("tin:   " + Arrays.toString(et.tin));
        System.out.println("tout:  " + Arrays.toString(et.tout));
        System.out.println("order: " + Arrays.toString(et.order));

        // Build a flat array in Euler order: flat[i] = val[order[i]]
        int[] flat = new int[n];
        for (int i = 0; i < n; i++) {
            flat[i] = val[et.order[i]];
        }
        System.out.println("flat:  " + Arrays.toString(flat));

        // Build Segment Tree over the flat array
        SegmentTree seg = new SegmentTree(flat);

        // Subtree sum of node 1 → query range [tin[1], tout[1]]
        // Subtree of 1 = {1, 3, 4} → values {2, 4, 5} → sum = 11
        int sum1 = seg.query(et.tin(1), et.tout(1));
        System.out.println("Subtree sum of node 1: " + sum1); // 11

        // Subtree sum of node 0 (entire tree) → 1+2+3+4+5+6 = 21
        int sum0 = seg.query(et.tin(0), et.tout(0));
        System.out.println("Subtree sum of node 0: " + sum0); // 21

        // Update node 3's value from 4 to 10
        // Node 3 sits at flat index tin[3], so update that position
        seg.update(et.tin(3), 10);

        // Subtree sum of node 1 is now {2, 10, 5} = 17
        int sum1Updated = seg.query(et.tin(1), et.tout(1));
        System.out.println("Subtree sum of node 1 after update: " + sum1Updated); // 17
    }
}
