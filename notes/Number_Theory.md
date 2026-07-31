# Number Theory

## Table of Contents
| # | Topic | Use For |
|---|---|---|
| 1 | [Trial Division](#1-trial-division) | Test if one number is prime — O(√n) |
| 2 | [Sieve of Eratosthenes](#2-sieve-of-eratosthenes) | All primes up to N — O(N log log N) |
| 3 | [Prime Factorization](#3-prime-factorization) | Break n into prime factors |
| 4 | [Counting Divisors](#4-counting-divisors) | Number of divisors from factorization |
| 5 | [GCD & LCM](#5-gcd--lcm) | Common divisor/multiple (Euclidean) |
| 6 | [Modular Exponentiation](#6-modular-exponentiation) | Compute xⁿ mod m — O(log n) |
| 7 | [Modular Inverse](#7-modular-inverse) | Division under a modulus |
| 8 | [Matrix Exponentiation](#8-matrix-exponentiation) | nth term of linear recurrence — O(log n) |
| 9 | [Reservoir Sampling](#9-reservoir-sampling) | Random k from an unbounded stream |
| — | [Modular Arithmetic Formulas](#modular-arithmetic-formulas) | Quick reference |

### Primality & Factorization — Which Tool?

```
Need info about ONE number?
 ├─ "Is n prime?"            → Trial Division        O(√n)
 ├─ "What are n's factors?"  → Prime Factorization   O(√n)
 └─ "How many divisors?"     → Count via factorization

Need info about MANY numbers (2..N)?
 └─ "All primes up to N?"    → Sieve of Eratosthenes O(N log log N)
```

---

## 1. Trial Division

**Idea:** Test if `n` is prime by dividing it by every integer from 2 to √n.

**Why stop at √n?** Factors come in pairs `a × b = n`. If both were > √n, their product would exceed n. So one factor of every pair is always ≤ √n — checking that far is enough.

```
  n = 29,  √29 ≈ 5.4      Check 2, 3, 4, 5
  29 % 2 ≠ 0   29 % 3 ≠ 0   29 % 4 ≠ 0   29 % 5 ≠ 0   → PRIME ✓
```

```java
public static boolean isPrime(int n) {
    if (n <= 1) return false;
    for (int i = 2; i * i <= n; i++) {   // i*i <= n avoids sqrt() and overflow
        if (n % i == 0) return false;    // found a divisor → composite
    }
    return true;
}
```

---

## 2. Sieve of Eratosthenes

**Idea:** To find *all* primes up to N, repeatedly cross out the multiples of each prime.

**Analogy:** Line up numbers 2..N. Circle 2, strike out every 2nd number after it. Circle the next survivor (3), strike out every 3rd. Repeat — the survivors are prime.

```
  Primes up to 10:
  2  3  4  5  6  7  8  9  10
  ✓  ✓  ✗  ✓  ✗  ✓  ✗  ✗  ✗     strike multiples of 2, then 3
  Stop at 3 (next prime 5, 5² = 25 > 10)
  → Primes: 2, 3, 5, 7
```

```java
public static void sieve(int n) {
    boolean[] isPrime = new boolean[n + 1];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;

    for (int p = 2; p * p <= n; p++) {
        if (isPrime[p]) {
            for (int i = p * p; i <= n; i += p)  // start at p²: smaller multiples already marked
                isPrime[i] = false;
        }
    }
}
```
> **Why start the inner loop at `p*p`?** Every multiple `k·p` with `k < p` was already struck out by a smaller prime.

---

## 3. Prime Factorization

**Idea:** Every integer > 1 is a unique product of primes (Fundamental Theorem of Arithmetic). Repeatedly divide out the smallest factor.

```
  60 ÷ 2 = 30    30 ÷ 2 = 15    15 ÷ 3 = 5    5 ÷ 5 = 1
  → 60 = 2² × 3 × 5
```

```java
public static List<Integer> getPrimeFactors(int n) {
    List<Integer> factors = new ArrayList<>();
    for (int i = 2; i * i <= n; i++) {
        while (n % i == 0) { factors.add(i); n /= i; }
    }
    if (n > 1) factors.add(n);   // leftover is a prime > √(original n)
    return factors;
}
// 315 → [3, 3, 5, 7]
```

---

## 4. Counting Divisors

**Formula:** If `n = p₁^a₁ × p₂^a₂ × … × pₖ^aₖ`, then

$$\text{divisor count} = (a_1+1)(a_2+1)\cdots(a_k+1)$$

**Intuition:** Each prime `pᵢ` can appear 0, 1, …, or `aᵢ` times in a divisor — that's `(aᵢ+1)` independent choices.

```
  12 = 2² × 3¹   →   (2+1)(1+1) = 6
  Verify: 1, 2, 3, 4, 6, 12  ✓
```

```java
public static int countDivisors(int n) {
    int total = 1;
    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) {
            int count = 0;
            while (n % i == 0) { count++; n /= i; }
            total *= (count + 1);
        }
    }
    if (n > 1) total *= 2;   // leftover prime, power 1 → (1+1)
    return total;
}
```

---

## 5. GCD & LCM

The **Euclidean Algorithm** is the most common math tool in interviews (array rotations, string periods, fractions).

| | Definition | Formula |
|---|---|---|
| **GCD** | Largest number dividing both | `gcd(a, b) = gcd(b, a % b)` until `b = 0` |
| **LCM** | Smallest common multiple | `lcm(a, b) = (a / gcd) * b` |

**Why the Euclid recurrence works:** any common divisor of `a` and `b` also divides `a % b`, so shrinking `(a, b) → (b, a % b)` preserves the GCD while rapidly reducing the numbers.

```
  gcd(48, 18):
  48 % 18 = 12   →   gcd(18, 12)
  18 % 12 =  6   →   gcd(12, 6)
  12 %  6 =  0   →   STOP → GCD = 6
```

```java
public static int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);   // O(log min(a,b))
}
public static int lcm(int a, int b) {
    if (a == 0 || b == 0) return 0;
    return (a / gcd(a, b)) * b;          // divide first → avoids overflow
}
// gcd(48,18)=6   lcm(4,6)=12
```

---

## 6. Modular Exponentiation

**Problem:** Compute `xⁿ mod m` for huge `n` without overflow.
**Idea — Square and Multiply:** read the exponent's bits; square the base each step, multiply into the result only on set bits. O(n) → **O(log n)**.

```
  3⁵ mod 7,  exp = 5 = 101₂
  bit  base(mod7)  result
   1     3          3       (multiply)
   0     3²=2       3       (square only)
   1     2²=4       3·4=12→5 (multiply)   → answer 5
```

```java
public static long power(long base, long exp, long mod) {
    long res = 1;
    base %= mod;
    while (exp > 0) {
        if ((exp & 1) == 1) res = (res * base) % mod;  // set bit → multiply
        base = (base * base) % mod;                     // always square
        exp >>= 1;
    }
    return res;
}
// 3^5 mod 7 = 5
```

---

## 7. Modular Inverse

`a⁻¹ mod m` is the value `x` with `a·x ≡ 1 (mod m)` — it lets you "divide" under a modulus. Pick the method by whether `m` is prime:

| Method | Condition | Core idea | Complexity |
|---|---|---|---|
| **Fermat's Little Theorem** | `m` is **prime** | `a⁻¹ ≡ a^(m-2) mod m` | O(log m) |
| **Extended Euclidean** | `gcd(a, m) = 1` (general) | Solve `ax + my = 1` for `x` | O(log m) |

### 7a. Fermat (prime modulus, e.g. 10⁹+7)
```java
public static int modInverse(int a, int m) {
    return modPow(a, m - 2, m);   // reuse modular exponentiation
}
// inverse of 3 mod 7 = 3^5 mod 7 = 5
```

### 7b. Extended Euclidean (general modulus)
Finds `x, y` with `ax + my = gcd(a, m)`. When `gcd = 1`, taking mod `m` gives `ax ≡ 1`, so `x` is the inverse.
```java
public static int modInverse(int a, int m) {
    int m0 = m, x = 1, y = 0;
    if (m == 1) return 0;
    while (a > 1) {
        int q = a / m, t = m;
        m = a % m; a = t;
        t = y; y = x - q * y; x = t;
    }
    return x < 0 ? x + m0 : x;   // normalize to positive
}
// inverse of 3 mod 11 = 4   (3·4 = 12 ≡ 1)
```

---

## 8. Matrix Exponentiation

**Use for:** the nth term of a linear recurrence (e.g. Fibonacci, Climbing Stairs) when `n` is huge (10⁹). Standard O(n) DP would TLE; this runs in **O(log n)** by applying binary exponentiation to a matrix.

Fibonacci as a matrix transition:

$$\begin{bmatrix} F_{n+1} \\ F_n \end{bmatrix} = \begin{bmatrix} 1 & 1 \\ 1 & 0 \end{bmatrix} \begin{bmatrix} F_n \\ F_{n-1} \end{bmatrix} \quad\Rightarrow\quad F_n = \left(\begin{bmatrix} 1 & 1 \\ 1 & 0 \end{bmatrix}^{\,n-1}\right)_{0,0}$$

```java
static long MOD = 1_000_000_007L;

public static long getNthFibonacci(int n) {
    if (n <= 1) return n;
    long[][] result = matrixPower(new long[][]{{1, 1}, {1, 0}}, n - 1);
    return result[0][0];
}

public static long[][] matrixPower(long[][] base, int exp) {
    long[][] res = {{1, 0}, {0, 1}};        // identity
    while (exp > 0) {
        if ((exp & 1) == 1) res = multiply(res, base);
        base = multiply(base, base);
        exp >>= 1;
    }
    return res;
}

public static long[][] multiply(long[][] A, long[][] B) {
    long[][] C = new long[2][2];
    for (int i = 0; i < 2; i++)
        for (int j = 0; j < 2; j++)
            for (int k = 0; k < 2; k++)
                C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
    return C;
}
```

---

## 9. Reservoir Sampling

**Problem:** Pick `k` items uniformly at random from a stream of **unknown size** that doesn't fit in memory (e.g. a random line from a huge file — LC 382, 398).

**Algorithm R:**
```
1. Fill reservoir with the first k elements.
2. For each later element i (i ≥ k):
     pick random j in [0, i]
     if j < k → reservoir[j] = element i   (else discard)
```
Each element ends up with equal probability `k/n` of being kept.

```java
public static int[] selectKItems(int[] stream, int k) {
    int[] reservoir = new int[k];
    for (int i = 0; i < k; i++) reservoir[i] = stream[i];   // seed

    Random rand = new Random();
    for (int i = k; i < stream.length; i++) {
        int j = rand.nextInt(i + 1);         // 0..i inclusive
        if (j < k) reservoir[j] = stream[i]; // replace with prob k/(i+1)
    }
    return reservoir;
}
```

---

## Modular Arithmetic Formulas

| Operation | Formula |
|---|---|
| Addition | `(a + b) % m = ((a % m) + (b % m)) % m` |
| Subtraction | `(a - b) % m = ((a % m) - (b % m) + m) % m` |
| Multiplication | `(a * b) % m = ((a % m) * (b % m)) % m` |
| Division | `(a / b) % m = ((a % m) * (b⁻¹ % m)) % m` |
| Exponentiation | `(a ^ b) % m = ((a % m) ^ b) % m` |

> **Subtraction gotcha:** always `+ m` before the final `% m` — otherwise a negative intermediate result stays negative in most languages.
