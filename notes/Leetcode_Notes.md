
# Algorithms and Data Structures


## Formulas

- Number of subarrays and substrings = `n(n+1) / 2`
- Number of subsequences = `2^n - 1`
- Number of subsets = `2^n`
- Number of contiguous subarrays with size `k` = `n - k + 1`


## Sorting

| **Sorting Algorithm** | **Type**                   | **Time Complexity (Avg/Worst)** | **Space Complexity** | **Stable?**   | **When to Use**                                                            |
|-----------------------|----------------------------|---------------------------------|----------------------|---------------|----------------------------------------------------------------------------|
| **Bubble Sort**       | Comparison-based           | O(n²) / O(n²)                   | O(1)                 | Yes           | Educational purposes only. Easy to implement but very inefficient.         |
| **Selection Sort**    | Comparison-based           | O(n²) / O(n²)                   | O(1)                 | No            | When memory is limited and stability isn't required. Rarely used.          |
| **Insertion Sort**    | Comparison-based           | O(n²) / O(n²)                   | O(1)                 | Yes           | Good for small or nearly sorted datasets. Used in hybrid sorts.            |
| **Merge Sort**        | Divide & Conquer           | O(n log n) / O(n log n)         | O(n)                 | Yes           | When stability is needed and memory isn't a constraint.                    |
| **Quick Sort**        | Divide & Conquer           | O(n log n) / O(n²)              | O(log n)             | No            | Fastest general-purpose sort for large datasets, but unstable.             |
| **Heap Sort**         | Comparison-based           | O(n log n) / O(n log n)         | O(1)                 | No            | Useful when constant space is required; not stable.                        |
| **Counting Sort**     | Non-comparison             | O(n + k) / O(n + k)             | O(k)                 | Yes           | Best for small integer ranges. Very fast when applicable.                  |
| **Radix Sort**        | Non-comparison             | O(nk) / O(nk)                   | O(n + k)             | Yes           | Good for fixed-length integers or strings. Fast and stable.                |
| **Bucket Sort**       | Non-comparison             | O(n + k) / O(n²)                | O(n)                 | Yes (depends) | Great for uniformly distributed floating point numbers.                    |
| **Tim Sort**          | Hybrid (Merge + Insertion) | O(n log n) / O(n log n)         | O(n)                 | Yes           | Used in Python (`sorted()`), Java (`Arrays.sort()`); efficient and stable. |
| **Shell Sort**        | Comparison-based           | O(n log n) / O(n²)              | O(1)                 | No            | Better than Insertion Sort for medium datasets. Unstable but simple.       |

**1. Bubble Sort**

- Compare adjacent elements and swap them if they are in the wrong order.
-  In a single pass, the smallest or largest element reaches at the end of the array, its in sorted order.

**2. Selection Sort**

- Find the smallest or largest element in array and swap it with the first element. Which becomes sorted.

**3. Insertion Sort**

- Pick an element(2nd element) and compare it with the previous element, if it is smaller than the previous element, 
- then move the prev element to the next position, at last insert the picked element at the correct position.

**Examples:**

