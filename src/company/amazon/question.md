## **1. Max Increasing Rating Pairs**

### **Problem Statement**

IMDB, a subsidiary of Amazon, is a popular platform to find ratings on movies and TV shows. Data analysts are exploring user preferences by analyzing sequences of movie ratings.

One metric they are interested in is the **number of indices `i` such that `ratings[i] < ratings[i + 1]`**. They want to **maximize this count** by rearranging the ratings in the most optimal way.

Given an array `ratings` of `n` integers, determine the **maximum possible number of such increasing positions** after any rearrangement of the array.

### **Function Signature (Java)**

```java
public static int getMaxIncrements(int[] ratings)
```


### **Parameters**

- `int[] ratings`: An array of integers representing movie ratings.


### **Returns**

- `int`: The **maximum number of indices `i`** such that `ratings[i] < ratings[i + 1]` after optimally rearranging the array.


### **Constraints**

- `2 ≤ n ≤ 2 * 10^5`
- `1 ≤ ratings[i] ≤ 2 * 10^5`


### **Example 1**

#### **Input**

```plaintext
n = 3
ratings = [2, 1, 3]
```

#### **Explanation**

All possible rearrangements:

| Arrangement | Indices where `ratings[i] < ratings[i+1]` | Count |
|-------------|-------------------------------------------|-------|
| [1, 2, 3]   | [0, 1]                                    | 2     |
| [1, 3, 2]   | [0]                                       | 1     |
| [2, 1, 3]   | [1]                                       | 1     |
| [2, 3, 1]   | [0]                                       | 1     |
| [3, 1, 2]   | [1]                                       | 1     |
| [3, 2, 1]   | []                                        | 0     |

The best possible count is **2**, so the output is:

#### **Output**

```plaintext
2
```

### **Sample Case 0**

#### **Input**

```plaintext
ratings = [2, 1, 1, 2]
```

#### **Output**

```plaintext
2
```

#### **Explanation**

Optimal arrangement: `[1, 2, 1, 2]`  
Indices where `ratings[i] < ratings[i + 1]` are `[0, 1]`.

### **Sample Case 1**

#### **Input**

```plaintext
ratings = [2, 3, 1, 5, 4]
```

#### **Output**

```plaintext
4
```

#### **Explanation**

Optimal arrangement: `[1, 2, 3, 4, 5]`  
Increasing indices: `[0, 1, 2, 3]`

---

## **2.  AWS Server Purchase Optimization**

### **Problem Statement**

AWS provides a range of servers to meet their clients' deployment and computation needs. One AWS client wants to purchase servers to deploy their application.

You are given a description of **`n`** servers in the form of two arrays:

- `efficiency[i]`: The computational power of the `i-th` server.
- `cost[i]`: The AWS credits required to purchase the `i-th` server, where each cost is either **1 or 2**.

You are also given an integer **`k`**, representing the minimum total efficiency required.

Your task is to find the **minimum total cost** required to purchase a subset of servers such that the **sum of their efficiencies is at least `k`**.

If it is **not possible** to achieve a total efficiency ≥ `k`, return **-1**.

**Note:** A server can be purchased at most once.


### **Function Signature (Java)**

```java
public static int findMinimumCost(int[] efficiency, int[] cost, long k)
```


### **Parameters**

- `int[] efficiency`: List of integers representing server efficiencies.
- `int[] cost`: List of integers (1 or 2) representing server costs.
- `long k`: Target minimum efficiency sum.


### **Returns**

- `int`: The **minimum possible cost** to achieve at least `k` efficiency, or **-1** if not possible.


### **Constraints**

- `1 ≤ n ≤ 10^5`
- `1 ≤ k ≤ 10^14`
- `1 ≤ efficiency[i] ≤ 10^9`
- `cost[i] ∈ {1, 2}`


### **Example**

#### **Input**

```plaintext
efficiency = [4, 4, 6, 7]
cost = [1, 1, 2, 2]
k = 7
```

#### **Output**

```plaintext
2
```

#### **Explanation**

We can achieve at least 7 efficiency using:

- Server 0 and Server 1: Efficiency = 4 + 4 = 8, Cost = 1 + 1 = **2**
- Or just Server 3: Efficiency = 7, Cost = **2**

The minimum cost is **2**.


### **Sample Case 0**

#### **Input**

```plaintext
efficiency = [75, 104, 72, 72, 8, 125]
cost =       [1,   2,   2,  1, 2,   1]
k = 376
```

#### **Output**

```plaintext
5
```

#### **Explanation**

One possible valid selection is:

- Servers 1, 2, 3, and 5  
  Efficiency = 104 + 72 + 72 + 125 = **373**

To reach at least 376, we can use:

- Servers 0, 1, 2, and 5  
  Efficiency = 75 + 104 + 72 + 125 = **376**  
  Cost = 1 + 2 + 2 + 1 = **6**

But there's a better combination:

- Servers 1, 2, 4, and 5  
  Efficiency = 104 + 72 + 8 + 125 = **309** — not enough

Eventually, we find an optimal selection that reaches at least **376** efficiency with a **minimum cost of 5**.
 me know if you want to include sample input/output format or implementation hints too!