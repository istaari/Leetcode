package leetcode.graph.algo;

/**
 * Union-Find (Disjoint Set Union) — By Size
 *
 * PROBLEM IT SOLVES:
 *   Same as Union-Find by Rank: manages disjoint sets with find and union.
 *   The difference is the UNION STRATEGY: instead of tracking tree height (rank),
 *   we track the NUMBER OF NODES (size) in each set.
 *
 * WHEN TO USE:
 *   - Same use cases as UnionFind (by Rank): cycle detection, connected components,
 *     Kruskal's MST, etc.
 *   - Preferred when you need to KNOW THE SIZE of each component
 *     (e.g., "largest connected component" problems).
 *   - size[find(x)] directly gives the component size — no extra work.
 *
 * KEY DIFFERENCE FROM UNION BY RANK:
 *   - Union by Rank: attach shorter tree under taller tree. Rank tracks height.
 *   - Union by Size: attach smaller set under larger set. Size tracks node count.
 *   - Both achieve the same amortized time complexity.
 *   - Size variant is useful when component sizes are needed.
 *
 * ALGORITHM STEPS:
 *   Initialization:
 *     - parent[i] = i  (each element is its own root)
 *     - size[i] = 1    (each set starts with 1 element)
 *
 *   find(x): Same as by-rank — path compression to flatten the tree.
 *
 *   union(x, y):
 *     Step 1: Find roots: rootX = find(x), rootY = find(y).
 *     Step 2: If same root, already in the same set — do nothing.
 *     Step 3: Attach SMALLER set under LARGER set's root.
 *             Update the size of the new root: size[root] += size[other].
 *
 * TIME:  O(α(n)) per operation (amortized, effectively O(1)).
 * SPACE: O(n) for parent[] and size[] arrays.
 */
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

    // find(x): Returns the root of x's set. Uses path compression.
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    // union(x, y): Merge sets containing x and y.
    //   Attach the SMALLER set under the LARGER set's root.
    //   Then add the smaller set's size to the larger set's size.
    //
    //   Example: union(3, 7) where set(3) has 2 nodes, set(7) has 5 nodes.
    //     rootX=find(3), rootY=find(7)
    //     size[rootX]=2 < size[rootY]=5, so parent[rootX] = rootY
    //     size[rootY] = 5 + 2 = 7
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return; // Already in the same set

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
