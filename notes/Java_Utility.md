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

### **🔹 Code Examples**

```java
Map<String, Integer> hashMap = new HashMap<>();

hashMap.put("A", 10);
Integer value = hashMap.putIfAbsent("A", 20); // Won't add because "A" already exists
System.out.println("map.putIfAbsent(\"A\", 20) : " + value); // 10

value = hashMap.putIfAbsent("B", 30); // Will add "B" because it doesn't exist, returns null
System.out.println("map.putIfAbsent(\"B\", 30) : " + value); // null

// computeIfAbsent — initialize a value only when the key is missing
Map<String, List<Integer>> hashMap2 = new HashMap<>();
List<Integer> value1 = hashMap2.computeIfAbsent("A", key -> new ArrayList<>(List.of(1, 2, 3)));
System.out.println("value1 : " + value1); // [1, 2, 3]
List<Integer> value2 = hashMap2.computeIfAbsent("A", key -> new ArrayList<>());
System.out.println("value2 : " + value2); // [1, 2, 3] (key exists, so function not called)

// computeIfPresent — update a value only when the key already exists
Map<String, List<Integer>> scores = new HashMap<>();
scores.put("Alice", new ArrayList<>(List.of(80, 85)));
scores.computeIfPresent("Alice", (key, existingList) -> {
    existingList.add(90);
    return existingList;
});
scores.computeIfPresent("Bob", (key, existingList) -> { // Does NOTHING — "Bob" doesn't exist
    existingList.add(100);
    return existingList;
});
System.out.println("Alice's scores: " + scores.get("Alice")); // [80, 85, 90]
System.out.println("Bob's scores: " + scores.get("Bob"));     // null
```

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

### **🔹 Code Examples**

```java
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.put(1, "one");
treeMap.put(2, "two");
treeMap.put(3, "three");
treeMap.put(4, "four");

System.out.println("First Key " + treeMap.firstKey());    // 1
System.out.println("Last Key " + treeMap.lastKey());      // 4
System.out.println("Floor Key " + treeMap.floorKey(5));   // 4 (largest key <= 5)
System.out.println("Ceil Key " + treeMap.ceilingKey(0));  // 1 (smallest key >= 0)
```

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
### **🔹 Code Examples**

```java
int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

Arrays.fill(array, 0);                                  // Fill with default values
Arrays.sort(array);                                      // Sort in ascending order
int[] copied = Arrays.copyOf(array, array.length);       // Copied array

// Convert ArrayList to int[]
List<Integer> list = new ArrayList<>(List.of(1, 2, 3, 2, 3));
int[] listArray = list.stream().mapToInt(a -> a).toArray();

// Convert String[] to int[]
String[] stringArray = {"1", "2", "3", "4", "5"};
int[] intArray = Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();

// Sort array in descending order
Integer[] boxed = Arrays.stream(array).boxed().toArray(Integer[]::new);
Arrays.sort(boxed, Collections.reverseOrder());
array = Arrays.stream(boxed).mapToInt(Integer::intValue).toArray();
```

---

# **📌 Collections.binarySearch – Searching in Sorted Lists**

`Collections.binarySearch(list, key)` performs binary search on a **sorted** list.
- Returns the **index** if found.
- Returns `-(insertionPoint) - 1` if not found (negative value encoding where the key would be inserted).

### **🔹 Code Examples**

```java
List<Integer> cIndices = new ArrayList<>(List.of(99, 3, 42, 10, 57, 25));
Collections.sort(cIndices); // Sorts to: [3, 10, 25, 42, 57, 99]

int index;

index = Collections.binarySearch(cIndices, 42);  // Found at index 3
index = Collections.binarySearch(cIndices, 30);  // Not found, returns -4 (insertionPoint = 3)
index = Collections.binarySearch(cIndices, 1);   // Not found, returns -1 (insertionPoint = 0)
index = Collections.binarySearch(cIndices, 100); // Not found, returns -7 (insertionPoint = 6)

// To get the insertion point from a negative result:
// int insertionPoint = -(index + 1);
```
---

### **Matrix Traversals Commonly Used in Dynamic Programming (DP) Problems**



### **1. Diagonal Traversal**

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
