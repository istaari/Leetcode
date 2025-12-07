### 1\. Trial Division

**Concept:**
Trial division is the most straightforward method for determining if a number $n$ is prime (checking for primality). The basic idea is to try dividing $n$ by every integer from 2 up to $\sqrt{n}$.

  * If $n$ is divisible by any of these numbers, it is **composite** (not prime).
  * If you reach $\sqrt{n}$ without finding a divisor, $n$ is **prime**.

**Why $\sqrt{n}$?**
If $n$ has a factor larger than $\sqrt{n}$, it must also have a matching factor smaller than $\sqrt{n}$. Therefore, checking up to the square root is sufficient.

**Example: Is 29 prime?**

  * $\sqrt{29} \approx 5.38$. We only need to check integers 2, 3, 4, and 5.
  * $29 \pmod 2 \neq 0$
  * $29 \pmod 3 \neq 0$
  * $29 \pmod 4 \neq 0$
  * $29 \pmod 5 \neq 0$
  * **Result:** 29 is Prime.

**Java Code:**

```java
public class TrialDivision {
    public static boolean isPrime(int n) {
        // 0 and 1 are not prime numbers
        if (n <= 1) return false;
        
        // Check from 2 to square root of n
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false; // Found a divisor, not prime
            }
        }
        return true; // No divisors found, it is prime
    }

    public static void main(String[] args) {
        System.out.println("Is 29 prime? " + isPrime(29)); // true
        System.out.println("Is 15 prime? " + isPrime(15)); // false
    }
}
```

-----

### 2\. The Sieve of Eratosthenes

**Concept:**
The Sieve is a highly efficient algorithm to find **all** prime numbers up to a specific limit $N$. Instead of checking each number individually (like trial division), it iteratively marks the multiples of each prime as composite.

**Steps:**

1.  Create a list of consecutive integers from 2 through $N$.
2.  Start with the smallest prime, $p = 2$.
3.  Mark all multiples of $p$ (i.e., $2p, 3p, 4p...$) as composite (not prime).
4.  Find the next number in the list that is not marked. Let this be the new $p$.
5.  Repeat until $p^2 > N$.

**Example: Primes up to 10**

1.  List: 2, 3, 4, 5, 6, 7, 8, 9, 10
2.  Start at **2**. Cross out multiples: 4, 6, 8, 10.
      * *Remaining:* 2, 3, 5, 7, 9
3.  Next is **3**. Cross out multiples: 6 (already done), 9.
      * *Remaining:* 2, 3, 5, 7
4.  Next is **5**. $5^2 = 25$, which is $> 10$, so we stop.
5.  **Primes:** 2, 3, 5, 7.

**Java Code:**

```java
import java.util.Arrays;

public class SieveOfEratosthenes {
    public static void sieve(int n) {
        // boolean array to mark primes. true = prime, false = composite
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true); // Assume all are prime initially
        isPrime[0] = false;
        isPrime[1] = false;

        for (int p = 2; p * p <= n; p++) {
            // If isPrime[p] is not changed, then it is a prime
            if (isPrime[p]) {
                // Update all multiples of p
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }

        // Print all prime numbers
        System.out.print("Primes up to " + n + ": ");
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        sieve(30);
    }
}
```

-----

### 3\. Prime Factorization

**Concept:**
Prime factorization is the process of breaking a composite number down into the product of prime numbers. According to the Fundamental Theorem of Arithmetic, every integer greater than 1 is either a prime itself or can be represented as the product of prime numbers in a unique way.

**Example: Factorize 60**

1.  Check 2: $60 \div 2 = 30$. (Factors: 2)
2.  Check 2 again: $30 \div 2 = 15$. (Factors: 2, 2)
3.  Check 2: 15 not divisible.
4.  Check 3: $15 \div 3 = 5$. (Factors: 2, 2, 3)
5.  Check 5: $5 \div 5 = 1$. (Factors: 2, 2, 3, 5)
6.  **Result:** $60 = 2^2 \times 3 \times 5$

**Java Code:**

```java
import java.util.ArrayList;
import java.util.List;

public class PrimeFactorization {
    
    public static List<Integer> getPrimeFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        
        // Loop from 2 to sqrt(n)
        for (int i = 2; i * i <= n; i++) {
            // While n is divisible by i, add i and divide n
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        
        // If n is still greater than 1, the remainder is a prime
        if (n > 1) {
            factors.add(n);
        }
        
        return factors;
    }

    public static void main(String[] args) {
        int number = 315;
        System.out.println("Prime factors of " + number + ": " + getPrimeFactors(number));
        // Output: [3, 3, 5, 7]
    }
}
```

-----

### 4\. Counting Divisors

**Concept:**
To find the total number of divisors of $n$, you could check every number from 1 to $n$. However, a faster way uses **Prime Factorization**.

If the prime factorization of a number $n$ is:
$$n = p_1^{a_1} \times p_2^{a_2} \times \dots \times p_k^{a_k}$$

The total count of divisors is:
$$\text{Count} = (a_1 + 1)(a_2 + 1)\dots(a_k + 1)$$

**Example: Count divisors of 12**

1.  Prime Factorization: $12 = 2^2 \times 3^1$
2.  Exponents are $a_1=2$ and $a_2=1$.
3.  Formula: $(2 + 1)(1 + 1) = 3 \times 2 = 6$.
4.  **Verification:** The divisors of 12 are 1, 2, 3, 4, 6, 12. There are exactly 6.

