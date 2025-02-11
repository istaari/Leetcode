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