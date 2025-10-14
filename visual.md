# A Guide to Visualizing LeetCode Patterns

Visualizing an algorithm helps transform abstract steps into a concrete, observable process. This is incredibly effective for understanding complex logic, debugging, and retaining knowledge. Here are visualization strategies for common LeetCode problem patterns.

-----

### 1\. Two Pointers & Sliding Window (Arrays/Strings)

This pattern involves iterating through a data structure with two pointers (or a "window" defined by them) that either move towards each other, away from each other, or in the same direction.

* **Best Visualization:** A visual representation of the array or string, with clear markers for the left and right pointers. The "window" between them can be highlighted.
* **What to Show:**
    * The movement of the pointers in each step.
    * The changing size and contents of the window.
    * A display for a running value (e.g., `current_sum`, `max_length`) that updates as the pointers move.
    * Color-code elements to show their state (e.g., inside the window, duplicate character).
* **Example Problem:** "Longest Substring Without Repeating Characters"
* **Visualization:**
    1.  Display the string `pwwkew`.
    2.  Show a left and right pointer, both starting at index 0. Highlight the window between them.
    3.  On the side, show a set or hash map of characters currently in the window.
    4.  Animate the right pointer moving. As it moves to the second `w`, the visualization shows that `w` is already in the set.
    5.  Animate the left pointer moving to the right, removing characters from the set and the highlighted window until the duplicate is gone.
    6.  Continuously update a "Max Length" counter.
* **Example LLM Prompt:**
  ```
  "Create a self-contained HTML file to visualize the 'Longest Substring Without Repeating Characters' algorithm. The UI should have an input for the string (default 'pwwkew'), a 'Next Step' button, and a 'Reset' button. Visually represent the string as a series of boxes. Use two markers for the 'left' and 'right' pointers. Highlight the current window. Display the set of characters in the current window and the maximum length found so far. Each click on 'Next Step' should advance the algorithm by one logical step (either moving the right pointer or the left pointer) and update the visualization and stats."
  ```

-----

### 2\. Tree Traversal (BFS & DFS)

Trees are inherently visual, making them perfect for this approach. The key is to show the order in which nodes are visited.

* **Best Visualization:** A classic node-and-edge tree diagram.
* **What to Show:**
    * **For BFS (Breadth-First Search):**
        * Highlight nodes level by level.
        * Display a queue data structure on the side. Animate nodes being added to the back of the queue and removed from the front.
    * **For DFS (Depth-First Search):**
        * Highlight the path from the root down to a leaf.
        * Animate the "backtracking" step, where the highlight moves back up to a parent node to explore a different branch.
        * Display the recursion call stack on the side to show the current depth.
* **Example Problem:** "Binary Tree Level Order Traversal" (BFS)
* **Visualization:**
    1.  Draw the binary tree.
    2.  Show a queue with the root node in it.
    3.  Animate the root being dequeued and highlighted.
    4.  Animate its children being enqueued and highlighted.
    5.  Repeat the process, showing a clear, level-by-level progression through the tree.
* **Example LLM Prompt:**
  ```
  "Generate a single HTML file to visualize a Binary Tree Level Order Traversal (BFS). Use a library like D3.js or a simple canvas to draw a binary tree from a default array representation (e.g., [3,9,20,null,null,15,7]). The UI should have 'Next Step' and 'Reset' buttons. On the side, visually represent the queue. Each click on 'Next Step' should show one step of the BFS algorithm: dequeue a node (highlight it in the tree), process it (add its value to the result), and enqueue its children (highlight them as they are added to the queue). Update the result array visually at each step."
  ```

-----

### 3\. Graph Algorithms (BFS, DFS, Dijkstra's)

Similar to trees, but with more complex connections (cycles, multiple paths). The goal is to show how the algorithm explores the graph.

