Bitmasking is a powerful technique in Dynamic Programming (DP) used to represent the **state of a set** using a single integer.

### The Core Concept: Mapping Sets to Integers

Imagine you have a set of $N$ items (labeled $0$ to $N-1$). We can represent any subset of these items using an integer where the **$i$-th bit** corresponds to the **$i$-th item**.

  * **Bit is 1:** The item is in the subset (Used/Visited/On).
  * **Bit is 0:** The item is not in the subset (Available/Unvisited/Off).

#### Example

Suppose we have 3 tasks: $\{A, B, C\}$.

  * Task $A$ is index $0$.
  * Task $B$ is index $1$.
  * Task $C$ is index $2$.

If we have completed Task A and Task C, but not B, our set is $\{A, C\}$.
In binary:

  * Index 2 (C): **1**
  * Index 1 (B): **0**
  * Index 0 (A): **1**

The binary number is $101_2$, which is the decimal integer **5**.
In our DP table, `dp[5]` would store the result for the state where A and C are completed.

-----

### Essential Bitwise Operations

To manipulate these sets, you use bitwise operators. Let's say `mask` is your current integer representing the set, and `i` is the index of the element you are interested in.

| Operation | Code | Explanation |
| :--- | :--- | :--- |
| **Add** element $i$ | `mask | (1 << i)` | Sets the $i$-th bit to 1. |
| **Remove** element $i$ | `mask & ~(1 << i)` | Sets the $i$-th bit to 0. |
| **Check** if $i$ exists | `(mask >> i) & 1` | Returns 1 if present, 0 if not. |
| **Toggle** element $i$ | `mask ^ (1 << i)` | Flips 0 to 1, or 1 to 0. |
| **Set of all $N$ items** | `(1 << N) - 1` | Creates a mask of $N$ ones (e.g., $111_2$). |

# Variations

## General Subset & Set Cover

**Concept:** You have a target state `(usually "all bits set to 1")` and a collection of options (numbers, strings, skills) that can turn specific bits on. You want to reach the target state with minimum cost or steps. 

**State:** `dp[mask]` = min cost/steps to cover the elements represented by `mask`.

### [Matchsticks to Square](https://leetcode.com/problems/matchsticks-to-square/description/)

You are given an integer array <code>matchsticks</code> where <code>matchsticks[i]</code> is the length of the <code>i^th</code> matchstick. You want to use **all the matchsticks**  to make one square. You **should not break**  any stick, but you can link them up, and each matchstick must be used **exactly one time** .

Return <code>true</code> if you can make this square and <code>false</code> otherwise.

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2021/04/09/matchsticks1-grid.jpg" style="width: 253px; height: 253px;">

```
Input: matchsticks = [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
```

**Example 2:** 

```
Input: matchsticks = [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.
```

**Constraints:** 

- <code>1 <= matchsticks.length <= 15</code>
- <code>1 <= matchsticks[i] <= 10^8</code>

**Solution**

```java

    /**
     * STATE: dp[mask] For a subset of sticks (mask), dp[mask] stores how many units are
     * currently contributing to the "incomplete" side.
     */
    public boolean makeSquare(int[] matchsticks) {
        int n = matchsticks.length;
        if (n < 4)
            return false;

        long totalSum = 0;
        for (int s : matchsticks)
            totalSum += s;

        // If the total length isn't divisible by 4, we can't form a square
        if (totalSum % 4 != 0)
            return false;

        int target = (int) (totalSum / 4);
        int numStates = 1 << n;
        int[] dp = new int[numStates];

        // BASE CASE:
        // Start with an empty set of sticks.
        // Initialize all other states as unreachable (-1).
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // TRANSITION:
        // For every reachable state (mask), try adding an unused matchstick (j).
        for (int mask = 0; mask < numStates; mask++) {
            if (dp[mask] == -1)
                continue;

            for (int j = 0; j < n; j++) {
                // Check if the j-th matchstick is already used in the current mask
                if ((mask & (1 << j)) == 0) {
                    // Include the current matchstick in the mask
                    int nextMask = mask | (1 << j);

                    // If adding this stick doesn't exceed the target side length...
                    if (dp[mask] + matchsticks[j] <= target) {
                        // Modulo resets side length to 0 once 'target' is reached, starting the next side.
                        // Goal state: all bits set (11...1) indicates all matchsticks have been used.
                        // Goal state: (1 << n) - 1 indicates every stick has been placed.
                        dp[nextMask] = (dp[mask] + matchsticks[j]) % target;
                    }
                }
            }
        }

        return dp[numStates - 1] == 0;
    }

```