**Java Code:**

```java
public class CountDivisors {
    public static int countDivisors(int n) {
        int totalDivisors = 1;
        
        // Loop from 2 to sqrt(n)
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                int count = 0;
                // Count the power of the current prime factor
                while (n % i == 0) {
                    count++;
                    n /= i;
                }
                // Apply formula: total = (p1 + 1) * (p2 + 1) ...
                totalDivisors *= (count + 1);
            }
        }
        
        // If n > 1, the remainder is a prime number with power 1
        // So we multiply by (1 + 1) = 2
        if (n > 1) {
            totalDivisors *= 2;
        }

        return totalDivisors;
    }

    public static void main(String[] args) {
        int num = 12;
        System.out.println("Total divisors of " + num + ": " + countDivisors(num));
        // Output for 12: 6 (Divisors are 1, 2, 3, 4, 6, 12)
    }
}
```

-----

### 4\. Modular Inverse


### Part 1: Modular Inverse using Fermat's Little Theorem

**Best for:** When the modulus $m$ is a **Prime Number** (e.g., $10^9 + 7$).

**Concept:**
According to Fermat's Little Theorem, if $m$ is a prime number, then for any integer $a$:
$$a^{m-2} \equiv a^{-1} \pmod m$$
This means finding the inverse is as simple as calculating $a$ raised to the power of $m-2$.

**Steps:**

1.  Verify $m$ is prime.
2.  Calculate `power(a, m - 2) % m`.

**Java Code (Fermat's Method):**

```java
public class FermatInverse {
    
    // Function to find modular inverse of a under modulo m
    public static int modInverse(int a, int m) {
        return modPow(a, m - 2, m);
    }

    // Standard Binary Exponentiation function (O(log m))
    public static int modPow(int base, int exp, int mod) {
        int res = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod; // If bit is set
            base = (base * base) % mod;                   // Square the base
            exp >>= 1;                                    // Shift right
        }
        return res;
    }

    public static void main(String[] args) {
        int a = 3, m = 7;
        // 3^(7-2) = 3^5 = 243. 243 % 7 = 5.
        System.out.println("Inverse of " + a + " mod " + m + " is: " + modInverse(a, m));
    }
}
```


### Part 2: Modular Inverse using Extended Euclidean Algorithm

**Best for:** When the modulus $m$ is **NOT Prime** (general case), but $a$ and $m$ are coprime ($GCD(a, m) = 1$).

**Concept:**
This method finds integers $x$ and $y$ such that:
$$ax + my = GCD(a, m)$$
Since we want the inverse, we assume $GCD(a, m) = 1$. The equation becomes:
$$ax + my = 1$$
Taking modulo $m$ on both sides removes $my$, leaving $ax \equiv 1 \pmod m$. Thus, $x$ is the modular inverse.


**Java Code (Extended Euclidean):**

```java
public class EuclideanInverse {

    public static int modInverse(int a, int m) {
        int m0 = m; // Keep original m for final step
        int y = 0, x = 1;

        if (m == 1) return 0; // Edge case

        while (a > 1) {
            // standard Euclidean Algorithm steps
            int q = a / m; // Quotient
            int t = m;

            // Update a and m
            m = a % m;
            a = t;
            t = y;

            // Update x and y
            y = x - q * y;
            x = t;
        }

        // If x is negative, make it positive
        if (x < 0) x += m0;

        return x;
    }

    public static void main(String[] args) {
        int a = 3, m = 11;
        // Works even though m=11 is prime, but intended for general cases
        System.out.println("Inverse of " + a + " mod " + m + " is: " + modInverse(a, m));
    }
}
```

-----

### 5\. Modular Exponentiation

**Concept:**
Computing huge powers like $x^n \pmod m$ efficiently. Instead of calculating $x^n$ (which overflows), we use the "Square and Multiply" method (Binary Exponentiation). This reduces complexity from $O(n)$ to $O(\log n)$.

**Example:** $3^5 \pmod 7$

  * Binary of exponent 5 is `101`.
  * Start result = 1.
  * Bit 1: Multiply result by base. Square base.
  * Bit 0: Only square base.
  * Bit 1: Multiply result by base. Square base.

**Java Code:**

```java
public class ModExponentiation {
    public static long power(long base, long exp, long mod) {
        long res = 1;
        base = base % mod; 
        
        while (exp > 0) {
            // If exp is odd, multiply base with result
            if ((exp % 2) == 1) 
                res = (res * base) % mod;
            
            // exp must be even now
            exp = exp >> 1; // divide by 2
            base = (base * base) % mod;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("3^5 mod 7 = " + power(3, 5, 7));
    }
}
```

----

### Modular Arithmetic Formula

- **Modular Addition**: `(a + b) % m = ((a % m) + (b % m)) % m`
- **Modular Subtraction**: `(a - b) % m = ((a % m) - (b % m) + m) % m`
- **Modular Multiplication**: `(a * b) % m = ((a % m) * (b % m)) % m`
- **Modular Division**: `(a / b) % m = ((a % m) * (b^(-1) % m)) % m`
- **Modular Exponentiation**: `(a ^ b) % m = ((a % m) ^ b) % m`