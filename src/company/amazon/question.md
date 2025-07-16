## **1. Max Increasing Rating Pairs**

### **Problem**

Given an array `ratings` of `n` integers, rearrange it to **maximize** the number of indices `i` where `ratings[i] < ratings[i + 1]`.

### **Function Signature**

```java
public static int getMaxIncrements(int[] ratings)
```

### **Constraints**

* `2 ≤ n ≤ 2 * 10^5`
* `1 ≤ ratings[i] ≤ 2 * 10^5`

### **Return**

* Maximum number of increasing adjacent pairs after rearrangement.

### **Example**

**Input:**
`ratings = [2, 1, 3]`
**Output:**
`2`
**Explanation:**
Optimal: `[1, 2, 3]` → increasing at indices `0, 1`.

---

## **2. AWS Server Purchase Optimization**

### **Problem**

Given `n` servers with:

* `efficiency[i]`: computational power
* `cost[i] ∈ {1, 2}`: AWS credit cost

Select servers such that total efficiency ≥ `k` with **minimum total cost**. Return `-1` if not possible.

### **Function Signature**

```java
public static int findMinimumCost(int[] efficiency, int[] cost, long k)
```

### **Constraints**

* `1 ≤ n ≤ 10^5`
* `1 ≤ k ≤ 10^14`
* `1 ≤ efficiency[i] ≤ 10^9`

### **Example**

**Input:**
`efficiency = [4, 4, 6, 7]`
`cost = [1, 1, 2, 2]`
`k = 7`
**Output:**
`2`
**Explanation:**
Choose servers 0 & 1 (eff = 8, cost = 2) or just 3 (eff = 7, cost = 2).

---