### [Smallest Sufficient Team](https://leetcode.com/problems/smallest-sufficient-team/description/)

In a project, you have a list of required skills <code>req_skills</code>, and a list of people. The <code>i^th</code> person <code>people[i]</code> contains a list of skills that the person has.

Consider a sufficient team: a set of people such that for every required skill in <code>req_skills</code>, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.

- For example, <code>team = [0, 1, 3]</code> represents the people with skills <code>people[0]</code>, <code>people[1]</code>, and <code>people[3]</code>.

Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in **any order** .

It is **guaranteed**  an answer exists.

**Example 1:** 

```
Input: req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
Output: [0,2]
```

**Example 2:** 

```
Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
Output: [1,2]
```

**Constraints:** 

- <code>1 <= req_skills.length <= 16</code>
- <code>1 <= req_skills[i].length <= 16</code>
- <code>req_skills[i]</code> consists of lowercase English letters.
- All the strings of <code>req_skills</code> are **unique** .
- <code>1 <= people.length <= 60</code>
- <code>0 <= people[i].length <= 16</code>
- <code>1 <= people[i][j].length <= 16</code>
- <code>people[i][j]</code> consists of lowercase English letters.
- All the strings of <code>people[i]</code> are **unique** .
- Every skill in <code>people[i]</code> is a skill in <code>req_skills</code>.
- It is guaranteed a sufficient team exists.



### [Stickers to Spell Word](https://leetcode.com/problems/stickers-to-spell-word/description/)

We are given <code>n</code> different types of <code>stickers</code>. Each sticker has a lowercase English word on it.

You would like to spell out the given string <code>target</code> by cutting individual letters from your collection of stickers and rearranging them. You can use each sticker more than once if you want, and you have infinite quantities of each sticker.

Return the minimum number of stickers that you need to spell out <code>target</code>. If the task is impossible, return <code>-1</code>.

**Note:**  In all test cases, all words were chosen randomly from the <code>1000</code> most common US English words, and <code>target</code> was chosen as a concatenation of two random words.

**Example 1:** 

```
Input: stickers = ["with","example","science"], target = "thehat"
Output: 3
Explanation:
We can use 2 "with" stickers, and 1 "example" sticker.
After cutting and rearrange the letters of those stickers, we can form the target "thehat".
Also, this is the minimum number of stickers necessary to form the target string.
```

**Example 2:** 

```
Input: stickers = ["notice","possible"], target = "basicbasic"
Output: -1
Explanation:
We cannot form the target "basicbasic" from cutting letters from the given stickers.
```

**Constraints:** 

- <code>n == stickers.length</code>
- <code>1 <= n <= 50</code>
- <code>1 <= stickers[i].length <= 10</code>
- <code>1 <= target.length <= 15</code>
- <code>stickers[i]</code> and <code>target</code> consist of lowercase English letters.



## Partitioning & Distribution (The "Bucket" Pattern)

**Concept:** You have items that must be distributed into K groups/buckets/workers.

**State:** Often `dp[mask]` = current remainder of the last bucket, or using a recursive function `solve(index, mask)` where the mask tracks used items.

**Optimization:** These often require iterating over **submasks** (3^N complexity) or pruning.


### [Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/description/)

Given an integer array <code>nums</code> and an integer <code>k</code>, return <code>true</code> if it is possible to divide this array into <code>k</code> non-empty subsets whose sums are all equal.

**Example 1:** 

```
Input: nums = [4,3,2,3,5,2,1], k = 4
Output: true
Explanation: It is possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
```

**Example 2:** 

```
Input: nums = [1,2,3,4], k = 3
Output: false
```

**Constraints:** 

