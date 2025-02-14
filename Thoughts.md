# Data Structure and Algorithms

## Array

## String

## Linked List

## Stack

- Use `two stack` when you need to keep track of two things, like characters with cumulative counts

## Recursion

- `Recursive Calls Represent Unfinished Work`, When a recursive function calls itself, it doesn’t immediately solve the
  current problem but instead defers it to be solved later by breaking it down into smaller sub-problems

- Think of the `bases cases and smallest input`

- Use `Nested Recursion`, Solution to one recursive call depends on the solution of another recursive call, or nested
  structure

## Tree

## Graph

## Backtracking

## Greedy

- Makes the best immediate choice at each step

## Dynamic Programming

- Always find what is sub-problem and what is state
- Think solution to smallest sub-problems, `smallest problem is almost always a problem of size 0 or 1 or empty string`
- Combine Smallest sub-problems to solve bigger problems
- Think of smallest input like size is empty or 1, or string is empty
- Some DP problems are about maximizing/minimizing a value subject to some constraints
- In Recursive dp think from the last

## Math

- Normalizing modulo to be always positive `mod = (mod + k) % k`

## Bit Manipulation

- A `bitmask` is typically an integer where each bit (from right to left) represents a state or an element

- `Bitwise manipulations` refer to operations that directly manipulate the individual bits of an integer using logical
  operators like AND, OR, XOR, NOT, and bit shifts.

## Technical words

- `Symmetry` - A property of a mathematical object that remains unchanged under some transformation


---

## How to approach a problem

- Always read constraints
- Break Down the Problem
- Think in Terms of Patterns
- Explore Brute Force First
- Think from different angle, generalize or simplify the problem

### **Rule of Thumb for Common Constraints**

| **Constraint**            | **Recommended Time Complexity**            | **Examples**                           |
|---------------------------|--------------------------------------------|----------------------------------------|
| <code>n &leq; 10</code>   | <code>O(n!)</code>, <code>O(2^n)</code>    | Backtracking, combinatorics            |
| <code>n &leq; 100</code>  | <code>O(n^2)</code>, <code>O(n^3)</code>   | Dynamic programming, matrix algorithms |
| <code>n &leq; 10^4</code> | <code>O(n log n)</code>, <code>O(n)</code> | Sorting, linear scans                  |
| <code>n &leq; 10^6</code> | <code>O(n)</code>, <code>O(n log n)</code> | Sliding window, prefix sums            |
| <code>n &leq; 10^9</code> | <code>O(log n)</code>, <code>O(1)</code>   | Binary search, modular arithmetic      |

---

## How to Solve Hard Problems

### **1. Change the Perspective**
- **Reverse the Problem:** Instead of proving something is true, try proving why it cannot be false.
- **Look at Special Cases:** Solve a simpler version of the problem first to gain insights.
- **Change Representation:** Express the problem in a different form—graphs, equations, diagrams, or words.
- **Use Analogies:** Compare the problem to something similar in another field (e.g., using physics intuition in mathematics).

### **2. Break It Down and Rebuild**
- **Divide and Conquer:** Break the problem into smaller, manageable pieces.
- **Generalize or Specialize:** If the problem is too broad, try solving a more specific case first. If it's too specific, generalize it and see if existing theorems apply.

### **3. Look for Patterns and Connections**
- **Seek Symmetry:** Many problems have hidden symmetries that simplify them.
- **Find Recurring Patterns:** If something repeats, it might suggest a deeper structure.
- **Use Different Mathematical Tools:** Apply tools from algebra, geometry, or calculus even if the problem seems unrelated.

### **4. Shift the Mindset**
- **Question Your Assumptions:** Sometimes we make implicit assumptions that limit our thinking.
- **Take a Break:** Let the subconscious work on it—many breakthroughs happen after stepping away.
- **Seek Alternative Views:** Explain the problem to someone else or see how different experts approach it.

### **5. Experiment and Play Around**
- **Try Extreme Values:** What happens at the limits? (e.g., when variables go to 0 or infinity)
- **Use Visualization:** Draw diagrams or simulate the problem with a physical model.
- **Apply Trial and Error:** Sometimes testing different cases leads to unexpected insights.

#### **Examples of This in Action:**
- **Einstein's General Relativity:** He stopped thinking about gravity as a force and instead saw it as the curvature of spacetime.
- **Andrew Wiles and Fermat’s Last Theorem:** He connected number theory with modular forms, which weren’t initially thought to be related.
- **Richard Feynman’s Problem-Solving:** He often used diagrams (Feynman diagrams) and analogies to reframe complex quantum physics problems.

