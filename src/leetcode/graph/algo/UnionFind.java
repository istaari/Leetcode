package leetcode.graph.algo;

/**
 * Union-Find (Disjoint Set Union) — By Rank
 *
 * PROBLEM IT SOLVES:
 *   Efficiently manages a collection of disjoint (non-overlapping) sets.
 *   Supports two operations:
 *     - find(x):    Which set does x belong to? (returns the representative/root)
 *     - union(x,y): Merge the sets containing x and y into one set.
 *
 * WHEN TO USE:
 *   - Detect cycles in undirected graphs (if find(u) == find(v) before adding edge u-v, cycle exists).
 *   - Count connected components (each root = one component).
 *   - Kruskal's MST algorithm (check if adding an edge creates a cycle).
 *   - Problems: Number of Connected Components (LC 323), Redundant Connection (LC 684),
 *     Accounts Merge (LC 721), Graph Valid Tree (LC 261), etc.
 *
 * KEY OPTIMIZATIONS:
 *   1. PATH COMPRESSION (in find):
 *      After finding the root, make every node on the path point directly to the root.
 *      This flattens the tree, making future finds nearly O(1).
 *
 *   2. UNION BY RANK:
 *      Always attach the shorter tree under the taller tree's root.
 *      Keeps the tree balanced, preventing degeneration into a linked list.
 *      Rank is an upper bound on the tree height.
 *
 * ALGORITHM STEPS:
 *   Initialization:
 *     - parent[i] = i  (each element is its own root/set)
 *     - rank[i] = 1    (each tree starts with height 1)
 *
 *   find(x):
 *     Step 1: If x is its own parent, return x (it's the root).
 *     Step 2: Otherwise, recursively find root of parent[x].
 *     Step 3: Set parent[x] = root (path compression — shortcut to root).
 *     Step 4: Return the root.
 *
 *   union(x, y):
 *     Step 1: Find roots: xRoot = find(x), yRoot = find(y).
 *     Step 2: If same root, they're already in the same set — do nothing.
 *     Step 3: Attach shorter tree under taller tree (compare ranks).
 *             If equal rank, pick one as root and increment its rank.
 *
 * TIME:  O(α(n)) per operation, where α is the inverse Ackermann function.
 *        Effectively O(1) amortized for all practical inputs.
 * SPACE: O(n) for parent[] and rank[] arrays.
 */
public class UnionFind {

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

    // find(x): Returns the root representative of x's set.
    //   Uses PATH COMPRESSION: after finding the root, every node on the
    //   path from x to root gets its parent set directly to root.
    //   This flattens the tree for fast future lookups.
    //
    //   Example: find(5) where 5->3->1->0 (root)
    //     After: 5->0, 3->0, 1->0 (all point to root directly)
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    // union(x, y): Merge the sets containing x and y.
    //   Uses UNION BY RANK: attach the shorter tree under the taller one.
    //   This keeps the tree balanced (height grows only when equal ranks merge).
    //
    //   Cases:
    //     rank[xRoot] < rank[yRoot]: attach x's tree under y's root.
    //     rank[xRoot] > rank[yRoot]: attach y's tree under x's root.
    //     equal ranks: pick x as root, increment x's rank.
    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) return; // Already in the same set

        // Attach the smaller rank tree under the root of the larger rank tree
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot]++;
        }
    }

    public static void main(String[] args) {
        UnionFind unionFind = new UnionFind(8);
        unionFind.union(1, 2);
        unionFind.union(2, 3);
        unionFind.union(4, 5);
        unionFind.union(5, 6);
        unionFind.union(6, 7);

        System.out.println("\nAfter find(3):");
        unionFind.find(3);
        System.out.println(unionFind);

        System.out.println("\nAfter find(7):");
        unionFind.find(7);
        System.out.println(unionFind);

    }
}





