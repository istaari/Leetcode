package dsa.graph;


// Initial Setup:
// Each node is its own parent initially, and the rank is 0 for all.
// Let's assume the nodes are {1, 2, 3, 4, 5, 6, 7}.
// Initial Parent array: [1, 2, 3, 4, 5, 6, 7]
// Initial Rank array: [0, 0, 0, 0, 0, 0, 0]

// Union(1, 2):
// - Since 1 and 2 are not connected, we union them.
// - After this operation, 1 becomes the parent of 2.
// - Since the ranks were the same, we increment the rank of 1.
// - Parent array: [1, 1, 3, 4, 5, 6, 7]
// - Rank array: [1, 0, 0, 0, 0, 0, 0]

// Union(2, 3):
// - Find the parent of 2: it's 1.
// - Find the parent of 3: it's 3.
// - Union the sets containing 2 and 3 by making 1 (parent of 2) the parent of 3.
// - Parent array: [1, 1, 1, 4, 5, 6, 7]
// - Rank array: [1, 0, 0, 0, 0, 0, 0]

// Union(4, 5):
// - Since 4 and 5 are not connected, we union them.
// - After this operation, 4 becomes the parent of 5.
// - Since the ranks were the same, we increment the rank of 4.
// - Parent array: [1, 1, 1, 4, 4, 6, 7]
// - Rank array: [1, 0, 0, 1, 0, 0, 0]

// Union(5, 6):
// - Find the parent of 5: it's 4.
// - Find the parent of 6: it's 6.
// - Union the sets containing 5 and 6 by making 4 (parent of 5) the parent of 6.
// - Parent array: [1, 1, 1, 4, 4, 4, 7]
// - Rank array: [1, 0, 0, 1, 0, 0, 0]

// Union(6, 7):
// - Find the parent of 6: it's 4.
// - Find the parent of 7: it's 7.
// - Union the sets containing 6 and 7 by making 4 (parent of 6) the parent of 7.
// - Parent array: [1, 1, 1, 4, 4, 4, 4]
// - Rank array: [1, 0, 0, 1, 0, 0, 0]

// Final Parents and Ranks:
// - Node 1's parent is 1, Rank is 1.
// - Node 2's parent is 1, Rank is 0.
// - Node 3's parent is 1, Rank is 0.
// - Node 4's parent is 4, Rank is 1.
// - Node 5's parent is 4, Rank is 0.
// - Node 6's parent is 4, Rank is 0.
// - Node 7's parent is 4, Rank is 0.

// This results in two distinct sets:
// - Set 1: {1, 2, 3} with parent 1 and rank 1.
// - Set 2: {4, 5, 6, 7} with parent 4 and rank 1.

public class DisjointSet {
    private final int[] parent;
    private final int[] rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

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
        DisjointSet disjointSet = new DisjointSet(8);
        disjointSet.union(1, 2);
        disjointSet.union(2, 3);
        disjointSet.union(4, 5);
        disjointSet.union(5, 6);
        disjointSet.union(6, 7);
        System.out.println(disjointSet);
    }
}





