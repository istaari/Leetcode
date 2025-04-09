package dsa;

public class SegmentTree {

    private final int[] tree;
    private final int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n]; // Stores the sum value
        buildTree(arr, 0, 0, n - 1);
    }

    // Build segment tree
    public void buildTree(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            buildTree(arr, 2 * node + 1, start, mid);
            buildTree(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Query segment tree
    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    public int query(int node, int start, int end, int l, int r) {
        // When start and end are out of range
        if (start > r || end < l) {
            return 0;
        }

        // When start and end are between l and r
        if (start >= l && end <= r) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);
        return leftSum + rightSum;

    }

    // Update Segment tree
    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }

    public void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = start + end / 2;

        if (index <= mid) {
            update(2 * node + 1, 0, mid, index, value);
        } else {
            update(2 * node + 2, mid + 1, end, index, value);
        }

        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree(arr);

        System.out.println(segmentTree.query(1, 3)); // 3 + 5 + 7 = 15

        segmentTree.update(1, 10); // arr[1] = 10
        System.out.println(segmentTree.query(1, 3)); // 10 + 5 + 7 = 22
    }


}