* **Best Visualization:** A node-and-edge graph diagram.
* **What to Show:**
    * Color-code nodes based on their state: unvisited, visiting, visited.
    * For weighted graphs (like in Dijkstra's), display the "tentative distance" from the start node next to each node and update it as shorter paths are found.
    * Show a priority queue for Dijkstra's, animating nodes being added and the one with the smallest distance being extracted.
* **Example Problem:** "Number of Islands"
* **Visualization:**
    1.  Display the 2D grid. Color '1's as land and '0's as water.
    2.  When the algorithm starts a traversal on a '1', change its color to "visiting".
    3.  Animate the BFS or DFS traversal, spreading the "visiting" color to all connected land cells.
    4.  Once an entire island is traversed, change its color to "visited" (e.g., a darker shade).
    5.  Show an "Island Count" that increments each time a new traversal begins on an unvisited land cell.
* **Example LLM Prompt:**
  ```
  "Create a self-contained HTML file to visualize the 'Number of Islands' problem. The UI should display a 10x10 grid of clickable cells that can be toggled between 'land' ('1') and 'water' ('0'). Provide a 'Run' button. When clicked, the visualization should step through the grid, using a different color for 'visiting' land cells during a DFS or BFS traversal. Once an island is fully explored, change its color to a 'visited' state. A counter on the side should increment each time a new, unvisited island is discovered. The animation should be slow enough to follow the exploration process."
  ```

-----

### 4\. Dynamic Programming

DP is about solving complex problems by breaking them down into a collection of simpler subproblems. Visualizing the DP table is the most effective strategy.

* **Best Visualization:** A 1D or 2D grid representing the DP table.
* **What to Show:**
    * Animate the process of filling the table, cell by cell.
    * When calculating the value for `dp[i][j]`, highlight the other cells it depends on (e.g., `dp[i-1][j]`, `dp[i][j-1]`). This makes the recurrence relation obvious.
    * Show the final result being extracted from one of the cells (usually the last one).
* **Example Problem:** "Longest Common Subsequence"
* **Visualization:**
    1.  Create a 2D grid with one string along the top and the other along the left.
    2.  Fill the grid cell by cell. When `str1[i] == str2[j]`, highlight the diagonal cell `dp[i-1][j-1]` before adding 1 to its value.
    3.  If they don't match, highlight the cells to the top and left, `dp[i-1][j]` and `dp[i][j-1]`, before taking their max.
* **Example LLM Prompt:**
  ```
  "Generate a single HTML file to visualize the 'Longest Common Subsequence' algorithm. The UI should have two text inputs for the strings (default 'AGGTAB' and 'GXTXAYB') and a 'Next Step' button. Display the DP table as an HTML grid. Each click on 'Next Step' should fill in the next cell of the DP table. When a cell dp[i][j] is being computed, highlight the cells it depends on and show the logic being applied. The final cell containing the answer should be highlighted distinctly at the end."
  ```

-----

### 5\. Backtracking

Backtracking explores different choices recursively and "backtracks" when a choice leads to a dead end.

* **Best Visualization:** A recursion tree or decision tree.
* **What to Show:**
    * Each node in the tree represents a state or a choice.
    * Animate the traversal down a path as choices are made.
    * If a path becomes invalid or a solution is found, animate the algorithm returning up the tree to the previous decision point.
    * Display the "current state" (e.g., the subset being built, the queens placed on the board) at each step.
* **Example Problem:** "N-Queens"
* **Visualization:**
    1.  Display an N x N chessboard.
    2.  Animate placing a queen in the first column.
    3.  In the next recursive call, animate trying to place a queen in the second column, row by row. Highlight invalid squares in red.
    4.  If a valid square is found, place the queen and proceed.
    5.  If no valid square is found in a column, animate removing the queen from the previous column and trying its next valid row (this is the "backtracking" step).
* **Example LLM Prompt:**
  ```
  "Create a self-contained HTML file to visualize the N-Queens problem for a 4x4 board. The UI should have 'Next Step' and 'Reset' buttons. Display a 4x4 chessboard. Each click on 'Next Step' should execute one step of the backtracking algorithm: either placing a queen in a valid square, or failing to find a valid square in a column and backtracking (removing the previous queen). Use colors to indicate the current queen being placed, and red highlights for squares under attack by existing queens. A side panel should show the current recursive call stack or the current state of placed queens."
  ```

-----

### 6\. Binary Search

This pattern is used for searching in a sorted data structure. The key is to repeatedly divide the search interval in half.

* **Best Visualization:** A visual representation of the array with markers for the search boundaries and midpoint.
* **What to Show:**
    * Markers for `low`, `high`, and `mid` pointers.
    * Highlight the current search space `[low...high]`.
    * Show the comparison between the target value and the element at `mid`.
    * Gray out or fade the half of the array that is discarded after each comparison.
    * Animate the `low` or `high` pointer moving to its new position.
* **Example Problem:** "Binary Search"
* **Visualization:**
    1.  Display the full sorted array.
    2.  Show `low` at index 0 and `high` at the last index.
    3.  Calculate `mid` and move a pointer to that index.
    4.  Compare the target to the value at `mid` and display the result.
    5.  Animate the discarded half of the array being grayed out.
    6.  Animate the `low` or `high` pointer moving to narrow the search space.
    7.  Repeat until the element is found or the search space is empty.
* **Example LLM Prompt:**
  ```
  "Create a self-contained HTML file to visualize the Binary Search algorithm. The UI should have an input for a sorted comma-separated array and a target value. Add 'Next Step' and 'Reset' buttons. Visually represent the array as boxes. Use markers for 'low', 'high', and 'mid' pointers. On each 'Next Step', show the calculation of 'mid', the comparison with the target, and then gray out the discarded half of the array before updating the 'low' or 'high' pointer. Highlight the 'mid' element in green if it matches the target."
  ```

-----

### 7\. Heaps / Priority Queues

This pattern is used for problems involving finding the Kth smallest/largest element, scheduling, or merging sorted collections.

* **Best Visualization:** A dual view showing both the binary tree structure of the heap and its underlying array representation.
* **What to Show:**
    * The heap drawn as a complete binary tree.
    * The array that backs the heap.
    * Animate elements being added, showing the sift-up (or heapify-up) process where an element bubbles up the tree, with corresponding swaps in the array.
    * Animate elements being removed (usually the root), showing the sift-down (or heapify-down) process where the replacement element sinks down.
* **Example Problem:** "Kth Largest Element in an Array"
* **Visualization:**
    1.  Display the input array and an empty min-heap of size k.
    2.  Animate iterating through the input array, one element at a time.
    3.  If the heap is not full, show the element being added, followed by the sift-up animation.
    4.  If the heap is full and the current element is larger than the heap's root, show the root being replaced. Animate the new element sift-down to its correct position.
    5.  The final root of the heap should be clearly marked as the answer.
* **Example LLM Prompt:**
  ```
  "Generate a single HTML file to visualize finding the 'Kth Largest Element' using a Min-Heap. The UI should take an array and a value for 'k'. Have a 'Next Step' button. Display the min-heap visually as both a tree and an array. On each step, show the next element from the input array being considered. If it's added to or swapped into the heap, animate the 'heapify' (sift-up or sift-down) process by showing the node swaps in both the tree and array views. The final element at the root of the heap should be clearly marked as the answer."
  ```

-----

### 8\. Linked List Manipulation

This pattern involves restructuring a linked list by changing the `next` pointers of its nodes.

* **Best Visualization:** A sequence of nodes (boxes) connected by arrows.
* **What to Show:**
    * Nodes containing a value.
    * Arrows representing the `next` pointers.
    * Markers for temporary pointers used in the algorithm, such as `prev`, `current`, and `next_temp`.
    * Animate the pointers moving from one node to the next.
    * Animate the arrows being re-wired to point to different nodes. This is the most crucial part.
* **Example Problem:** "Reverse a Linked List"
* **Visualization:**
    1.  Display the linked list horizontally (e.g., `1 -> 2 -> 3 -> null`).
    2.  Show markers for `prev` (pointing to `null`) and `current` (pointing to the head).
    3.  In a loop: Animate a `next_temp` pointer being set to `current.next`.
    4.  Animate the arrow from `current` flipping to point to `prev`.
    5.  Animate the `prev` marker moving to where `current` is.
    6.  Animate the `current` marker moving to where `next_temp` is.
    7.  Repeat until `current` is `null`.
* **Example LLM Prompt:**
  ```
  "Create a self-contained HTML file to visualize the iterative 'Reverse a Linked List' algorithm. Display a linked list (e.g., 1 -> 2 -> 3 -> 4 -> 5) as boxes connected by arrows. Use labels for 'prev', 'current', and 'next_temp' pointers. A 'Next Step' button should advance the algorithm by one iteration of the loop. Animate the re-wiring of the 'next' pointer at each step and the movement of the three pointers. The final prev pointer should be marked as the new head."
  ```