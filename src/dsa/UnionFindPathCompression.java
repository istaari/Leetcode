package dsa;


public class UnionFindPathCompression {

    private final int[] parent;
    private final int[] rank;

    public UnionFindPathCompression(int n) {
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
    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

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
        UnionFindPathCompression unionFind = new UnionFindPathCompression(8);
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





