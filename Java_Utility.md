## **📌 HashMap – Key-Value Storage (O(1) Average Time Complexity)**

A `HashMap<K, V>` stores key-value pairs with **fast lookups**, insertions, and deletions.

### **🔹 Lesser-Known but Useful Methods**

| Method                                                           | Description                                                     |
|------------------------------------------------------------------|-----------------------------------------------------------------|
| `compute(K key, BiFunction<K, V, V> remappingFunction)`          | Updates a value using a function (or removes if `null`).        |
| `computeIfAbsent(K key, Function<K, V> mappingFunction)`         | Adds a key-value pair **only if the key is missing**.           |
| `computeIfPresent(K key, BiFunction<K, V, V> remappingFunction)` | Updates value **only if the key exists**.                       |
| `getOrDefault(K key, V defaultValue)`                            | Returns a value if present; otherwise, returns a default.       |
| `putIfAbsent(K key, V value)`                                    | Inserts a value **only if the key is missing**.                 |
| `merge(K key, V value, BiFunction<V, V, V> remappingFunction)`   | Combines existing and new values using a function.              |
| `replace(K key, V newValue)`                                     | Replaces the value **only if the key exists**.                  |
| `replace(K key, V oldValue, V newValue)`                         | Replaces a value **only if it matches a given old value**.      |
| `keySet()`                                                       | Returns a `Set<K>` of all keys.                                 |
| `values()`                                                       | Returns a `Collection<V>` of all values.                        |
| `entrySet()`                                                     | Returns a `Set<Map.Entry<K, V>>` for iterating key-value pairs. |

---

## **📌 TreeMap – Sorted Key-Value Storage (O(log n) Time Complexity)**

A `TreeMap<K, V>` stores key-value pairs in **sorted order**.

### **🔹 Lesser-Known but Useful Methods**

| Method                                                                   | Description                                                                      |
|--------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `higherKey(K key)`                                                       | Returns the **smallest key** that is strictly **greater** than the given key.    |
| `lowerKey(K key)`                                                        | Returns the **largest key** that is strictly **less** than the given key.        |
| `ceilingKey(K key)`                                                      | Returns the **smallest key** that is **greater than or equal** to the given key. |
| `floorKey(K key)`                                                        | Returns the **largest key** that is **less than or equal** to the given key.     |
| `pollFirstEntry()`                                                       | Removes and returns the **smallest (first) entry** in the map.                   |
| `pollLastEntry()`                                                        | Removes and returns the **largest (last) entry** in the map.                     |
| `descendingMap()`                                                        | Returns a **reverse-order view** of the map.                                     |
| `subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)` | Returns a **portion** of the map between two keys.                               |
| `headMap(K toKey, boolean inclusive)`                                    | Returns a view of the map for **keys less than toKey**.                          |
| `tailMap(K fromKey, boolean inclusive)`                                  | Returns a view of the map for **keys greater than fromKey**.                     |

---

## **📌 TreeSet – Sorted Unique Elements (O(1) Average Time Complexity)**

A `TreeSet<E>` stores **unique elements** in **sorted order**.

| Method                                                                           | Description                                                     |
|----------------------------------------------------------------------------------|-----------------------------------------------------------------|
| `higher(E e)`                                                                    | Returns the **smallest element** strictly **greater** than `e`. |
| `lower(E e)`                                                                     | Returns the **largest element** strictly **less** than `e`.     |
| `ceiling(E e)`                                                                   | Returns the **smallest element** greater than or equal to `e`.  |
| `floor(E e)`                                                                     | Returns the **largest element** less than or equal to `e`.      |
| `pollFirst()`                                                                    | Removes and returns the **first (smallest) element**.           |
| `pollLast()`                                                                     | Removes and returns the **last (largest) element**.             |
| `descendingSet()`                                                                | Returns a **reverse-order view** of the set.                    |
| `subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive)` | Returns a **portion** of the set within a range.                |

---

# **📌 StringBuilder – Mutable and Efficient Strings**

A `StringBuilder` is a **mutable** sequence of characters, making it more efficient than `String` for **frequent
modifications**.

### **🔹 Lesser-Known but Useful Methods**

| Method                                                         | Description                                         |
|----------------------------------------------------------------|-----------------------------------------------------|
| `setCharAt(int index, char ch)`                                | Replaces a character at a specific index.           |
| `getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)` | Copies a substring into a `char[]`.                 |
| `replace(int start, int end, String str)`                      | Replaces part of the string with another string.    |
| `delete(int start, int end)`                                   | Removes a substring.                                |
| `deleteCharAt(int index)`                                      | Removes a specific character.                       |
| `insert(int offset, char c)`                                   | Inserts a character at a given position.            |
| `ensureCapacity(int minCapacity)`                              | Ensures enough capacity to avoid resizing overhead. |
| `trimToSize()`                                                 | Reduces the capacity to match the current length.   |
| `codePointAt(int index)`                                       | Returns the Unicode code point at a position.       |
| `reverse()`                                                    | Reverses the sequence of characters.                |