- <code>1 <= k <= nums.length <= 16</code>
- <code>1 <= nums[i] <= 10^4</code>
- The frequency of each element is in the range <code>[1, 4]</code>.

### [Find Minimum Time to Finish All Jobs](https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/description/)

You are given an integer array <code>jobs</code>, where <code>jobs[i]</code> is the amount of time it takes to complete the <code>i^th</code> job.

There are <code>k</code> workers that you can assign jobs to. Each job should be assigned to **exactly**  one worker. The **working time**  of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the **maximum working time**  of any worker is **minimized** .

Return the **minimum**  possible **maximum working time**  of any assignment. 

**Example 1:** 

```
Input: jobs = [3,2,3], k = 3
Output: 3
Explanation: By assigning each person one job, the maximum time is 3.
```

**Example 2:** 

```
Input: jobs = [1,2,4,7,8], k = 2
Output: 11
Explanation: Assign the jobs the following way:
Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
Worker 2: 4, 7 (working time = 4 + 7 = 11)
The maximum working time is 11.
```

**Constraints:** 

- <code>1 <= k <= jobs.length <= 12</code>
- <code>1 <= jobs[i] <= 10^7</code>


## Profile DP (Grid / Broken Profile)

**Concept:** Placing items on a grid where the current cell depends only on the state of the previous row or the immediate neighbors.

**State:** `dp[row_index][mask]` = max value for `row_index` given that `mask` represents the configuration of the current/previous row.


### [Maximum Students Taking Exam](https://leetcode.com/problems/maximum-students-taking-exam/description/)

Given a <code>m* n</code>matrix <code>seats</code>that represent seats distributionsin a classroom.If a seatisbroken, it is denoted by <code>'#'</code> character otherwise it is denoted by a <code>'.'</code> character.

Students can see the answers of those sitting next to the left, right, upper left and upper right, but he cannot see the answers of the student sittingdirectly in front or behind him. Return the **maximum ** number of students that can take the exam togetherwithout any cheating being possible.

Students must be placed in seats in good condition.

**Example 1:** 
<img height="200" width="339" src="https://assets.leetcode.com/uploads/2020/01/29/image.png">

```
Input: seats = [["#",".","#","#",".","#"],
               [".","#","#","#","#","."],
               ["#",".","#","#",".","#"]]
Output: 4
Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam. 
```

**Example 2:** 

```
Input: seats = [[".","#"],
               ["#","#"],
               ["#","."],
               ["#","#"],
               [".","#"]]
Output: 3
Explanation: Place all students in available seats. 

```

**Example 3:** 

```
Input: seats = [["#",".","**.** ",".","#"],
               ["**.** ","#","**.** ","#","**.** "],
               ["**.** ",".","#",".","**.** "],
               ["**.** ","#","**.** ","#","**.** "],
               ["#",".","**.** ",".","#"]]
Output: 10
Explanation: Place students in available seats in column 1, 3 and 5.
```

**Constraints:** 

- <code>seats</code>contains only characters<code>'.'<font face="sans-serif, Arial, Verdana, Trebuchet MS">and</code><code>'#'.</code>
- <code>m ==seats.length</code>
- <code>n ==seats[i].length</code>
- <code>1 <= m <= 8</code>
- <code>1 <= n <= 8</code>


### [Maximize Grid Happiness](https://leetcode.com/problems/maximize-grid-happiness/description/)

You are given four integers, <code>m</code>, <code>n</code>, <code>introvertsCount</code>, and <code>extrovertsCount</code>. You have an <code>m x n</code> grid, and there are two types of people: introverts and extroverts. There are <code>introvertsCount</code> introverts and <code>extrovertsCount</code> extroverts.

You should decide how many people you want to live in the grid and assign each of them one grid cell. Note that you **do not**  have to have all the people living in the grid.

The **happiness**  of each person is calculated as follows:

- Introverts **start**  with <code>120</code> happiness and **lose**  <code>30</code> happiness for each neighbor (introvert or extrovert).
- Extroverts **start**  with <code>40</code> happiness and **gain**  <code>20</code> happiness for each neighbor (introvert or extrovert).

Neighbors live in the directly adjacent cells north, east, south, and west of a person's cell.