- [Leetcode Insertion Sort Linked List](https://leetcode.com/problems/insertion-sort-list/description/)

**4. Merge Sort**

- Recursively Divide the array until it has only one element.
- Then in the merge step, first create two array from left to mid and mid+1 to right.
- Copy from original array to these two arrays.
- Then merge these two arrays in sorted order.

**Examples:**

- [Leetcode Sort Linked List](https://leetcode.com/problems/sort-list/description/)

**5. Quick Sort**

- Select a pivot element, either first, last or random element.
- then partition the array such that all elements less than pivot are on left and greater are on right.
- The pivot element is at its sorted position.
- Recursively apply the same steps to the left and right subarrays.

**6. Heap Sort**

**7. Counting Sort**

- Find the max value, calculate then length
- Count the occurrences
- Store the cumulative count
- From the last take the elements find the right index and place it in the output array

**8. Bucket Sort**

- The input is uniformly distributed over a range

**9. Radix Sort**

**10. Cyclic**

```java
 void cyclicSort(int[] arr) {
      int i = 0;
      while (i < arr.length) {
          // Calculate the correct index for the current element
          int correctIndex = arr[i] - 1;
          if (arr[i] != arr[correctIndex]) {
              // Swap the current element with the one at its correct position
              swap(arr, i, correctIndex);
          } else {
              // Move to the next element if it's in the correct position
              i++;
          }
      }
}
```

---

## String

**1. Basic String Manipulation or Processing** 

**Examples:**

- [Shortest Distance to a Character](https://leetcode.com/problems/shortest-distance-to-a-character/description/)

  - Store the index of target character in `Treeset`
  - Compute the distance to both the floor and ceiling indices `Math.min(Treeset.ceiling(i) - i,  i - Treeset.floor(i))`

- [Top K Frequent Words](https://leetcode.com/problems/top-k-frequent-words/description/?envType=problem-list-v2&envId=bucket-sort)
  
  - Use a `HashMap` to store the frequency of each word.
  - Use a `PriorityQueue` to store the words based on frequency and lexicographical order with custom comparator.
     ```java
      Queue<String>  queue = new PriorityQueue<>((a, b) -> {
        if(map.get(b) == map.get(a)) {
          return a.compareTo(b); // Sort alphabetically in ascending order
        }else{
          return map.get(b) - map.get(a); // Sort by frequency in descending order
        }
      } );
     ```

- [String Compression](https://leetcode.com/problems/string-compression/description/) 

  - Count the adjacent charcters using `forward inner while loop` then add the character and count to the result

  
  
**2. Palindrome Problems** 

**Examples:**

- [Total Palindrome substrings]()
   
   - Expand around the center, for each character in the string, expand around the center and check for palindrome

   ```java
    int extendPalindrome(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int odd = palindromeCount(s, i, i); // odd Length
            int even = palindromeCount(s, i, i + 1); // even Length
            ans += even + odd;
        }
        return ans;
    }

   int palindromeCount(String s, int left, int right) {
        int count = 0;
        int n = s.length();
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }
   ```


---

## Array

- For Circular Array, use `i % n` to get the correct index `i < n * 2`
- For negative index, `(i % n + n) % n`
- For odd length array  `n / 2` gives the `middle index`
- For even length array `n / 2 - 1` -  lower middle index  and `n / 2` - upper middle index
- Middle index based on last index
   - For odd length array `lastIndex + 1 / 2` 
   - For even length array `(lastIndex + 1 / 2) - 1` - Lower middle index and `(lastIndex + 1 / 2)` - Upper middle index
- Rotated Index in Rotated Arrays
   - `(i + k) % n` (for right rotation by k).
   - `(i - k + n) % n` (for left rotation by k)

**1. Subarrays** 

- Total Subarrays = `n * (n + 1) / 2`
- Sum Of All Subarrays : `contribution = (i + 1) * (n - i)` and `sum += arr[i] * contribution`
- Sum Odd Length Subarrays : `contribution = ( (i + 1) * (n - i) + 1 ) / 2` and `sum += arr[i] * contribution`
- Use **`Map`** to store the **`index, pair, count`** of element to see in future and past and calculate distance between index, Like **`pair(count, last seen index, current index)`**

 - ```java
    int sumOddLengthSubarrays(int[] arr) {
        int sum = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int contribution = ((i + 1) * (n - i) + 1) / 2;
            sum += arr[i] * contribution;
        }
        
        return sum;  
    }
   ``` 

**Examples:**

- [Check array is rotated and sorted](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

  - Check deviations `nums[i] > nums[(i + 1) % nums.length]`, If array is sorted and rotated array there will 0 or 1 incorrectly Positioned element


- [Remove Duplicates(In place)](), [Move Zeroes at end(In place)](), [Remove Elements(In place)]()
   
   - Solve using two pointers, one for `iterating` and one for `placing` the element
   - Create a `placing index`, then replace or swap `iterating index` with placing index based on condition

---
## Binary Search

- `low + (high - low) / 2`  - Selects lower middle, if there are even elments 
- `low + high / 2`  - Selects lower middle, if there are even elments 
- `low + (high - low + 1) / 2`  - Selects upper middle, if there are even elments 

- `Binary Search for the First True in a Boolean Array` or `Binary Search for the First Valid Element`

  ```java
    // int[] scores = {-1, -1, -1, 50, 60, 70, 80}; // Sorted array with -1 representing absent students
    int findFirstValidScore(int[] scores) {
          int left = -1; // Left boundary (starts before the array)
          int right = scores.length; // Right boundary (starts after the array)

          // Binary search to find the first valid score
          while (right - left > 1) {
              int mid = left + (right - left) / 2; // Middle point
              if (scores[mid] == -1) {
                  left = mid; // If -1, move the left boundary
              } else {
                  right = mid; // If valid score, move the right boundary
              }
          }

          // Return the index of the first valid score
          return right;
      }
  ```


**1. Classic Binary Search**

**Examples:**

- [Find First and Last Occurences of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

    - When target is found for first occurrence, move `right = mid-1`
    - When target is found for last occurrence, move `left = mid+1`

- Floor and Ceil of a number in a sorted array

    - If `target > mid`, then ` floor = mid and low = mid + 1`
    - If `target < mid`, then ` ceil = mid and high = mid - 1`

- [Search Insert Position](https://leetcode.com/problems/search-insert-position/)

    - ```java 
        int searchInsert(int[] nums, int target) {
            int low = 0;
            int high = nums.length; // Full Length

            while (low < high) {
                int mid = low + (high - low) / 2;
                if (target <= nums[mid]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            
            return low;
        }
      ```


**2. Binary Search on the Result**

**Examples:**

- [Minimum Number of Days to Make m Bouquets](https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/) - Find the minimum number of days required to make m bouquets

  - Apply binary search on the range of days, then validate if it is possible to make m bouquets in `mid` days.
  - Only adjacent flowers can be used to make a bouquet, so reset the count of flowers when `bloomDay > day`

- [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/) - Minimize the speed k at which Koko eats bananas to finish in time.

  - Consider the range like max value in array, then apply `modified binary search on the range(1...N)`
  - Validate the condition, if it is possible to eat all bananas in `mid` speed(k).

- [Capacity to Ship Packages Within D Days](https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/) - Minimize the capacity needed to ship packages in a given time.


**3. Rotated Sorted Arrays**

- Use Modified Binary Search, find smallest or pivot `if(nums[mid] <= nums[high])`, then apply Binary Search on left or right part.
- Normal Binary Search `mid = (low + high) / 2 , realMidValue = (mid + rotationPoint) % n`

**Examples:**

- [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/) 

- [Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) 

- [Find Minimum in Rotated Sorted Array Contains Duplicates ](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/)

  - ```java 
      int findMin(int[] nums) {
          int low = 0;
          int high = nums.length - 1;

          while (low < high) {
              int mid = low + (high - low) / 2;
              if (nums[mid] < nums[high]) {
                  high = mid;
              } else if (nums[mid] > nums[high]) {
                  low = mid + 1;
              } else { // nums[mid] == nums[hi])

                  if (nums[high - 1] > nums[high]) {
                      low = high; // low is Pivot index or min element
                      break;
                  }
                  high--;
              }
          }
          return nums[low];
      } 
    ```


**4. Searching in Monotonic Functions**

**Examples:**
- [Find Peak Element](https://leetcode.com/problems/find-peak-element/) – Find a local maximum in the array.
  - `if (arr[mid] < arr[mid+1])`
- [Peak Index in a Mountain Array](https://leetcode.com/problems/peak-index-in-a-mountain-array/) – Find the peak in a "mountain array."
  - `if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1])`
- [Find a Peak Element II](https://leetcode.com/problems/find-a-peak-element-ii/) – Find a peak in a 2D grid.


**5. Searching in Multi-Dimensional Arrays**
  - Treat the matrix as a flat array `matrix[mid / n][mid % n]`, where n is column length
  - Exploit row/column properties.

**Examples:**
- [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/) – Search in a matrix where rows and columns are sorted.
- [Search a 2D Matrix II](https://leetcode.com/problems/search-a-2d-matrix-ii/) – Search in a matrix with sorted rows and columns.
  
---
## Linked List

- Todo


---
## Stack

**1. Stack Simulation**

- [Decode String](https://leetcode.com/problems/decode-string/) -  Use a stack to decode nested encoded strings (e.g., `"3[a2[c]]"` becomes `"accaccacc"`).

  - Use two stacks, one for numbers and one for strings.
  - when digit is encountered, push into number stack
  - When an open bracket is encountered, initialize a string variable
  - When character is encountered, append to the string variable
  - when close bracket is encountered, pop the number and repeat the string that many times.

- [Remove All Adjacent Duplicates in String II](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/) -  Use a stack to remove adjacent duplicates in a string when they occur \( k \) times consecutively.

   - `Solution 1` : Use Two Stack one to store Cumulative frequency and one to store character, when TOP is K, then pop the K times from both the stack
   - `Solution 2` : Use pair to store the char and total frequency, when count of TOP is K, then pop from stack

- [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/) - Simulate collisions between asteroids using stack mechanics.

  - Asteroid Collision happens when `stack.peek() > 0 && asteroid[i] < 0`
  
- [Remove K Digits](https://leetcode.com/problems/remove-k-digits/) - Simulate the removal of digits to achieve the smallest possible number using a monotonic stack.

- [Car Fleet](https://leetcode.com/problems/car-fleet/) - Simulate car fleets merging using a stack based on their speeds and positions. 



**2. Valid Parentheses and Expressions Evaluation**

- **Infix Expression**: The operators are written between the operands. Example: `A + B, (A + B) * C`.  
  **How to Solve**: 

- **Postfix Expression (Reverse Polish Notation)**: The operators are written after the operands. Example: `AB+, AB+C*`.  
  **How to Solve**: 

- **Prefix Expression (Polish Notation)**: The operators are written before the operands. Example: `+AB, *+ABC`.  
  **How to Solve**: 

**Examples:**
- [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) - Check if parentheses are balanced in a string.

- [Minimum Add to Make Parentheses Valid](https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/) - Count the minimum additions required to make a string of parentheses valid.

- [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) - Evaluate a postfix expression using a stack.

- [Basic Calculator](https://leetcode.com/problems/basic-calculator/) - Handle parentheses and operators in infix expressions.

- [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii/) - Evaluate an infix arithmetic expression.
  

**3. Monotonic Stack Problems**

**Examples:**
- [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) - Find the number of days until a warmer temperature.

- [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) - Find the largest rectangular area in a histogram using a monotonic stack.

- [Sum of Subarray Ranges](https://leetcode.com/problems/sum-of-subarray-ranges/) - Calculate the sum of the range (difference between maximum and minimum) of all subarrays.  

- [Sum of Subarray Minimums](https://leetcode.com/problems/sum-of-subarray-minimums/) - Calculate the sum of the minimum elements of all subarrays.

- [Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/description/) - Remove duplicate letters to make the string lexicographically smallest.

  ```java
      String removeDuplicateLetters(String s) {
          HashMap<Character, Integer> map = new HashMap<>();
          for (char c : s.toCharArray()) {
              map.put(c, map.getOrDefault(c, 0) + 1);
          }

          Stack<Character> stack = new Stack<>();
          HashSet<Character> seen = new HashSet<>();

          for (char c : s.toCharArray()) {
              map.put(c, map.get(c) - 1);
              if (seen.contains(c)) continue;

              while (!stack.isEmpty() && stack.peek() > c && map.get(stack.peek()) > 0) {
                  seen.remove(stack.pop());
              }

              stack.push(c);
              seen.add(c);
          }

          StringBuilder result = new StringBuilder();
          for (Character c : stack) {
              result.append(c);
          }

          return result.toString();
      }
  ```

---

## Sliding Window

- Window Size = `j - i + 1`
- Remove first element from window `i - k` k is window size
- Circular Sliding Window
   - Start of the window - `i % n`
   - End of the window - `(i + k - 1) % n` where k is the window size.
- Digits - `count = new int[10]`, Small Aphabets - `count = new int[26]`, Big Aphabets - `count = new int[128]`
- When `two strings` are invloved first create a map of frequency of first string, then compare with second string
- When `one string or array` is involved, Inside the loop increment and decrement the count of that element in map

**Examples:**

**1. Fixed Window Size**

- [Maximum Points From Cards](https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/) - Pick cards from either the beginning or the end to maximize the total points.

  - Calculate total sum, then calculate `remaining window sum and size(arr.length - k)`, find sum of fixed remaining window size, deduct from total sum which give sum of k window size
  - ```java
       int maxScore(int[] cardPoints, int k) {
          int totalSum = 0;
          for (int point : cardPoints) {
              totalSum += point;
          }
  
          int remWindowSize = cardPoints.length - k;
          int remWindowSum = 0;
  
          for (int i = 0; i < remWindowSize; i++) {
              remWindowSum += cardPoints[i];
          }
  
          int result = Math.max(0, totalSum - remWindowSum);
  
          for (int i = remWindowSize; i < cardPoints.length; i++) {
              remWindowSum += cardPoints[i] - (cardPoints[i - remWindowSize]);
              result = Math.max(result, totalSum - remWindowSum);
          }
  
          return result;
      }
    ```

**2. Variable Window Size**

- [Permutations in String](https://leetcode.com/problems/permutation-in-string/) - Check if `s2` contains any permutation of `s1`,  s1 = "ab",  s2 = "eidbaooo"

   - ```java
        boolean checkInclusion(String s1, String s2) {
          int left = 0;
          int[] count = new int[26];
  
          for (int i = 0; i < s1.length(); i++) {
              count[s1.charAt(i) - 'a']++;
          }
  
          for (int right = 0; right < s2.length(); right++) {
              char current = s2.charAt(right);
              count[current - 'a']--;
  
              while (count[current - 'a'] < 0) { // negative means s2 does not contain in s1
                  count[s2.charAt(left) - 'a']++;
                  left++;
              }
  
              if (right - left + 1 == s1.length()) return true;
          }
  
          return false;
      }
     ```
      


**3. Longest/Shortest Subarrays**

- [Longest Subarray K Frequency](https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/description/) - Find the longest subarray with exactly `k` distinct elements.

    - Loop and put into a map, expand the window until map size is <= k, then remove the count from map from left side and shrink the window if exceeds k, at last update the max length

    ```java
        int maxSubarrayLength(int[] nums, int k) {
          Map<Integer, Integer> count = new HashMap<>();

          int left = 0;
          int result = 0;

          for (int right = 0; right < nums.length; right++) {

              count.put(nums[right], count.getOrDefault(nums[right], 0) + 1);

              while (count.get(nums[right]) > k) {
                  count.put(nums[left], count.get(nums[left]) - 1);
                  left++;
              }

              result = Math.max(result, right - left + 1);
          }

          return result;
      }
    ```

**4. Longest/Shortest Substrings**

- [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) - Given a string, replace up to `k` characters to find the longest substring with the same character.

   - Calculate the most frequent character and reduce the window if its satisfies `(right - left + 1) - mostFrequent > k`

- [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) - Find the smallest substring in `s` that contains all characters from `t`.

   - Similiar concept to `Permutations in String`, Keep track of starting index and min length of window.

**5. Number of Subarrays**

- [Nice Subarrays](https://leetcode.com/problems/count-number-of-nice-subarrays/description/) - Find the number of subarrays where the number of odd integers is exactly `k`.

  - `subarrayAtMostK(nums, k) - subarrayAtMostK(nums, k - 1)` - Total Subarrays with atmost k - Total Subarrays with atmost k-1

  ```java
    int subarrayAtMostK(int[] nums, int K) {
        int result = 0;
        int left = 0;
        int right = 0;
        int count = 0;

        while (right < nums.length) {
            if (nums[right] % 2 == 1)  count++;
          
            while (count > K) {
                if (nums[left] % 2 == 1)  count--;
                left++;
            }

            result += right - left + 1; // Total Subarrays
            right++;
        }

        return result;
    }

    int numberOfSubarrays(int[] nums, int k) {
        return subarrayAtMostK(nums, k) - subarrayAtMostK(nums, k - 1);
    }
  ```

- [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) - Find the maximum value in each sliding window of size `k`.

   ```java
      int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            // Check if the element exceeds the maximum size in deque
            // i - k remove last element from window if its exceeds size
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }

            // Create a decreasing monotonic queue, like [5, 4, 3, 1]
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);

            // add all the elements of window
            // First valid window i >= k - 1
            if (i >= k - 1 && !deque.isEmpty()) {
                result[index++] = nums[deque.peekFirst()];
            }
        }

        return result;
    }
   ```

- [Subarrays K Different Integers](https://leetcode.com/problems/subarrays-with-k-different-integers/) - Find the number of subarrays with exactly `k` different integers.

  - `countAtMostKSubarrays(nums, k) - countAtMostKSubarrays(nums, k - 1)` - Total Subarrays with atmost k - Total Subarrays with atmost k-1

**6. Number of Substrings**

- [Substrings Containing 3 Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/description/) - Find the number of substrings that contain exactly 3 distinct characters.

   - `s.length() − right` total substrings can be formed including `[left-right] to N`
    
---
## Prefix Sum

- `prefix[j] - prefix[i - 1] = k` sum of a subarray from index i to j is equal to k
- `prefix[i - 1] = prefix[j] - k` , prefix[i - 1] is valid subarray with sum k
- Subarray sum multuple of k, `prefix[j] % k = prefix[i - 1] % k` 


**1. Subarray Problems with Prefix Sum**

**Examples:**

- [Range Sum Query 2D - Immutable](https://leetcode.com/problems/range-sum-query-2d-immutable/)

- [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

- [Continuous Subarray Sum](https://leetcode.com/problems/continuous-subarray-sum/)

- [Longest Subarray with Sum Divisible by K](https://leetcode.com/problems/subarray-sums-divisible-by-k/)


---
## Greedy

- [Greedy Template](https://huaguo.substack.com/p/greedy-algorithm)


## Trees

- `Inorder successor(smallest element in left subtree from right node)` is next node element in inorder traversal(Sorted Element in BST)


**0. Tree Representations in array**

- For 1-based indexing:

  - **Left child** of node at index `i`: `2 * i`
  - **Right child** of node at index `i`: `2 * i + 1`
  - **Parent** of node at index `i`: `i // 2` (only if `i > 1`)

- For 0-based indexing:

  - **Left child** of node at index `i`: `2 * i + 1`
  - **Right child** of node at index `i`: `2 * i + 2`
  - **Parent** of node at index `i`: `(i - 1) // 2` (only if `i > 0`)

**1. Traversal**

`Note : Visualize with 3 nodes`

  1. **Inorder Iterative(Left-Root-Right)**  

  - Initialize `current variable` with root, push left node until its null
  - Pop last left node process it, then initialize current variable with right node

  2. **Preorder Iterative(Left-Root-Right)**

  - First add root to stack
  - While stack is not empty pop from stack process the element, then push right node and then left node

  3. **Postorder Iterative(Left-Root-Right)**
  
  - Create two stack input and output
  - Push root to a input stack, the pop from stack, then push the element to ouput stack
  - Push left node to input stack and right node to input stack

**2. BST Operations**  

- Insertions

  ```java
      TreeNode insert(TreeNode root, int key) {
          if (root == null) {
              return new TreeNode(key);
          }
          if (key < root.val) {
              root.left = insertHelper(root.left, key); // Fill the new node
          } else if (key > root.val) {
              root.right = insertHelper(root.right, key); // // Fill the new node
          }
          return root; // return or propagate the root, means fill the left and right child of parent node
      }
  ```

- Deletions 

  ```java
    TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val)
            root.left = deleteHelper(root.left, key); // If no child,  null is filled, if one node filled either one left or right node
        else if (key > root.val)
            root.right = deleteHelper(root.right, key);
        else {
            // Node with only one child or no child
            if (root.left == null) return root.right;
            else if (root.right == null)  return root.left;

            root.val = inorderSuccessor(root.right); // Replace with Inorder Successor
            root.right = deleteHelper(root.right, root.val); // Delete the inorder successor
        }

        return root;
    }
  ```

**3. Depth/Height** 

- [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/description/)

  ```java
    int diameter = 0;

    int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }
  ```

- [Max Depth of binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/description/)

  ```java 
    int maxDepth(TreeNode root) {
       if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left , right) + 1;   
    }
  ```

- [Maximum Depth of N-ary Tree](https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/)

  ```java
    int maxDepth(Node root) {
        if (root == null) return 0;

        if (root.children.isEmpty()) return 1;

        int depth = 0;
        for (Node child : root.children) {
            depth = Math.max(depth, maxDepth(child)); // This find max depth for each children
        }

        return depth + 1; // max depth of a child and including root  
    }
  ```  


**4. Path problem binary tree** 

- Path from root to leaf for target sum

  ```java
    boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        
        if ( root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);  
    }
  ```

**5. Comparison on Two Trees**

- In [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/description/) Compare two subtree parallely.
   
  ```java
    boolean helper(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null || q == null) return false;

        return (p.val == q.val) && helper(p.left, q.right) && helper(p.right, q.left);
    }
  ```

**6. Counting nodes in Tree**


**Examples**

- [Count Good Nodes in Binary Tree](https://leetcode.com/problems/count-good-nodes-in-binary-tree/description/)
   
   - In function call keep one variable contains max value in tree path, then compare max value with root value, count good node and update the max value.

   ```java
      int good;
      void DFS(TreeNode root, int max) {
          if (root == null)
              return;

          if (root.val >= max)
              good++;

          max = Math.max(max, root.val);
          DFS(root.left, max);
          DFS(root.right, max);
      }
   ```  


**7. Ancestor**

- [Lowest Common Ancestor](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) in BST

  ```Java 
      TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
          int small = Math.min(p.val, q.val);
          int large = Math.max(p.val, q.val);
          while (root != null) {
              if (root.val > large) // p, q belong to the left subtree
                  root = root.left;
              else if (root.val < small) // p, q belong to the right subtree
                  root = root.right;
              else // Now, small <= root.val <= large -> This root is the LCA between p and q
                  return root;
          }
          return null;
      }
  ```

**8. Different view of tree**

**Examples:**

- [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)
  
  - Process one node(right) at each level, maintain level parameter in function call

  ```java
     void helper(List<Integer> result, TreeNode root, int level) {
          if (root == null) return;

          if (level == result.size()) result.add(root.val);

          helper(result, root.right, level + 1);
          helper(result, root.left, level + 1);
    }
  ```
- [Binary Tree Left Side View](https://leetcode.com/problems/binary-tree-right-side-view/solutions/3125913/java-all-tree-views-easy-fast/)

  - Process one node(left) at each level, maintain level parameter in function call

- [Binary Tree Top Side View](https://leetcode.com/problems/binary-tree-right-side-view/solutions/3125913/java-all-tree-views-easy-fast/)

  - Use `level order traversal`, create pair of node and horizontal distance.
  - Assign horizontal distance to each node, like root is 0, left child is -1, right child is +1
  ```
          1(0)
        /     \
      2(-1)    3(+1)
    /  \     /   \
  4(-2) 5(0) 6(0)  7(+2)

  ```
  - If distance does not exist in TreeMap, then add the node to TreeMap, Top view node will have unique distance

- [Binary Tree Bottom Side View](https://leetcode.com/problems/binary-tree-right-side-view/solutions/3125913/java-all-tree-views-easy-fast/)

  - Similar to top view, the only difference is that we need to replace the node in TreeMap with the same distance

- [Binary Tree Diagonal View]()  

  - start with root 0, for left node assign same distance and for right node assign distance + 1


**9. Tree construction** 

**Examples:**

- [Construct Unique Binary Search Trees](https://leetcode.com/problems/unique-binary-search-trees-ii/description/)

- [Number of Unique Binary Search Trees](https://leetcode.com/problems/unique-binary-search-trees/description/)

```java

   long binomialCoefficient(int n, int k) {
        long res = 1;

        if (k > n - k)
            k = n - k; // Using the property: C(n, k) = C(n, n-k)

        for (int i = 0; i < k; i++) {
            res *= (n - i); // Multiply by decreasing numerator
            res /= (i + 1); // Divide by increasing denominator
            // Using the property of the associativity of multiplication and division:
            // (a / b) × (c / d) = (a × c) / (b × d)
        }

        return res;
    }

   int numTrees(int n) {
        return (int) (binomialCoefficient(2 * n, n) / (n + 1));
    }

```

- [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/)

- [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/)
   

**10 Serialize and Deserialize**

**Examples:**

- [Verify Preorder Serialization of a Binary Tree](https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/description/) - Serialized tree "9,3,4,#,#,1,#,#,2,#,6,#,#"

   - Use stack to collapse the nodes if prev 3 nodes are `4,#,#` pattern into single hash `#`
   - If stack size is 1 and its `#` value then return true else false

- [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/) 

   - Use `preorder traversal` to serialize the tree
   - Use `preorder traversal` to deserialize the tree 

**11. B and B+ Tree**  


**12. AVL Tree**   


**13. Red-Black Tree**  


**14. Segment Tree**  


---
## Backtracking

- **Pruning the search** : We can often optimize backtracking by pruning the search tree.
- **Meet in the middle** : Meet in the middle is a technique where the search space is divided into two parts of about equal size. A separate search is performed for both of the parts, and finally the results of the searches are combined. Example `subset sum` can be optimize using this technique


### **1. Subsets (Power Set)**  

**Examples:**  

- [Subsets](https://leetcode.com/problems/subsets/) - Generate all possible subsets of a given set.

  - Always Select the next element, then backtrack and remove the element
  
  ```java
    void backtrack(int[] nums, List<Integer> list, List<List<Integer>> result, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            backtrack(nums, list, result, i + 1);
            list.removeLast();
        }
    }
  ```

- [Subsets II](https://leetcode.com/problems/subsets-ii/) - Generate all possible subsets of a given set, handling duplicates.  

  - Sort the elements and check if there is duplicates by comparing with previous element, then skip the duplicates


### **2. Permutations**  

**Examples:**  

- [Permutations](https://leetcode.com/problems/permutations/) - Generate all possible permutations of a given set of numbers. 

  - Recursive functions always starts with `i = 0` or first element and check duplicates in the list

  ```java
    void backtrack(int[] nums, List<Integer> list, List<List<Integer>> result) {
        if (nums.length == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int num : nums) {
            if (list.contains(num)) continue;

            list.add(num);
            backtrack(nums, list, result);
            list.removeLast();
        }
    }
  ```


- [Permutations II](https://leetcode.com/problems/permutations-ii/) - Generate all unique permutations of a given set, handling duplicates.  

  - Can be used visited array to check duplicates
    ```java
      if (visited[i]) continue;
      if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
    ```


### **3. Combinations**  

**Examples:**  

- [Combinations](https://leetcode.com/problems/combinations/) - Generate all possible combinations of `k` numbers from a given set. 

  - Recursive Subset pattern can be used here, call recursive function with next starting index

  - **Meet in the Middle Optimizations** : 
    
    - For example, suppose that the list is [2,4,5,9] and x = 15. First, we divide the list into A= [2,4] and B= [5,9]. After this, we create lists SA = [0,2,4,6]
  and SB = [0,5,9,14]. In this case, the sum x = 15 is possible to form, because SA contains the sum 6, SB contains the sum 9, and 6 + 9= 15. This corresponds to the solution [2,4,9].


- [Combination Sum](https://leetcode.com/problems/combination-sum/) - Find all unique combinations of numbers that sum up to a target.  


- [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) - Similar to Combination Sum but with each number used at most once.  


- [Combination Sum III](https://leetcode.com/problems/combination-sum-iii/) - Find all valid combinations of `k` numbers that sum to `n`.  


### **4. Word Search**  

**Examples:**  

- [Word Search](https://leetcode.com/problems/word-search/) - Check if a word exists in a grid using backtracking.  

- [Word Search II](https://leetcode.com/problems/word-search-ii/) - Find all words from a dictionary that exist in a grid.  



### **5. Sudoku Solver**  

**Examples:**  

- [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) - Solve a Sudoku puzzle by filling empty cells with valid numbers.  

- [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) - Check if a given Sudoku board configuration is valid.  


### **6. N-Queens**  

**Examples:**  

- [N-Queens](https://leetcode.com/problems/n-queens/) - Place `N` queens on an `N×N` board without attacking each other.  

- [N-Queens II](https://leetcode.com/problems/n-queens-ii/) - Count the number of distinct solutions to the N-Queens problem.  


### **7. Backtracking with String**  

**Examples:**  

- [Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation/) - Generate all possible case variations of a string containing letters.  

- [Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/) - Generate all possible valid IP addresses from a given string.  


---

## Trie

**1. Basic Trie Implementation**

**Examples:**

- [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) - Build a Trie with insert, search, and prefix-check operations.

- [Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/) - Implement a Trie that supports adding words and searching words with `.` as a wildcard.


**2. Word Search and Prefix Matching**

**Examples:**

- [Concatenated Words](https://leetcode.com/problems/concatenated-words/) - Find all words that can be formed by concatenating two or more dictionary words.

- [Replace Words](https://leetcode.com/problems/replace-words/) - Replace words in a sentence with the shortest prefix found in a dictionary.


**3. Autocomplete and Suggestions**

**Examples:**

- [Design Search Autocomplete System](https://leetcode.com/problems/design-search-autocomplete-system/) - Build an autocomplete system that suggests hot sentences based on user input.

- [Search Suggestions System](https://leetcode.com/problems/search-suggestions-system/) - Given a list of products, return lexicographically sorted product suggestions based on a search prefix.


**4. Dictionary and Word Manipulation**

**Examples:**

- [Longest Word in Dictionary](https://leetcode.com/problems/longest-word-in-dictionary/) - Find the longest word that can be built one character at a time using a given list of words.

- [Prefix and Suffix Search](https://leetcode.com/problems/prefix-and-suffix-search/) - Design a data structure that finds words matching a given prefix and suffix.

- [Map Sum Pairs](https://leetcode.com/problems/map-sum-pairs/) - Implement a Trie-based key-value mapping where keys share prefixes.


**5. Bit Manipulation and Trie**

**Examples:**

Here are the descriptions for all four problems in the requested format:

1. [Maximum XOR of Two Numbers in an Array](https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/description/) - Find the maximum XOR of two numbers in an array by comparing all possible pairs.

2. [Maximum XOR with an Element from Array](https://leetcode.com/problems/maximum-xor-with-an-element-from-array/description/)** - Compute the maximum XOR of a given element with any element from an array.

3. [Maximum Strong Pair XOR I](https://leetcode.com/problems/maximum-strong-pair-xor-i/description/) - Determine the maximum XOR value of a strong pair from an array, where a strong pair is defined by specific conditions.

---


## Design


###  1. Cache & Advanced DS Design (High Value)

* [**Insert Delete GetRandom O(1)**](https://leetcode.com/problems/insert-delete-getrandom-o1/) – Design a data structure that supports insert, delete, and get random element in average O(1) time.

* [**Insert Delete GetRandom O(1) – Duplicates allowed**](https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/) – Extend the above to handle duplicates while keeping O(1) operations.

* [**All O\`one Data Structure**](https://leetcode.com/problems/all-oone-data-structure/) – Implement a structure that supports increment, decrement, and retrieving max/min keys in O(1).

* [**LFU Cache**](https://leetcode.com/problems/lfu-cache/) – Design a Least Frequently Used cache with O(1) operations using hash map + doubly linked list.

* [**Dinner Plate Stacks**](https://leetcode.com/problems/dinner-plate-stacks/) – Simulate stacks with capacity constraints, supporting push/pop efficiently with a priority queue.

* [**Design Skiplist**](https://leetcode.com/problems/design-skiplist/) – Implement a probabilistic data structure (skip list) supporting search, insert, and erase in O(log n).

* [**Snapshot Array**](https://leetcode.com/problems/snapshot-array/) – Create an array that supports snapshots (versioning) with efficient get and set operations.


###  2. System / Real-World Inspired Designs (Medium–High Value)


* [**Design Circular Deque**](https://leetcode.com/problems/design-circular-deque/) – Extend circular queue to allow insert/delete at both ends efficiently.

* [**My Calendar I**](https://leetcode.com/problems/my-calendar-i/) – Book intervals in a calendar ensuring no overlaps using binary search tree.

* [**My Calendar II**](https://leetcode.com/problems/my-calendar-ii/) – Extend calendar booking to allow double bookings but prevent triple overlaps.

* [**My Calendar III**](https://leetcode.com/problems/my-calendar-iii/) – Further extend calendar to return the maximum number of concurrent bookings.

* [**Maximum Frequency Stack**](https://leetcode.com/problems/maximum-frequency-stack/) – Implement a stack-like structure that pops the most frequent element first.

* [**Online Stock Span**](https://leetcode.com/problems/online-stock-span/) – Compute stock spans using a monotonic stack for online queries.


###  3. String / Trie Based Designs (Medium Value)

* [**Implement Magic Dictionary**](https://leetcode.com/problems/implement-magic-dictionary/) – Build a dictionary supporting search with one-character modification.

* [**Map Sum Pairs**](https://leetcode.com/problems/map-sum-pairs/) – Implement a map that allows prefix-sum queries using a Trie.

* [**Stream of Characters**](https://leetcode.com/problems/stream-of-characters/) – Design a system that checks if recent characters form any word in a dictionary.

* [**Encrypt and Decrypt Strings**](https://leetcode.com/problems/encrypt-and-decrypt-strings/) – Create a string encryption/decryption system using mapping rules.


---

## Number Theory





---

## Bit Manipulation


### XOR Properties

| Property                 | Meaning                       |
|--------------------------|-------------------------------|
| `a ^ b = c  ⇒ b ^ c = a` | You can reverse XOR           |
| `x ^ 0 = x`              | XOR with 0 returns same value |
| `x ^ x = 0`              | XOR with itself is 0          |


### Bit Shift Tricks

| Operation | Meaning       |
|-----------|---------------|
| `1 << n`  | Equals `2^n`  |
| `a >> 1`  | Divide by 2   |
| `a << 1`  | Multiply by 2 |


### Set Operations Using Bitmask


| Operation | Code Example | Explanation with Example |
| :--- | :--- | :--- |
| **Union** | `A \| B` | Combines elements from both sets. Example: `5 \| 6` (`...00000101 \| ...00000110`) = `7` (`...00000111`). The resulting set is `{0, 1, 2}`. |
| **Intersection** | `A & B` | Finds elements common to both sets. Example: `5 & 6` (`...00000101 & ...00000110`) = `4` (`...00000100`). The resulting set is `{2}`. |
| **Subtraction** | `A & ~B` | Removes elements of B from A. Example: `5 & ~6`. `~6` is `...11111001`. `5 & ...11111001` = `1` (`...00000001`). The resulting set is `{0}`. |
| **Negation** | `~A` | Creates a set of all elements *not* in A (the complement set). Example: `~5` (`~...00000101`) = `...11111010`. |
| **Set bit** | `A \|= (1 << bit)` | Adds a specific element (`bit`) to the set. Example: Add element 3 to set A: `5 \|= (1 << 3)`. `(1 << 3)` is `...00001000`. `5 \|= 8` = `13` (`...00001101`). The new set is `{0, 2, 3}`. |
| **Clear bit** | `A &= ~(1 << bit)` | Removes a specific element (`bit`) from the set. Example: Remove element 2 from set A: `5 &= ~(1 << 2)`. `~(1 << 2)` is `~4` or `...11111011`. `5 &= ~4` = `1` (`...00000001`). The new set is `{0}`. |
| **Test bit** | `(A & (1 << bit)) != 0` | Checks if a specific element (`bit`) exists in the set. Example: Is element 2 in set A? `(5 & (1 << 2)) != 0`. `(5 & 4)` = `4`. Since `4 != 0`, the answer is true. |
| **Extract last bit**| `A & -A` | Isolates the lowest-order `1` bit (the smallest element in the set). Example: `6 & -6`. In two's complement, `-6` is `...11111010`. `6 & -6` (`...00000110 & ...11111010`) = `2` (`...00000010`). This tells you the lowest element is `1` (since $2 = 2^1$). |
| **Remove last bit**| `A & (A - 1)`| Clears the lowest-order `1` bit. Example: `6 & (6 - 1)`. `6 - 1 = 5`. `6 & 5` (`...00000110 & ...00000101`) = `4` (`...00000100`). The new set is `{2}`. |
| **All 1-bits**| `~0` | Represents a universal set containing all possible elements (all bits are `1`). In a 32-bit integer, this is all `1`s. |


---

## Matrix Tricks

**Basic Directions (left, right, top, down)**

- `{0, 1}` :  movement to the right
- `{0, -1}`:  movement to the left
- `{1, 0}` :  movement downwards
- `{-1, 0}`:  movement upwards

**Additional diagonal movements**

- `{1, 1}` :  movement diagonally down and to the right
- `{1, -1}` :  movement diagonally down and to the left
- `{-1, 1}` :  movement diagonally up and to the right
- `{-1, -1}`:  movement diagonally up and to the left

**Matrix Formula**

- Convert `n * m` matrix to an array : 

  ```plaintext

  a[row * m + col] = matrix[row][col] where n = matrix.length and m = matrix[0].length

  ```

- Convert array to `n * m` matrix : 

  ```plaintext

  matrix[i / m][i % m] = a[i] where n = matrix.length and m = matrix[0].length

  ```

- Grid Number = `(row / 3) * 3 + (col / 3)`

---