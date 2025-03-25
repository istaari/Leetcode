package dsa;

public class UnionFindWithoutPathCompression {

    private final int[] parent;
    private final int[] rank;

    public UnionFindWithoutPathCompression(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // Find operation without path compression
    public int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    // Union two sets
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // Attach the smaller rank tree under the root of the larger rank tree
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {

            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public static void main(String[] args) {
        UnionFindWithoutPathCompression unionFind = new UnionFindWithoutPathCompression(10);
        // Perform some unions
        unionFind.union(1, 2);
        unionFind.union(2, 3);
        unionFind.union(3, 4);
        unionFind.union(4, 5);
    }

}