The **grid happiness**  is the **sum**  of each person's happiness. Return the **maximum possible grid happiness** .

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/05/grid_happiness.png" style="width: 261px; height: 121px;">

```
Input: m = 2, n = 3, introvertsCount = 1, extrovertsCount = 2
Output: 240
Explanation: Assume the grid is 1-indexed with coordinates (row, column).
We can put the introvert in cell (1,1) and put the extroverts in cells (1,3) and (2,3).
- Introvert at (1,1) happiness: 120 (starting happiness) - (0 * 30) (0 neighbors) = 120
- Extrovert at (1,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
- Extrovert at (2,3) happiness: 40 (starting happiness) + (1 * 20) (1 neighbor) = 60
The grid happiness is 120 + 60 + 60 = 240.
The above figure shows the grid in this example with each person's happiness. The introvert stays in the light green cell while the extroverts live on the light purple cells.
```

**Example 2:** 

```
Input: m = 3, n = 1, introvertsCount = 2, extrovertsCount = 1
Output: 260
Explanation: Place the two introverts in (1,1) and (3,1) and the extrovert at (2,1).
- Introvert at (1,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
- Extrovert at (2,1) happiness: 40 (starting happiness) + (2 * 20) (2 neighbors) = 80
- Introvert at (3,1) happiness: 120 (starting happiness) - (1 * 30) (1 neighbor) = 90
The grid happiness is 90 + 80 + 90 = 260.
```

**Example 3:** 

```
Input: m = 2, n = 2, introvertsCount = 4, extrovertsCount = 0
Output: 240
```

**Constraints:** 

- <code>1 <= m, n <= 5</code>
- <code>0 <= introvertsCount, extrovertsCount <= min(m * n, 6)</code>


## Assignment & Matching (Domain Inversion)

**Concept:** Assigning items from Set A to Set B.

**Trick:** If Set A is large (e.g., hats, N=40) and Set B is small (e.g., people, N=10), you must flip your thinking. Do not bitmask the hats; **bitmask the people**.

**State:** `dp[item_index][mask]` = number of ways to assign items up to `item_index` to the set of people represented by `mask`.

### [Number of Ways to Wear Different Hats to Each Other](https://leetcode.com/problems/number-of-ways-to-wear-different-hats-to-each-other/description/)

There are <code>n</code> people and <code>40</code> types of hats labeled from <code>1</code> to <code>40</code>.

Given a 2D integer array <code>hats</code>, where <code>hats[i]</code> is a list of all hats preferred by the <code>i^th</code> person.

Return the number of ways that <code>n</code> people can wear **different**  hats from each other.

Since the answer may be too large, return it modulo <code>10^9 + 7</code>.

**Example 1:** 

```
Input: hats = [[3,4],[4,5],[5]]
Output: 1
Explanation: There is only one way to choose hats given the conditions. 
First person choose hat 3, Second person choose hat 4 and last one hat 5.
```

**Example 2:** 

```
Input: hats = [[3,5,1],[3,5]]
Output: 4
Explanation: There are 4 ways to choose hats:
(3,5), (5,3), (1,3) and (1,5)
```

**Example 3:** 

```
Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
Output: 24
Explanation: Each person can choose hats labeled from 1 to 4.
Number of Permutations of (1,2,3,4) = 24.
```

**Constraints:** 

- <code>n == hats.length</code>
- <code>1 <= n <= 10</code>
- <code>1 <= hats[i].length <= 40</code>
- <code>1 <= hats[i][j] <= 40</code>
- <code>hats[i]</code> contains a list of **unique**  integers.


### [Minimum Cost to Connect Two Groups of Points](https://leetcode.com/problems/minimum-cost-to-connect-two-groups-of-points/description/)

You are given two groups of points where the first group has <code>size<sub>1</sub></code> points, the second group has <code>size<sub>2</sub></code> points, and <code>size<sub>1</sub> >= size<sub>2</sub></code>.

