## Divisibility and Modular Arithmetic

- If a and b are integers with a != 0, we say that a divides b if there is an integer c such that  
  `b = ac`. When a divides b, we say that `a is a factor or divisor of b`, and that `b is a multiple of a`.

### Divisibility Property

- If `a | b` (a divides b) and `a | c` (a divides c), then `a | (mb + nc)` for any integers `m` and `n`.
- This means that if `a` is a common divisor of `b` and `c`, it also divides any **linear combination** of `b` and `c`.

#### Example:

Let `a = 3`, `b = 12`, and `c = 15`:

- Since `3 | 12` (because `12 = 3 × 4`)
- And `3 | 15` (because `15 = 3 × 5`)
- Then for any integers `m, n`, `3 | (m × 12 + n × 15)`.

If `m = 2`, `n = 3`:  
\[ 2 × 12 + 3 × 15 = 24 + 45 = 69 \]
Since `69 = 3 × 23`, we conclude `3 | 69`, proving the rule.

---

### The Division Algorithm

- When an integer `a` is divided by a **positive** integer `d`, there exist unique integers `q` (quotient) and `r` (remainder) such that:  
  \[ a = dq + r \]
  where `0 ≤ r < d`.

#### Example:

For `a = 23` and `d = 5`:

- `23 ÷ 5 = 4` remainder `3`, so:  
  \[ 23 = 5 × 4 + 3 \]
- **Quotient** (`q`) = `4`
- **Remainder** (`r`) = `3`


### What Does a ≡ b (mod m) Mean?

- When we say **"a is congruent to b modulo m"**, it means that the difference (a - b) is **divisible by m**.  
- Mathematically, this means:  
  - m | (a - b)  
- Another way to understand this:  
  - a and b leave the **same remainder** when divided by m.  
- We write this as:  
  - a ≡ b (mod m)  
  where **m** is called the **modulus**


### Primes

- An integer p greater than 1 is called prime if the only positive factors of p are 1 and p.
A positive integer that is greater than 1 and is not prime is called composite.

- **Fundamental Theorem of Arithmetic**: Every integer greater than 1 can be written uniquely as a prime or as the product of two or more primes where the prime factors are written in nondecreasing order.

   - Prime factorization of 100 is 2 × 2 × 5 × 5.
   - 641 is already a prime number, so its prime factorization is just 641 itself
   - The prime factorization of 999 is 3 × 3 × 3 × 37

- **Trial Division**

  - To determine whether a number n is prime, we can divide n by all integers from 2 to √n. If n is not divisible by any of these integers, then n is prime.

- **The Sieve of Eratosthenes**

  - To find sieve of Eratosthenes, we start with a list of integers from 2 to n. We then remove all multiples of 2, then all multiples of 3, and so on. The remaining numbers are prime.

- **Conjecture about primes**

  - Golbach's Conjecture
  - Twin Prime Conjecture

### Primality testing

- Primality testing is the process of determining whether a given number is a prime number or a composite number. A prime number is a number greater than 1 that has no positive divisors other than 1 and itself, whereas a composite number has divisors other than 1 and itself


### Solving Linear Congruences

- `Linear Congruence` is an equation of the form `ax ≡ b (mod m)`, where `a`, `b`, and `m` are integers, and `m` is positive. The goal is to find all integers `x` that satisfy the equation.


### Sieve of Eratosthenes

The **Sieve of Eratosthenes** is an efficient algorithm for finding all prime numbers up to a specified integer. It works by iteratively marking the multiples of each prime number, starting with 2, as composite (not prime).

* **How it works:**
    1.  Create a list of consecutive integers from 2 to a number $n$.
    2.  Initially, let $p = 2$, the first prime number.
    3.  Iterate through the list and mark all multiples of $p$ (e.g., $2p, 3p, 4p, \dots$) as composite. Do not mark $p$ itself.
    4.  Find the next number in the list that is not marked. Let this new prime be $p$.
    5.  Repeat the process until $p^2 > n$. All remaining unmarked numbers in the list are prime.

