### **Core Definitions**

* **Mutually Exclusive Events:** Events that cannot happen at the same time. "If one happens, the other cannot."
    * *Example:* Flipping a coin. It can be Heads **or** Tails, but never both at once.
    * **Notation:** $P(A \cap B) = 0$

---

### **Basic Counting Principles**

These are the building blocks for all other counting rules.

#### **A. Addition Principle ( The "OR" Rule)**
Used when you must choose **one** option from multiple distinct groups.
* **Rule:** If events are mutually exclusive, add the number of outcomes.
* **Formula:** $\text{Total} = m + n$
* **Example:** You are at a restaurant. You can pick **one** drink. They have 3 sodas and 2 juices.
    * Total choices = $3 + 2 = 5$ choices.

#### **B. Multiplication Principle (The "AND" Rule)**
Used when you must make a sequence of choices (one after another).
* **Rule:** If events are independent (the first choice doesn't affect the second), multiply the outcomes.
* **Formula:** $\text{Total} = m \times n$
* **Example:** You need to pick an outfit consisting of **one** shirt and **one** pair of pants. You have 3 shirts and 2 pairs of pants.
    * Total outfits = $3 \times 2 = 6$ outfits.

#### **C. Inclusion-Exclusion Principle**
Used when sets overlap, to ensure you don't count the overlapping items twice.
* **Formula:** $|A \cup B| = |A| + |B| - |A \cap B|$
* **Example:** In a class of 30 students:
    * 15 play Soccer ($A$)
    * 10 play Basketball ($B$)
    * 5 play **both** ($A \cap B$)
    * How many play at least one sport?
    * $\text{Total} = 15 + 10 - 5 = 20$ students.



---

### **Permutations vs. Combinations**

This is the most common confusion point. The key question to ask is: **"Does the order matter?"**

| Feature | Permutation | Combination |
| :--- | :--- | :--- |
| **Concept** | Arrangement / Ranking | Selection / Grouping |
| **Order?** | **YES**, Order matters | **NO**, Order doesn't matter |
| **Keywords** | Arrange, Schedule, Rank, Password | Choose, Select, Group, Team |
| **Analogy** | A **Lock** code ($1\text{-}2\text{-}3$ is different from $3\text{-}2\text{-}1$) | A **Fruit Salad** (Apple & Banana is the same as Banana & Apple) |

#### **1. Permutations (Order Matters)**
The number of ways to arrange $r$ items from a set of $n$ distinct items.

* **Formula:**
    $$P(n, r) = \frac{n!}{(n - r)!}$$

* **Example:**
    There are 3 runners ($A, B, C$) competing for Gold and Silver (top 2 spots).
    * $n=3$ (runners), $r=2$ (medals).
    * $$P(3, 2) = \frac{3!}{(3-2)!} = \frac{3 \times 2 \times 1}{1} = 6$$
    * *Outcomes:* $(A,B), (B,A), (A,C), (C,A), (B,C), (C,B)$.
    * *Note:* $(A,B)$ means A gets Gold, B gets Silver. This is different from $(B,A)$.

#### **2. Combinations (Order Doesn't Matter)**
The number of ways to select $r$ items from a set of $n$ distinct items.

* **Formula:**
    $$C(n, r) \text{ or } \binom{n}{r} = \frac{n!}{r!(n - r)!}$$

* **Example:**
    You have 3 friends ($A, B, C$) and want to invite 2 of them to dinner.
    * $n=3$ (friends), $r=2$ (invites).
    * $$C(3, 2) = \frac{3!}{2!(3-2)!} = \frac{6}{2 \times 1} = 3$$
    * *Outcomes:* $\{A, B\}, \{A, C\}, \{B, C\}$.
    * *Note:* Inviting A and B is the exact same distinct event as inviting B and A.

---

### **Handling Repetition**

Sometimes you can pick the same item more than once (like a password `111` or scoops of ice cream).

#### **1. Permutations with Repetition**
Used when items can be reused in the arrangement.

* **Formula:**
    $$P_{rep} = n^r$$

* **Example:** A standard bicycle lock has 4 dials, each with digits 0-9 ($n=10$). How many codes are possible?
    * $$10^4 = 10,000 \text{ codes.}$$

#### **2. Combinations with Repetition**
Used when you just want to fill a "bag" with items, and you can pick the same item multiple times. This is often called the **"Stars and Bars"** method.

* **Formula:**
    $$C_{rep}(n, r) = \binom{n + r - 1}{r} = \frac{(n + r - 1)!}{r!(n - 1)!}$$

* **Example (The Ice Cream Rule):**
    An ice cream shop has 3 flavors (Chocolate, Vanilla, Strawberry). You want to buy a bowl with 2 scoops. You can pick two of the same flavor.
    * $n=3$ (flavors), $r=2$ (scoops).
    * $$\binom{3 + 2 - 1}{2} = \binom{4}{2} = \frac{4 \times 3}{2 \times 1} = 6 \text{ ways.}$$
    * *Outcomes:* $\{CC, CV, CS, VV, VS, SS\}$.

---

### **Catalan Numbers**

Catalan numbers describe a specific sequence of integers that appear in many recursive counting problems.

* **Formula:**
    $$C_n = \frac{1}{n+1}\binom{2n}{n} = \frac{(2n)!}{(n+1)! \, n!}$$

* **The Sequence ($C_0, C_1, C_2...$):**
    $1, 1, 2, 5, 14, 42, 132, \dots$

* **Common Applications (When to use them):**
    1.  **Valid Parentheses:** How many ways can $n$ pairs of parentheses be correctly matched?
        * For $n=3$ ($C_3=5$): `((()))`, `()(())`, `()()()`, `(())()`, `(()())`
    2.  **Binary Trees:** Number of different binary trees with $n$ nodes.
    3.  **Polygon Triangulation:** Number of ways to cut a polygon with $n+2$ sides into triangles.

To find (calculate) Catalan numbers efficiently, you have two main approaches: using the **Direct Formula** (best if you already have an `nCr` function) or using the **Iterative Recurrence** (best for calculating a specific Catalan number from scratch without overflow).

Here is how to calculate them efficiently.

#### **Method 1: The Iterative Way (Best for Coding)**

Instead of calculating huge factorials like $(2n)!$, which overflow very quickly, we can use a simple relationship between $C_n$ and $C_{n-1}$.

**The Formula:**
$$C_n = \frac{2(2n - 1)}{n + 1} \times C_{n-1}$$

**Why this is good:**

  * **Time Complexity:** $O(n)$ (Linear).
  * **Space Complexity:** $O(1)$.
  * **Overflow Safety:** It keeps numbers smaller for longer compared to calculating $(2n)!$ directly.

**Java Code (Compact & Iterative):**

```java
public class Catalan {
    // Returns the nth Catalan number
    public static long catalan(int n) {
        long res = 1; // C_0 = 1

        // Calculate C_n using the previous value (C_{i-1})
        for (int i = 1; i <= n; i++) {
            res = (res * 2 * (2 * i - 1)) / (i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("C_5 = " + catalan(5)); // Output: 42
    }
}
```


#### **Method 2: The Direct Formula (Using nCr)**

If you already have a helper function to calculate Combinations ($\binom{n}{r}$), you can simply wrap it.

**The Formula:**
$$C_n = \frac{1}{n+1} \times \binom{2n}{n}$$

**Java Code:**

```java
public static long catalanDirect(int n) {
    // Assuming you have the nCr function we discussed earlier
    long c = binomialCoeff(2 * n, n); 
    return c / (n + 1);
}
```


#### **3. Why does this sequence happen? (The Recursive Insight)**

If you are asking "How do we find/derive this sequence in a problem?", it usually comes from breaking a problem into two sub-problems (Left and Right).

Take **Binary Trees** as an example. To form a tree with $n$ nodes:

1.  Pick 1 node as the **Root**.
2.  You have $n-1$ nodes left.
3.  You can split them: $i$ nodes go to the **Left** child, and remaining $n-1-i$ nodes go to the **Right**.
4.  This creates the recursive sum (Segner's Recurrence):
    $$C_n = \sum_{i=0}^{n-1} C_i \times C_{n-1-i}$$


  * **Example for $n=3$:**
      * (0 on Left, 2 on Right) + (1 on Left, 1 on Right) + (2 on Left, 0 on Right)
      * $C_0C_2 + C_1C_1 + C_2C_0$
      * $(1 \times 2) + (1 \times 1) + (2 \times 1) = 5$.


-----

### **Binomial Coefficients: $\binom{n}{k}$**

Binomial coefficients represent the number of ways to choose $k$ items from a set of $n$ distinct items (order doesn't matter).

#### **Method 1: Pascal's Triangle (Dynamic Programming)**

This method uses the recurrence relation: $\binom{n}{k} = \binom{n-1}{k-1} + \binom{n-1}{k}$. This avoids large factorials and is perfect for computing values modulo a number.

[Image of Pascal's Triangle]

**Time Complexity:** $O(n^2)$

```java
public class BinomialDP {
    public static long binomialCoeff(int n, int k) {
        long[][] C = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                // Base Cases: Choose 0 or Choose all = 1
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    // Recurrence relation: sum of two values above
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }
        return C[n][k];
    }

    public static void main(String[] args) {
        System.out.println("C(5, 2) = " + binomialCoeff(5, 2)); // Output: 10
    }
}
```

#### **Method 2: Factorial Definition (Modular Inverse)**

Uses the formula $\binom{n}{k} = \frac{n!}{k!(n-k)!}$.
When working with large numbers (modulo $10^9+7$), division isn't allowed. Instead, we multiply by the **Modular Inverse** of the denominator using Fermat's Little Theorem ($a^{MOD-2} \equiv a^{-1} \pmod{MOD}$).

**Time Complexity:** $O(n)$ (or $O(\log MOD)$ if factorials are precomputed).

```java
public class BinomialInverse {
    static final int MOD = 1000000007;

    // Function to compute (base^exp) % mod
    static long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    static long modInverse(long n) {
        return power(n, MOD - 2);
    }

    static long nCr(int n, int r) {
        if (r < 0 || r > n) return 0;
        if (r == 0 || r == n) return 1;
        if (r > n / 2) r = n - r;

        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++)
            fact[i] = (fact[i - 1] * i) % MOD;

        return (fact[n] * modInverse((fact[r] * fact[n - r]) % MOD)) % MOD;
    }

    public static void main(String[] args) {
        System.out.println("C(5, 2) % MOD = " + nCr(5, 2));
    }
}
```

-----

### **Derangements (\!n)**

A Derangement is a permutation of elements where **no element appears in its original position**.
*Example:* For set $\{1, 2, 3\}$, the derangements are $\{2, 3, 1\}$ and $\{3, 1, 2\}$. $\{1, 3, 2\}$ is NOT a derangement because $1$ is in the first spot.

#### **Method 1: Principle of Inclusion-Exclusion**

Formula: $D_n = n! \sum_{i=0}^{n} \frac{(-1)^i}{i!}$
This expands to: $D_n = n! (1 - \frac{1}{1!} + \frac{1}{2!} - \frac{1}{3!} + \dots)$

#### **Method 2: Dynamic Programming (Recurrence)**

A simpler recurrence relation exists:
$D_n = (n-1) \times (D_{n-1} + D_{n-2})$
*Base cases:* $D_1 = 0, D_2 = 1$.

```java
public class Derangement {
    public static long countDerangements(int n) {
        if (n == 1) return 0;
        if (n == 2) return 1;

        long prev2 = 0; // D_1
        long prev1 = 1; // D_2
        long current = 0;

        for (int i = 3; i <= n; i++) {
            current = (i - 1) * (prev1 + prev2);
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static void main(String[] args) {
        System.out.println("Derangements of 4 items: " + countDerangements(4)); // Output: 9
    }
}
```

-----

### **Stars and Bars**

This technique counts the number of ways to distribute $n$ identical items into $k$ distinct bins.

  * **Theorem:** The number of ways is $\binom{n+k-1}{k-1}$ (or equivalently $\binom{n+k-1}{n}$).

**Example:**
How many ways can you distribute 7 indistinguishable coins among 3 distinct people?

  * $n = 7$ (stars/coins), $k = 3$ (bars/people).
  * Formula: $\binom{7+3-1}{3-1} = \binom{9}{2} = 36$ ways.

<!-- end list -->

```java
// This relies on the nCr function defined in the first section
public class StarsAndBars {
    public static void main(String[] args) {
        int coins = 7;
        int people = 3;
        // We use the nCr logic from before. 
        // Note: For simplicity, assuming small numbers or using the BigInteger/DP approach
        System.out.println("Ways to distribute: " + BinomialDP.binomialCoeff(coins + people - 1, people - 1));
    }
}
```