The <code>cost</code> of the connection between any two points are given in an <code>size<sub>1</sub> x size<sub>2</sub></code> matrix where <code>cost[i][j]</code> is the cost of connecting point <code>i</code> of the first group and point <code>j</code> of the second group. The groups are connected if **each point in both groups is connected to one or more points in the opposite group** . In other words, each point in the first group must be connected to at least one point in the second group, and each point in the second group must be connected to at least one point in the first group.

Return the minimum cost it takes to connect the two groups.

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/03/ex1.jpg" style="width: 322px; height: 243px;">

```
Input: cost = [[15, 96], [36, 2]]
Output: 17
Explanation
: The optimal way of connecting the groups is:
1--A
2--B
This results in a total cost of 17.
```

**Example 2:** 
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/03/ex2.jpg" style="width: 322px; height: 403px;">

```
Input: cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
Output: 4
Explanation
: The optimal way of connecting the groups is:
1--A
2--B
2--C
3--A
This results in a total cost of 4.
Note that there are multiple points connected to point 2 in the first group and point A in the second group. This does not matter as there is no limit to the number of points that can be connected. We only care about the minimum total cost.
```

**Example 3:** 

```
Input: cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
Output: 10
```

**Constraints:** 

- <code>size<sub>1</sub> == cost.length</code>
- <code>size<sub>2</sub> == cost[i].length</code>
- <code>1 <= size<sub>1</sub>, size<sub>2</sub> <= 12</code>
- <code>size<sub>1</sub> >= size<sub>2</sub></code>
- <code>0 <= cost[i][j] <= 100</code>

## Game Theory (Minimax with State)

**Concept:** Two players play a game. The state of the game is determined by which numbers/items have already been removed.

**State:** `dp[mask]` = boolean (True if current player can win given the current `mask` of used numbers).


### [Can I Win](https://leetcode.com/problems/can-i-win/description/)

In the "100 game" two players take turns adding, to a running total, any integer from <code>1</code> to <code>10</code>. The player who first causes the running total to **reach or exceed**  100 wins.

What if we change the game so that players **cannot**  re-use integers?

For example, two players might take turns drawing from a common pool of numbers from 1 to 15 without replacement until they reach a total >= 100.

Given two integers <code>maxChoosableInteger</code> and <code>desiredTotal</code>, return <code>true</code> if the first player to move can force a win, otherwise, return <code>false</code>. Assume both players play **optimally** .

**Example 1:** 

```
Input: maxChoosableInteger = 10, desiredTotal = 11
Output: false
Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
```

**Example 2:** 

```
Input: maxChoosableInteger = 10, desiredTotal = 0
Output: true
```

**Example 3:** 

```
Input: maxChoosableInteger = 10, desiredTotal = 1
Output: true
```

**Constraints:** 

- <code>1 <= maxChoosableInteger <= 20</code>
- <code>0 <= desiredTotal <= 300</code>


## Graph & TSP Variations (Traveling Salesman)

**Concept:** Finding the shortest/longest path that visits every node or meets specific criteria. The "history" of visited nodes must be stored in the mask.

**State:** `dp[mask][last_node]` = min cost to visit the set of nodes in `mask`, ending at `last_node`.

### [Shortest Path Visiting All Nodes](https://leetcode.com/problems/shortest-path-visiting-all-nodes/description/)

You have an undirected, connected graph of <code>n</code> nodes labeled from <code>0</code> to <code>n - 1</code>. You are given an array <code>graph</code> where <code>graph[i]</code> is a list of all the nodes connected with node <code>i</code> by an edge.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

**Example 1:** 
<img alt="" src="https://assets.leetcode.com/uploads/2021/05/12/shortest1-graph.jpg" style="width: 222px; height: 183px;">

```
Input: graph = [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
```

**Example 2:** 
<img alt="" src="https://assets.leetcode.com/uploads/2021/05/12/shortest2-graph.jpg" style="width: 382px; height: 222px;">

```
Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]
```

**Constraints:** 

- <code>n == graph.length</code>
- <code>1 <= n <= 12</code>
- <code>0 <= graph[i].length <n</code>
- <code>graph[i]</code> does not contain <code>i</code>.
- If <code>graph[a]</code> contains <code>b</code>, then <code>graph[b]</code> contains <code>a</code>.
- The input graph is always connected.
