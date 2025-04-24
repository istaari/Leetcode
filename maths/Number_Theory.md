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


### Modular Exponentiation

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

- Conjecture about primes

  - Golbach's Conjecture
  - Twin Prime Conjecture

### GCD

- `Relatively Prime`  Two numbers are said to be `relatively prime (or coprime) if their gcd is 1.` This means they have no common divisors other than 1

- `GCD Using Prime Factorization`

- `Euclidean Algorithm`

### LCM

- The `LCM` is used to determine when two events, occurring at different intervals, will happen at the same time. For instance, if one event occurs every 4 days and another every 6 days, the LCM (12) will tell us that both events will occur together every 12 days.

- it's the smallest positive integer that is divisible by both given numbers

- `LCM Using Prime Factorization`


### Primality testing

- Primality testing is the process of determining whether a given number is a prime number or a composite number. A prime number is a number greater than 1 that has no positive divisors other than 1 and itself, whereas a composite number has divisors other than 1 and itself


### 9. Bézout's Theorem


### 10. Fermat’s Little Theorem


### 11. Modular Inverse


### 12. Solving Linear Congruences

- `Linear Congruence` is an equation of the form `ax ≡ b (mod m)`, where `a`, `b`, and `m` are integers, and `m` is positive. The goal is to find all integers `x` that satisfy the equation.

- **Chinese Remainder Theorem**


### 13. Modular Arithmetic

- **Modular Addition**: `(a + b) % m = ((a % m) + (b % m)) % m`
- **Modular Subtraction**: `(a - b) % m = ((a % m) - (b % m) + m) % m`
- **Modular Multiplication**: `(a * b) % m = ((a % m) * (b % m)) % m`
- **Modular Division**: `(a / b) % m = ((a % m) * (b^(-1) % m)) % m`
- **Modular Exponentiation**: `(a ^ b) % m = ((a % m) ^ b) % m`
- **Modular Inverse**: `a^(-1) % m = a^(m-2) % m` (Fermat's Little Theorem)
- **Divisibility Check** : `if a mod m = 0 then a is divisible by m`


 