* **Example:** Finding all primes up to 10.
    1.  List: `2, 3, 4, 5, 6, 7, 8, 9, 10`.
    2.  Start with $p = 2$. Mark its multiples: `4, 6, 8, 10`.
    3.  Next unmarked number is $p = 3$. Mark its multiples: `6, 9`.
    4.  The next unmarked number is $p = 5$. Since $5^2 > 10$, we stop.
    5.  The remaining unmarked numbers are `2, 3, 5, 7`.



### Modular Exponentiation

**Modular exponentiation** is an algorithm for efficiently computing the remainder of a large exponentiation. It's used in public-key cryptography to compute $(b^e) \pmod{m}$ where $e$ is a large number.

* **How it works:** The algorithm uses the property that $(a \cdot b) \pmod{m} \equiv ((a \pmod{m}) \cdot (b \pmod{m})) \pmod{m}$. It works by converting the exponent $e$ into its binary representation and repeatedly squaring the base, taking the modulus at each step.

* **Example:** Calculate $(3^{13}) \pmod{7}$.
    * Binary of 13 is $1101$. This means $13 = 8 + 4 + 1$.
    * $3^1 \pmod{7} = 3$
    * $3^2 \pmod{7} = 9 \pmod{7} = 2$
    * $3^4 \pmod{7} = (3^2)^2 \pmod{7} = 2^2 \pmod{7} = 4$
    * $3^8 \pmod{7} = (3^4)^2 \pmod{7} = 4^2 \pmod{7} = 16 \pmod{7} = 2$
    * Now, multiply the results for the bits that are `1` in the binary representation of 13:
        $(3^{13}) \pmod{7} \equiv (3^8 \cdot 3^4 \cdot 3^1) \pmod{7} \equiv (2 \cdot 4 \cdot 3) \pmod{7} \equiv (24) \pmod{7} \equiv 3$.


### The Extended Euclidean Algorithm

The **Extended Euclidean Algorithm** is an extension of the standard Euclidean algorithm. It not only finds the greatest common divisor (GCD) of two integers, $a$ and $b$, but also finds integers $x$ and $y$ such that $ax + by = \text{gcd}(a, b)$. This is known as Bézout's identity.

* **How it works:** It uses the same steps as the standard Euclidean algorithm, but it keeps track of the coefficients $x$ and $y$ at each recursive step. The identity is built up by substitution as the algorithm works backward from the base case.

* **Example:** Find the GCD of 24 and 9, and the coefficients $x$ and $y$.
    1.  $24 = 2 \cdot 9 + 6$
    2.  $9 = 1 \cdot 6 + 3$
    3.  $6 = 2 \cdot 3 + 0$
    4.  The GCD is 3. Now, work backward to find $x$ and $y$.
    * From step 2: $3 = 9 - 1 \cdot 6$
    * Substitute 6 from step 1: $3 = 9 - 1 \cdot (24 - 2 \cdot 9)$
    * Simplify: $3 = 9 - 1 \cdot 24 + 2 \cdot 9 = 3 \cdot 9 - 1 \cdot 24$.
    * So, $x = -1$ and $y = 3$. The equation is $-1 \cdot 24 + 3 \cdot 9 = 3$.


### Modular Inverse Explained

A **modular inverse** of an integer $a$ modulo $m$ is an integer $x$ such that $(ax) \pmod{m} = 1$. It's a key concept in modular arithmetic and is analogous to a reciprocal in real number arithmetic. A modular inverse exists if and only if $a$ and $m$ are coprime (i.e., $\text{gcd}(a, m) = 1$).

