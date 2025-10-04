package dsa;

public class UnionFindBySize {

    private final int[] parent;
    private final int[] size;

    public UnionFindBySize(int size) {
        parent = new int[size];
        this.size = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            this.size[i] = 1;
        }
    }

    // Find the root of the set containing x with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union two sets
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // Attach the smaller tree under the root of the larger tree
        if (size[rootX] > size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
    }
}
