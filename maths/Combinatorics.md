### **Notes**

- `Mutually Exclusive` refers to events or outcomes in probability theory that cannot occur at the same time. In other words, if two events are mutually exclusive, the occurrence of one event prevents the occurrence of the other.

   - `P(A ∩ B) = 0`

### **1. Basic Counting Principles**

- **Addition Principle**: If events are mutually exclusive, the total number of outcomes is the sum of the individual outcomes.  

    - `Total outcomes = m + n`

- **Multiplication Principle**: If events are independent, the total number of outcomes is the product of the individual outcomes.  

    - `Total outcomes = m * n`

- **Inclusion-Exclusion Principle**: If events overlap, the total number of outcomes is the sum of the individual outcomes, minus the overlap (to avoid double-counting).  
  
    - `Total outcomes = |A| + |B| - |A ∩ B|`

### **2. Permutations and Combinations**

- **Permutations**: The arrangement of objects where **order matters**.  
- **Combinations**: The selection of objects where **order doesn’t matter**.  

#### **1. Permutations (Order Matters)**

When you arrange items, and the order of the arrangement matters, you're dealing with permutations.

**Formula for Permutations**:

P(n, r) = <span style="font-size: larger;">n!</span> / <span style="font-size: larger;">(n - r)!</span>

- **n**: Total number of items.
- **r**: Number of items being arranged.
- **n!**: The factorial of \(n\).

**Examples**:

1. **Simple Permutation**:  
   Suppose you have 3 letters: \(A, B, C\). How many ways can you arrange 2 letters?  

   Here \(n = 3\), \(r = 2\):  
 
   P(3, 2) = <span style="font-size: larger;">3!</span> / <span style="font-size: larger;">(3-2)!</span> = <span style="font-size: larger;">3 × 2 × 1</span> / <span style="font-size: larger;">1</span> = 6

   The arrangements are:  
   \(AB, AC, BA, BC, CA, CB\).

2. **Permutations with all items \(r = n\)**:  
   How many ways can you arrange \(A, B, C\)?  
   \(n = 3\), \(r = 3\):  

   P(3, 3) = <span style="font-size: larger;">3!</span> / <span style="font-size: larger;">(3-3)!</span> = <span style="font-size: larger;">3!</span> = 6
   
   Arrangements: \(ABC, ACB, BAC, BCA, CAB, CBA\).


#### **2. Combinations (Order Doesn't Matter)**

When you're selecting items and the order doesn’t matter, you're dealing with combinations.

**Formula for Combinations**:

C(n, r) = <span style="font-size: larger;">n!</span> / (<span style="font-size: larger;">r! × (n - r)!</span>)

- **n**: Total number of items.
- **r**: Number of items being chosen.

**Examples**:

1. **Simple Combination**:  
   Suppose you have 3 letters \(A, B, C\), and you want to select 2 letters. How many ways can you select them? 

   \(n = 3\), \(r = 2\):  
  
   C(3, 2) = <span style="font-size: larger;">3!</span> / (<span style="font-size: larger;">2! × (3-2)!</span>) = <span style="font-size: larger;">3 × 2 × 1</span> / (<span style="font-size: larger;">2 × 1 × 1</span>) = 3
   
   The combinations are:  
   \(\{A, B\}, \{A, C\}, \{B, C\}\).

2. **Combinations with all items (\(r = n\))**:  
   How many ways can you select all 3 letters?  

   \(n = 3\), \(r = 3\):  

   C(3, 3) = <span style="font-size: larger;">3!</span> / (<span style="font-size: larger;">3! × (3-3)!</span>) = <span style="font-size: larger;">6</span> / (<span style="font-size: larger;">6 × 1</span>) = 1
   
   Only one way: \(\{A, B, C\}\).

#### **3. Permutations with Repetition**:

The number of ways to arrange \(r\) items from \(n\) items when repetition is allowed:  

- P_rep(n, r) = <span style="font-size: larger;">n<sup>r</sup></span>


**Examples**:

How many ways can you arrange 3 digits where repetition is allowed, and the digits are \(1, 2, 3\)?  

- P_rep(3, 3) = <span style="font-size: larger;">3<sup>3</sup></span> = 27

- Arrangements: \(111, 112, 113, 121, 122, 123, \....\).


#### **4. Combinations with Repetition**:
The number of ways to choose \(r\) items from \(n\) items when repetition is allowed:  


- C_rep(n, r) = <span style="font-size: larger;">(n + r - 1)!</span> / (<span style="font-size: larger;">r! × (n - 1)!</span>)


#### Example:

How many ways can you choose 3 items from 2 types of fruit (\(A, B\)), allowing repetition?  


- C_rep(2, 3) = <span style="font-size: larger;">(2 + 3 - 1)!</span> / (<span style="font-size: larger;">3! × (2 - 1)!</span>) = <span style="font-size: larger;">4!</span> / (<span style="font-size: larger;">3! × 1!</span>) = <span style="font-size: larger;">24</span> / <span style="font-size: larger;">6</span> = 4

- Selections: \(\{AAA, AAB, ABB, BBB\}\).


### Catlan Numbers:

`Cₙ = (2n)! / ((n+1)! n!)`

`C(n) = Ci(2n,n)/n+1`

<p>Where:</p>
<ul>
  <li><code>(2n)! / (n! &bull; n!)</code> is the binomial coefficient <code>&#8658; binomial(2n, n)</code></li>
  <li><code>n!</code> is the factorial of <code>n</code></li>
</ul>




#### Examples:

- `C₀ = 1`
- `C₁ = 1`
- `C₂ = 2`
- `C₃ = 5`
- `C₄ = 14`
- `C₅ = 42`