* **How it works:** The modular inverse can be found using the **Extended Euclidean Algorithm**. Since we know that $\text{gcd}(a, m) = ax + my = 1$, the value of $x$ is the modular inverse of $a$ modulo $m$.
* **Example:** Find the modular inverse of 3 modulo 7.
    * Using the Extended Euclidean Algorithm for $\text{gcd}(3, 7)$:
        1.  $7 = 2 \cdot 3 + 1$
        2.  $1 = 7 - 2 \cdot 3$
    * The remainder is 1, so the GCD is 1. We found that $1 = (-2) \cdot 3 + 1 \cdot 7$.
    * The equation $ax + my = 1$ is satisfied by $x = -2$ and $y = 1$.
    * Since we need a positive inverse, we can add $m$ to $x$: $(-2 + 7) \pmod{7} = 5$.
    * The modular inverse is 5. We can check this: $(3 \cdot 5) \pmod{7} = 15 \pmod{7} = 1$.


### Fermat’s Little Theorem

**Fermat's Little Theorem** is a fundamental theorem in number theory. It states that if $p$ is a prime number, then for any integer $a$ not divisible by $p$, the number $a^{p-1} - 1$ is an integer multiple of $p$. This can be written as $a^{p-1} \equiv 1 \pmod{p}$.

* **How it works:** This theorem is often used to efficiently find modular inverses. Since $a^{p-1} \equiv 1 \pmod{p}$, we can see that $a \cdot a^{p-2} \equiv 1 \pmod{p}$. This means the modular inverse of $a$ is $a^{p-2} \pmod{p}$.
* **Example:** Find the modular inverse of 3 modulo 7 using Fermat's Little Theorem.
    * Here, $a = 3$ and $p = 7$. $p$ is prime and 3 is not a multiple of 7.
    * The inverse is $3^{7-2} \pmod{7} = 3^5 \pmod{7}$.
    * $3^1 = 3$
    * $3^2 = 9 \equiv 2 \pmod{7}$
    * $3^4 = 2^2 = 4 \pmod{7}$
    * $3^5 = 3^4 \cdot 3^1 \equiv (4 \cdot 3) \pmod{7} = 12 \pmod{7} = 5$.
    * The modular inverse is 5.


### GCD and LCM

* **Greatest Common Divisor (GCD):** The **GCD** of two or more integers is the largest positive integer that divides each of the integers without a remainder. It can be found using the **Euclidean algorithm**.
* **Least Common Multiple (LCM):** The **LCM** of two or more integers is the smallest positive integer that is a multiple of all the numbers.
* **Relationship:** There is a direct relationship between the GCD and LCM of two numbers, $a$ and $b$: $a \cdot b = \text{gcd}(a, b) \cdot \text{lcm}(a, b)$. This allows you to find one if you know the other.

* **Example:** Find the GCD and LCM of 12 and 18.
    * **GCD:** Using the Euclidean algorithm:
        1.  $18 = 1 \cdot 12 + 6$
        2.  $12 = 2 \cdot 6 + 0$
        3.  The last non-zero remainder is 6, so $\text{gcd}(12, 18) = 6$.
    * **LCM:** Using the relationship formula:
        $\text{lcm}(12, 18) = \frac{12 \cdot 18}{\text{gcd}(12, 18)} = \frac{216}{6} = 36$.


### Prime Factorization

**Prime factorization** is the process of finding the prime numbers that multiply together to make an integer. Every integer greater than 1 is either a prime number itself or can be represented as a product of prime numbers.

