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


| Word      | Meaning                                                                              | Example Sentence                                                 |
|-----------|--------------------------------------------------------------------------------------|------------------------------------------------------------------|
| Resilient | Able to recover quickly from difficulties                                            | "Despite the challenges, she remained resilient and kept going." |
| Symmetry  | A property of a mathematical object that remains unchanged under some transformation | "The butterfly’s wings showed perfect symmetry."                 |


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