---

# **📌 Arrays Utility – Efficient Array Manipulation**

The `Arrays` class provides **static methods** for common operations on arrays.

### **🔹 Lesser-Known but Useful Methods**

| Method                                                     | Description                                                           |
|------------------------------------------------------------|-----------------------------------------------------------------------|
| `Arrays.asList(T... a)`                                    | Converts an array into a `List<T>`.                                   |
| `Arrays.copyOf(T[] original, int newLength)`               | Copies an array with a new length.                                    |
| `Arrays.copyOfRange(T[] original, int from, int to)`       | Copies a sub-array.                                                   |
| `Arrays.fill(T[] a, T val)`                                | Fills an array with a specific value.                                 |
| `Arrays.setAll(int[] array, IntUnaryOperator generator)`   | Sets values using a lambda function.                                  |
| `Arrays.parallelPrefix(int[] array, IntBinaryOperator op)` | Performs cumulative operations efficiently.                           |
| `Arrays.parallelSort(T[] a)`                               | Sorts the array using **parallel sorting** (faster for large arrays). |
| `Arrays.compare(T[] a, T[] b)`                             | Compares two arrays lexicographically.                                |
| `Arrays.mismatch(T[] a, T[] b)`                            | Returns the first differing index between two arrays.                 |

---

### **Matrix Traversals Commonly Used in Dynamic Programming (DP) Problems**

---

### **1. Row-wise Traversal (Left to Right)**

- **Usage:** Used in **1D DP table filling** or **grid-based DP** where each state depends on the left or top cell.
- **Example Problem:** Longest Common Subsequence (LCS), Coin Change.

```
for (int i = 0; i < rows; i++) {
    for (int j = 0; j < cols; j++) {
        dp[i][j] = ... // DP transition
    }
}
```

---

### **2. Column-wise Traversal (Top to Bottom)**

- **Usage:** Used when DP transition depends on the **above cell**.
- **Example Problem:** Minimum Path Sum.

```cpp
for (int j = 0; j < cols; j++) {
    for (int i = 0; i < rows; i++) {
        dp[i][j] = ... // DP transition
    }
}
```

---

### **3. Diagonal Traversal**

- **Usage:** Used in **problems where state transition depends on diagonal elements**.
- **Example Problem:** Longest Palindromic Subsequence (LPS), Matrix Chain Multiplication.

```cpp
for (int d = 0; d < rows + cols - 1; d++) {
    int row = max(0, d - cols + 1);
    int col = min(d, cols - 1);
    while (row < rows && col >= 0) {
        dp[row][col] = ... // DP transition
        row++;
        col--;
    }
}
```

---

### **4. Zig-Zag Diagonal Traversal**

- **Usage:** Similar to diagonal traversal but alternates between top-left to bottom-right and vice versa.
- **Example Problem:** DP on grids with non-traditional dependencies.

```cpp
for (int d = 0; d < rows + cols - 1; d++) {
    if (d % 2 == 0) { // Top to Bottom
        int row = min(d, rows - 1);
        int col = max(0, d - rows + 1);
        while (row >= 0 && col < cols) {
            dp[row][col] = ... // DP transition
            row--;
            col++;
        }
    } else { // Bottom to Top
        int col = min(d, cols - 1);
        int row = max(0, d - cols + 1);
        while (col >= 0 && row < rows) {
            dp[row][col] = ... // DP transition
            col--;
            row++;
        }
    }
}
```

---

### **5. L-Shaped Traversal**

- **Usage:** Used in problems where DP state depends on **rightward and downward moves**.
- **Example Problem:** Unique Paths, Minimum Cost Path.

```cpp
for (int i = 0; i < rows; i++) {
    dp[i][0] = ... // Base case for leftmost column
}
for (int j = 1; j < cols; j++) {
    dp[rows - 1][j] = ... // Base case for bottom row
}
```

---

### **6. Wave Traversal**

- **Usage:** Useful in **problems where alternating transitions are required**.
- **Example Problem:** Problems with **alternating row-based dependencies**.

```cpp
for (int j = 0; j < cols; j++) {
    if (j % 2 == 0) {
        for (int i = 0; i < rows; i++) dp[i][j] = ... // DP transition
    } else {
        for (int i = rows - 1; i >= 0; i--) dp[i][j] = ... // DP transition
    }
}
```