* **How it works:** You can find the prime factors by repeatedly dividing the number by the smallest prime numbers (2, 3, 5, etc.) until the result is a prime number itself.
* **Example:** Find the prime factorization of 120.
    1.  Start with the smallest prime, 2. $120 \div 2 = 60$.
    2.  $60 \div 2 = 30$.
    3.  $30 \div 2 = 15$.
    4.  15 is not divisible by 2. Try the next prime, 3. $15 \div 3 = 5$.
    5.  5 is a prime number, so we stop.
    * The prime factorization of 120 is $2 \cdot 2 \cdot 2 \cdot 3 \cdot 5$, or $2^3 \cdot 3^1 \cdot 5^1$.
    
  * **Counting Divisors:** The number of divisors of a number $N$ can be found from its prime factorization. If $N = p_1^{e_1} \cdot p_2^{e_2} \cdot \dots \cdot p_k^{e_k}$, the number of divisors is $(e_1 + 1)(e_2 + 1)\dots(e_k + 1)$.
      * **Example:** How many divisors does 120 have? We know $120 = 2^3 \cdot 3^1 \cdot 5^1$.
          The number of divisors is $(3+1)(1+1)(1+1) = 4 \cdot 2 \cdot 2 = 16$.

  * **Sum of Divisors:** The sum of divisors can also be calculated from the prime factorization.
      * **Example:** The sum of divisors of 120 is $(1 + 2^1 + 2^2 + 2^3)(1+3^1)(1+5^1) = (15)(4)(6) = 360$.

### Chinese Remainder Theorem

The **Chinese Remainder Theorem (CRT)** is a way to solve a system of linear congruences. It's used when you need to find an integer that has a specific remainder when divided by several different numbers.

* **How it works:** Given a system of congruences like:
    $x \equiv a_1 \pmod{n_1}$
    $x \equiv a_2 \pmod{n_2}$
    ...
    $x \equiv a_k \pmod{n_k}$
    The theorem guarantees a unique solution for $x$ modulo the product of the $n_i$'s, provided that the $n_i$'s are pairwise coprime (their greatest common divisor is 1).

* **Example:** A classic riddle is: "What number leaves a remainder of 1 when divided by 3, a remainder of 2 when divided by 5, and a remainder of 3 when divided by 7?"
    Using the CRT, you can solve this system:
    $x \equiv 1 \pmod{3}$
    $x \equiv 2 \pmod{5}$
    $x \equiv 3 \pmod{7}$
    The smallest solution is $x = 52$.

### Euler's Totient Function (phi function) and Euler's Theorem

**Euler's Totient Function**, denoted as $\phi(n)$, counts the number of positive integers up to a given integer $n$ that are relatively prime to $n$ (meaning their GCD is 1).

* **How it's used:** It's the basis for **Euler's Theorem**, which is a generalization of Fermat's Little Theorem. Euler's Theorem states that if $a$ and $n$ are relatively prime integers, then:
    $a^{\phi(n)} \equiv 1 \pmod{n}$
    This is useful for finding modular inverses when the modulus is not prime.

* **Example:** Find the last digit of $3^{2023}$ (which is finding $3^{2023} \pmod{10}$).
    1.  We need to find $\phi(10)$. The numbers relatively prime to 10 are 1, 3, 7, 9. So, $\phi(10) = 4$.
    2.  By Euler's Theorem, we know $3^{\phi(10)} \equiv 3^4 \equiv 1 \pmod{10}$.
    3.  We can rewrite the exponent: $2023 = 4 \cdot 505 + 3$.
    4.  $3^{2023} = 3^{4 \cdot 505 + 3} = (3^4)^{505} \cdot 3^3 \pmod{10}$.
    5.  This simplifies to $(1)^{505} \cdot 3^3 \equiv 1 \cdot 27 \equiv 7 \pmod{10}$.
    The last digit is 7.


### Modular Arithmetic Formula

- **Modular Addition**: `(a + b) % m = ((a % m) + (b % m)) % m`
- **Modular Subtraction**: `(a - b) % m = ((a % m) - (b % m) + m) % m`
- **Modular Multiplication**: `(a * b) % m = ((a % m) * (b % m)) % m`
- **Modular Division**: `(a / b) % m = ((a % m) * (b^(-1) % m)) % m`
- **Modular Exponentiation**: `(a ^ b) % m = ((a % m) ^ b) % m`
- **Modular Inverse**: `a^(-1) % m = a^(m-2) % m` (Fermat's Little Theorem)


 
