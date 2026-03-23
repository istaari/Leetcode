package dsa.tree;

/**
 * Segment Tree — a binary tree data structure for efficient range queries
 * and point updates on an array.
 *
 * KEY IDEA:
 *   Each node stores an aggregate (here: sum) of a contiguous subarray.
 *   The root covers the entire array [0, n-1].
 *   Its left child covers [0, mid], right child covers [mid+1, n-1], and so on
 *   recursively until each leaf covers a single element.
 *
 * TREE LAYOUT (stored in a flat array, 1-indexed or 0-indexed):
 *   - Node i's left child  = 2*i + 1
 *   - Node i's right child = 2*i + 2
 *   - We allocate 4*n space to safely hold all nodes.
 *
 *         [0..5] sum=36          <- root covers full array
 *        /              \
 *    [0..2] sum=9     [3..5] sum=27
 *    /      \          /       \
 * [0..1]=4  [2]=5   [3..4]=16  [5]=11
 *  /   \              /    \
 * [0]=1 [1]=3      [3]=7  [4]=9
 *
 * TIME COMPLEXITY:
 *   Build:  O(n)        — visits every node once
 *   Query:  O(log n)    — at most 2 nodes per level are visited
 *   Update: O(log n)    — walks one root-to-leaf path
 *
 * SPACE: O(n) — the tree array has at most 4*n entries.
 */
public class SegmentTree {

    private final int[] tree; // internal array storing node values
    private final int n;      // size of the original array

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n]; // 4*n guarantees enough space for any n
        buildTree(arr, 0, 0, n - 1);
    }

    // ─────────────────────────────────────────────────────────────
    // BUILD: Recursively construct the tree bottom-up.
    //
    //   node  = index in the tree[] array for the current segment
    //   start = left boundary of the segment this node covers
    //   end   = right boundary of the segment this node covers
    //
    // Base case: leaf node (start == end) → store the array element.
    // Recursive: build left and right children, then merge (sum).
    // ─────────────────────────────────────────────────────────────
    public void buildTree(int[] arr, int node, int start, int end) {
        if (start == end) {
            // Leaf: this node covers exactly one element arr[start].
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;

            // Build left subtree covering [start, mid]
            buildTree(arr, 2 * node + 1, start, mid);

            // Build right subtree covering [mid+1, end]
            buildTree(arr, 2 * node + 2, mid + 1, end);

            // Internal node = aggregate of its two children
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // ─────────────────────────────────────────────────────────────
    // QUERY: Find the sum of elements in range [l, r].
    //
    //   Three cases at each node covering [start, end]:
    //
    //   1. NO OVERLAP:   [start, end] is completely outside [l, r]
    //      → return 0 (identity for sum)
    //
    //   2. TOTAL OVERLAP: [start, end] is completely inside [l, r]
    //      → return tree[node] directly (no need to go deeper)
    //
    //   3. PARTIAL OVERLAP: some part overlaps
    //      → recurse into both children and combine results
    // ─────────────────────────────────────────────────────────────
    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private int query(int node, int start, int end, int l, int r) {
        // Case 1: No overlap — this segment is entirely outside [l, r]
        if (start > r || end < l) {
            return 0; // identity element for sum
        }

        // Case 2: Total overlap — this segment is entirely within [l, r]
        if (start >= l && end <= r) {
            return tree[node];
        }

        // Case 3: Partial overlap — split and combine
        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    // ─────────────────────────────────────────────────────────────
    // POINT UPDATE: Set arr[index] = value, then propagate changes
    //               up the tree.
    //
    //   Walk from root toward the leaf that holds arr[index].
    //   At each level, go left or right depending on where index falls.
    //   Once we reach the leaf, set its value.
    //   On the way back up, recalculate each ancestor as the sum
    //   of its two children.
    // ─────────────────────────────────────────────────────────────
    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            // Leaf node — update the value
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {
            // index falls in the left child's range [start, mid]
            update(2 * node + 1, start, mid, index, value);
        } else {
            // index falls in the right child's range [mid+1, end]
            update(2 * node + 2, mid + 1, end, index, value);
        }

        // After updating the leaf, recalculate this node from its children
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree(arr);

        // Query sum of arr[1..3] = 3 + 5 + 7 = 15
        System.out.println(segmentTree.query(1, 3)); // 15

        // Update arr[1] = 10, then query arr[1..3] = 10 + 5 + 7 = 22
        segmentTree.update(1, 10);
        System.out.println(segmentTree.query(1, 3)); // 22
    }
}
