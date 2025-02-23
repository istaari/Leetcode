
# Algorithms and Data Structures


## Formulas

- Number of subarrays and substrings = `n(n+1) / 2`
- Number of subsequences = `2^n - 1`
- Number of subsets = `2^n`
- Number of contiguous subarrays with size `k` = `n - k + 1`


## Sorting

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

- Window Size = `j - 1 + 1`
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

- Greedy problem exhibits `Greedy Choice and Optimal Substructure` properties.

**1. Interval Scheduling**

**Examples:**

- [Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Merge overlapping intervals.

  - Sort intervals by `start time`, put first interval in list, compare other intervals to last interval from list for overlapp, then remove-merge or add in list

- [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals/) - Minimum number of intervals to remove to make the remaining intervals non-overlapping.

  -  Sort intervals by `start time`, Take the `End of First Interval` and compare the `Next Start of Interval` for overlapp
  -  If overlapps then update the `End = Math.min(intervals[i][1], End)` min end time of both intervals, If does not overlaps `End = intervals[i][1]`


- [Insert Interval](https://leetcode.com/problems/insert-interval/) - Insert a new interval into a list of non-overlapping intervals.

  - Todo

- [Meeting Rooms I](https://leetcode.com/problems/meeting-rooms/) - Given intervals, Determine if a person could attend all meeting

  -  Sort intervals by `start time`, Take the `End of 1st Interval` and compare the `next Start of Interval` for overlap then return true or false

- [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Find the minimum number of meeting rooms required to hold all meetings

  - ```java
        int minMeetingRooms(int[][] intervals) {
            // Sort the intervals by their start times
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            minHeap.add(intervals[0][1]); // first meeting's end time
    
            for (int i = 1; i < intervals.length; i++) {
                // If there is no overlap
                if (intervals[i][0] >= minHeap.peek()) {
                    minHeap.poll();
                }
    
                // update current meeting's end time in heap
                minHeap.add(intervals[i][1]);
            }
            // The size of the heap tells us the minimum rooms required
            return minHeap.size();
        }
    ```


**2. Scheduling Problems**

- Scheduling a task optimally with with gap of n intervals.

**Examples:**

- [Task Scheduler](https://leetcode.com/problems/task-scheduler/) - Greedily assign tasks while considering cooldown periods.

- [Reorganize String](https://leetcode.com/problems/reorganize-string/description/)

  - Use priority queue to store the character and its frequency(High - Low), then pop the top two elements and add to the result, then add back to the queue if frequency is not zero

  - `Another` approach first check solution exits or not ` Does not exist, maxFrequency >  (n + 1) / 2`  and create max heap and distribute the characters in alternate positions
    
    ```java
      String reorganizeStringSort(String s) {
          HashMap<Character, Integer> map = new HashMap<>();
          for (char c : s.toCharArray()) {
              map.put(c, freqMap.getOrDefault(c, 0) + 1);
          }

          PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
          maxHeap.addAll(map.keySet());
          // Solution does not exist,  maxFrequency >  (n + 1) / 2
          if (map.get(maxHeap.peek()) > (s.length() + 1) / 2) {
              return "";
          }

          char[] result = new char[s.length()];
          int index = 0;
          while (!maxHeap.isEmpty()) {
              char c = maxHeap.poll();
              // First fills the even index
              // Then it fills the odd index
              for (int j = 0; j < map.get(c); j++) {
                  if (index >= s.length()) index = 1; // This will only execute once
                  result[index] = c;
                  index += 2;
              }
          }

          return new String(result);
      }
    ```

**3. Greedy for Arrays**

**Examples:**

- [Jump Game](https://leetcode.com/problems/jump-game/) - Determine if you can reach the last index by making greedy jumps.

  - Calculate max jump so far from current index, from privious index
     
     ```java
        boolean canJump(int[] nums) {
            int far = 0;
            int n = nums.length;
    
            for (int i = 0; i < n; i++) {
                if (i > far) return false;
                far = Math.max(i + nums[i], far);
            }
    
            return true;   
        }
     ```

- [Jump Game II](https://leetcode.com/problems/jump-game-ii/) - Minimum number of jumps needed to reach the last index.

   -  When you reached prev jump, then increament the count
  
       ```java
          public int jump(int[] nums) {
              int far = 0;
              int reached = 0;
              int count = 0;
              int n = nums.length;
      
              for (int i = 0; i < n - 1; i++) {
                  far = Math.max(i + nums[i], far);
                  if (i == reached) {
                      count++;
                      reached = far;
                  }
              }
      
              return count;
          }
        ```

- [Wiggle Subsequence](https://leetcode.com/problems/wiggle-subsequence/description/)

    - Greedy DP
    - Count peak and valley, `peak = valley + 1` , `valley = peak + 1` when you encounter peak and valley twice in a row there will be no change

    ```java
    int wiggleMaxLength(int[] nums) {
        int size = nums.length;
        int peak = 1;
        int valley = 1;
        for (int i = 1; i < size; ++i) {
            if (nums[i] > nums[i - 1]) {
                peak = valley + 1;
            }
            else if (nums[i] < nums[i - 1]) {
                valley = peak + 1;
            }
        }
        return Math.max(peak, valley);
    }
    ``` 
- [Car Pooling](https://leetcode.com/problems/car-pooling/description/)

   - Uses the Sweep Line Algorithm, commonly used for interval-based problems, like `meeting rooms scheduling, car pooling, and skyline problems`

   - Convert each trip into two events `Pick up and Drop-off event` then sort by location and capacity

     ```java
        boolean carPooling(int[][] trips, int capacity) {
            List<int[]> location = new ArrayList<>();

            // Convert trips into pickup/drop-off events
            for (int[] trip : trips) {
                location.add(new int[]{trip[1], trip[0]});  // Pickup event (start location, numPassengers)
                location.add(new int[]{trip[2], -trip[0]}); // Drop-off event (end location, -numPassengers)
            }

            Comparator<int[]> comparator = (a, b) -> {
                if (a[0] == b[0]) return a[1] - b[1]; // Pickup before drop-off
                return a[0] - b[0]; // Sort by location
            };

            location.sort(comparator);
            int currentCapacity = 0;
            for (int[] trip : location) {
                currentCapacity += trip[1];

                if (currentCapacity > capacity) return false;
            }

            return true;
        }
     ```
  
  - [Cinema Seat Allocation](https://leetcode.com/problems/cinema-seat-allocation/description/) 

     -  Problem involves `set or row comparison`, use bitmask to represent set and compare using `&` operator

  - [Group the People Given the Group Size They Belong To](https://leetcode.com/problems/group-the-people-given-the-group-size-they-belong-to/description/)

     - Create a list based on size and add the elements to the list 

     ```java
        List<List<Integer>> groupThePeople(int[] groupSizes) {
            List<List<Integer>> result = new ArrayList<>();
            Map<Integer, List<Integer>> map = new HashMap<>();

            for (int i = 0; i < groupSizes.length; i++) {
                List<Integer> tempList = map.computeIfAbsent(groupSizes[i], (key) -> new ArrayList<>());
                tempList.add(i);

                if (tempList.size() == groupSizes[i]) {
                    result.add(map.get(groupSizes[i]));
                    map.put(groupSizes[i], new ArrayList<>());
                }
            }

          return result;
      }
     ``` 

- [Partition Labels](https://leetcode.com/problems/partition-labels/) - Partition a string into as many parts as possible such that each letter appears in only one part.

   - Store the last index of each character, then iterate the string and find the last index of each character, if it is equal to current index then partition the string

- [Candy](https://leetcode.com/problems/candy/) - Distribute candies to children such that each child has at least one candy and children with higher ratings get more candies.

   - Traverse from left to right, check if left neighbour is greater than current element, If yes then add prev candies plus 1
   - Traverse from right to left, check if right neighbour is greater than current element, If yes then add max of current candy or next candies plus 1
   - Can be done in one pass using [Up-Down-Peak Method](https://leetcode.com/problems/candy/solutions/4037646/99-20-greedy-two-one-pass/)

- [Minimum Cost to Hire K Workers](https://leetcode.com/problems/minimum-cost-to-hire-k-workers/)

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


**1. Subsets (Power Set)**  

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


**2. Permutations**  

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


**3. Combinations**  

**Examples:**  

- [Combinations](https://leetcode.com/problems/combinations/) - Generate all possible combinations of `k` numbers from a given set. 

  - Recursive Subset pattern can be used here, call recursive function with next starting index


- [Combination Sum](https://leetcode.com/problems/combination-sum/) - Find all unique combinations of numbers that sum up to a target.  


- [Combination Sum II](https://leetcode.com/problems/combination-sum-ii/) - Similar to Combination Sum but with each number used at most once.  


- [Combination Sum III](https://leetcode.com/problems/combination-sum-iii/) - Find all valid combinations of `k` numbers that sum to `n`.  


**4. Word Search**  

**Examples:**  

- [Word Search](https://leetcode.com/problems/word-search/) - Check if a word exists in a grid using backtracking.  

- [Word Search II](https://leetcode.com/problems/word-search-ii/) - Find all words from a dictionary that exist in a grid.  



**5. Sudoku Solver**  

**Examples:**  

- [Sudoku Solver](https://leetcode.com/problems/sudoku-solver/) - Solve a Sudoku puzzle by filling empty cells with valid numbers.  

- [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) - Check if a given Sudoku board configuration is valid.  


**6. N-Queens**  

**Examples:**  

- [N-Queens](https://leetcode.com/problems/n-queens/) - Place `N` queens on an `N×N` board without attacking each other.  

- [N-Queens II](https://leetcode.com/problems/n-queens-ii/) - Count the number of distinct solutions to the N-Queens problem.  


**7. Backtracking with String**  

**Examples:**  

- [Letter Case Permutation](https://leetcode.com/problems/letter-case-permutation/) - Generate all possible case variations of a string containing letters.  

- [Restore IP Addresses](https://leetcode.com/problems/restore-ip-addresses/) - Generate all possible valid IP addresses from a given string.  


---
## Dynamic Programming

**1. Fibonacci Variations**

**Examples:** 

- [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) - Count distinct ways to reach the top by taking 1
  or 2 steps.

- [Min Cost Climbing Stairs](https://leetcode.com/problems/min-cost-climbing-stairs/) - Find the minimum cost to reach
  the top when each step has a cost.

- [House Robber](https://leetcode.com/problems/house-robber/) - Maximize money stolen without robbing adjacent houses.

- [House Robber II](https://leetcode.com/problems/house-robber-ii/) - House Robber problem with houses arranged in a
  circle.

- [Decode Ways](https://leetcode.com/problems/decode-ways/) - Count the number of ways to decode a string of digits.

**2. Grids (Path Problems)**

**Examples:** 

- [Unique Paths](https://leetcode.com/problems/unique-paths/) - Count unique paths from top-left to bottom-right in an
  empty grid.

- [Unique Paths II](https://leetcode.com/problems/unique-paths-ii/) - Count unique paths in a grid with obstacles.

- [Minimum Path Sum](https://leetcode.com/problems/minimum-path-sum/) - Find the minimum sum path from top-left to
  bottom-right.

- [Minimum Falling Path Sum](https://leetcode.com/problems/find-the-safest-path-in-a-grid/) - Find the minimum sum
  falling path in a matrix.

- [Triangle](https://leetcode.com/problems/triangle/description/) - Find the minimum path sum from top to bottom in a
  triangle.



 **3. Subsequences(Knapsack, Subset, Coin Change, Partition)** 

 **Examples:**

- [Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/)  - Check if subset exists with some target

  - Check if sum is even, then divide the sum by 2, then check if subset sum is equal to half of sum
  - Its a `0/1 Knapsack Problem`

  - ```java
      // Iterative Solution
      boolean iterative(int[] nums, int targetSum) {
          boolean[][] dp = new boolean[nums.length + 1][targetSum + 1];

          for (int i = 0; i <= nums.length; i++)
              dp[i][0] = true; // For any set of numbers, you can always form a sum of 0 by taking no elements at all

          for (int i = 1; i <= nums.length; i++) {
              for (int j = 1; j <= targetSum; j++) {

                  if (nums[i - 1] <= j) {
                      // dp[i - 1][j] - Can we form excluding current item
                      // dp[i - 1][j - nums[i - 1]] - Can we form excluding current item and remaining capacity
                      dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                  } else {
                      dp[i][j] = dp[i - 1][j];
                  }
              }
          }
          return dp[nums.length][targetSum];
      }
    ``` 
- [Coin Change](https://leetcode.com/problems/coin-change/)  - Find minimum number of coins to make a sum, `same coins can be used more than once`

  - Recurrence Relation: `dp[i][j] = min(dp[i-1][j], dp[i][j-coins[i-1]] + 1)`
  - Base Case: `dp[0][j] = inf, dp[i][0] = 0`  
  - Variations **Unbounded Knapsack problem**
  - Exclude the current item: `dp[i-1][j]`
  - Include the current item: `dp[i][j-coins[i-1]] + 1`
  
  ```java
      public static int iterative(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 0;
        }

        for (int j = 1; j <= amount; j++) {
            dp[0][j] = amount + 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {

                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length][amount] > amount ? -1 : dp[coins.length][amount];
    }
  ```


- [Coin Change 2](https://leetcode.com/problems/coin-change-ii/description/)  - Find number of ways to make a sum, `same coins can be used more than once`

  - Recurrence Relation: `dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]` 
  - Base Case : ` dp[i][0] = 1`
  - Variations **Unbounded Knapsack problem**
  - Exclude the current item: `dp[i-1][j]`
  - Include the current item: `dp[i][j-coins[i-1]]`


  ```java
    public static int Iterative(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];

        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {

                if (coins[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length][amount];
    }
  ```
  
- [Combination Sum IV](https://leetcode.com/problems/combination-sum-iv/) - Number of ways(no. of Subsequence) to make a target sum, `same number can be used more than once`

- [Target Sum](https://leetcode.com/problems/target-sum/) - Number of ways to make a target sum, using `+` and `-` operator, Same number can be used more than once

- [Rod Cutting](https://leetcode.com/discuss/interview-question/4889192/4-Solutions-or-Top-DownBottom-Up-or-Best-Explanation-Using-Comments-or-C%2B%2B-Code) - Maximize profit by cutting a rod into smaller pieces

- [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)  - Length of longest increasing subsequence

  - Recurrence Relation: `dp[i] = max(dp[i], dp[j] + 1)`
  - Base Case : ` dp[i] = 1`
     
  - Recursion logic for each previous values check, if there is a increasing subsequence

  ```java
    // Iterative Solution
    public static int iterative(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[n - 1];
    }
  ```


 **4. String(Subsequence, Substring, Edit Distance, Wildcard)** 

 **Examples:**

- [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/) - Longest common subsequence between two strings

   ```java
      public static int iterative(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
   ```

- [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)  - Longest palindromic substring in a string


   ```java

     public static String iterative(String s) {
        String result = "";
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;

                    if (i - j + 1 > result.length()) {
                        result = s.substring(j, Math.min(i + 1, s.length()));
                    }
                }
            }
        }

        return result;
    }

   ```

- [Longest Palindromic Subsequence](https://leetcode.com/problems/longest-palindromic-subsequence/)  - Longest palindromic subsequence in a string

  ```java
    public static int iterative(String s) {
        int n = s.length();

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = 1;

        for (int j = 0; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }
  ```


- [Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/)  - Number of distinct subsequences of `s` which equals `t`

  ```java
     public static int iterative(String s, String t) {
        int m = t.length(); // Target
        int n = s.length(); // Source
        int[][] dp = new int[m + 1][n + 1];

        for (int j = 0; j <= n; j++) {
            // The first row is set to 1 because there's one way to match an empty string t in any prefix of s: by deleting all characters.
            dp[0][j] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[t.length()][s.length()];
    }
  ```

- [Interleaving String](https://leetcode.com/problems/interleaving-string/)  - find target string s2 by interleaving substring of s1 and s2

  ```java

    public static boolean iterative(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length();

        if (n + m != s3.length()) return false;

        boolean[][] dp = new boolean[n + 1][m + 1];

        dp[0][0] = true;

        // Fill first column (considering only s1)
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // Fill first row (considering only s2)
        for (int j = 1; j <= m; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int k = i + j - 1;
                dp[i][j] = (s1.charAt(i - 1) == s3.charAt(k) && dp[i - 1][j]) || (s2.charAt(j - 1) == s3.charAt(k) && dp[i][j - 1]);
            }
        }

        return dp[n][m];
    }

  ```

- [Edit Distance (Levenshtein Distance)](https://leetcode.com/problems/edit-distance/) - minimum number of operations required to convert word1 to word2, `insert, delete, replace`

- [Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching/)

- [Wildcard Matching](https://leetcode.com/problems/wildcard-matching/)  


**5. Optimizations(State Machines, Maximum Subarray )** 

**Examples:**

**1. Maximum Subarray/Contiguous Subarray Problems**

- [Maximum Subarray (Kadane’s Algorithm)](https://leetcode.com/problems/maximum-subarray/) - Find the largest sum of a contiguous subarray. 
 
  - Can be solved using `Kadane's Algorithm`
  
- [Maximum Sum Circular Subarray](https://leetcode.com/problems/maximum-sum-circular-subarray/) - Find the maximum sum of a circular subarray.  

  - Can be solved using `Kadane's Algorithm`

- [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray/) - Find the largest product of a contiguous subarray.

  ```java
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxProd = nums[0]; // Maximum product so far
        int minProd = nums[0]; // Minimum product so far
        int result = nums[0];  // Final result


        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = maxProd;
                maxProd = minProd;
                minProd = temp;
            }

            // Update maxProd and minProd
            maxProd = Math.max(nums[i], maxProd * nums[i]);
            minProd = Math.min(nums[i], minProd * nums[i]);

            result = Math.max(result, maxProd);
        }

        return result;
    }
  ```

- [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) 

- [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)  

- [Best Time to Buy and Sell Stock III](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/) 

   ```java
     public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int minPrice1 = Integer.MAX_VALUE;
        int minPrice2 = Integer.MAX_VALUE;
        int profit1 = 0;
        int profit2 = 0;

        for (int price : prices) {
            minPrice1 = Math.min(minPrice1, price);
            profit1 = Math.max(profit1, price - minPrice1);

            minPrice2 = Math.min(minPrice2, price - profit1);
            profit2 = Math.max(profit2, price - minPrice2);
        }

        return profit2;
    }
   ```

- [Best Time to Buy and Sell Stock with Cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/) 

  ```java
      public static int maxProfit(int[] prices) {
          if (prices == null || prices.length == 0) return 0;

          int n = prices.length;

          int hold = -prices[0];
          int sold = 0;
          int cooldown = 0;

          for (int i = 1; i < n; i++) {
              int prevHold = hold;
              hold = Math.max(hold, cooldown - prices[i]);
              cooldown = Math.max(cooldown, sold);
              sold = prevHold + prices[i];
          }

          return Math.max(sold, cooldown);
      }
  ```

- [Best Time to Buy and Sell Stock IV](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/)  

  ```java

    public static int maxProfit(int k, int[] prices) {
        int[] buy = new int[k + 1], sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }

  ```
 
 **6. Interval DP(MCM)**  

 **Examples:**

  - [Minimum Cost to Cut a Stick](https://leetcode.com/problems/minimum-cost-to-cut-a-stick/description/)

  - [Burst Balloons](https://leetcode.com/problems/burst-balloons/description/)


**7. Bitmasking and Partitioning**  

**Examples:**

- [Palindrome Partitioning 2](https://leetcode.com/problems/palindrome-partitioning-ii/) - Partition a string into the minimum number of palindromic substrings. 

- [Word Break](https://leetcode.com/problems/word-break/) - Check if a string can be segmented into a sequence of dictionary words.  

- [Partition to K Equal Sum Subsets](https://leetcode.com/problems/partition-to-k-equal-sum-subsets/)  - Partition a set of numbers into `k` subsets where each subset has the same sum

- [Word Break II](https://leetcode.com/problems/word-break-ii/)  - Break a sentence into words using a dictionary of words


**8. Game Theory(Minimax)**

- [Stone Game](https://leetcode.com/problems/stone-game/) - Two players take turns removing stones from piles. Determine if the first player can win. 

- [Predict the Winner](https://leetcode.com/problems/predict-the-winner/) - Determine if the first player can guarantee a win with optimal moves.  

---

## Graph

**Traversal & Basic Operations**

**1. DFS and BFS Traversal Problems**

**Examples:**

- [Flood Fill](https://leetcode.com/problems/flood-fill/) - Fill a connected region in a grid starting from a given point.

  - In Flood Fill, we change the color of a group of connected cells starting from a given cell.

- [Number of Islands](https://leetcode.com/problems/number-of-islands/) - Count the number of islands in a 2D grid of water and land.

  - In Number of Islands, we count groups of connected '1's (land) and change them to '0' to mark them as visited.

  - The problem can be represented count connected components in a graph

- [Clone Graph](https://leetcode.com/problems/clone-graph/description/)

- [Get Watched Videos by Your Friends](https://leetcode.com/problems/get-watched-videos-by-your-friends/) - Find the most popular videos watched by your friends in a social network.

- [Evaluate Division](https://leetcode.com/problems/evaluate-division/) - Evaluate the result of division based on a set of equations.

- [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/) - Reconstruct a travel itinerary based on given flight tickets.


**2. Connected Components Problems**

- `BFS`, `DFS`, `Union Find` can be used to find connected components in a graph

- `Kosaraju's Algorithm` is a well-known algorithm for finding Strongly Connected Components (SCCs) in a directed graph

**Examples:**

- [Number of Provinces](https://leetcode.com/problems/number-of-provinces/) - Count the number of connected provinces in a graph.

- [Number of Operations to Make Network Connected](https://leetcode.com/problems/number-of-operations-to-make-network-connected/) - Find the minimum number of operations to make the network fully connected.

- [Accounts Merge](https://leetcode.com/problems/accounts-merge/) - Merge accounts with common email addresses into a single account.

- [Critical Connections in a Network](https://leetcode.com/problems/critical-connections-in-a-network/) - Identify the critical connections in a network that, if removed, would increase the number of connected components.

- [Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/) - Find the maximum number of stones that can be removed in a grid.


**3. Cycle Detection in Graph**

- **Cycle Detection in an Undirected Graph**

  - A single edge between two vertices (`A <--> B`) **does not form a cycle** unless there is a self-loop (an edge from `A` to `A` or `B` to `B`).

  - A **cycle in an undirected graph must involve at least 3 vertices** (except when self-loops exist).

  - **Techniques to detect cycles:**
    - **DFS (Depth-First Search) with Parent Tracking**: If a visited node is encountered again, and it is not the parent of the current node, a cycle exists.
    
    - **Union-Find (Disjoint Set Union)**: Useful for detecting cycles in a graph given as edge lists.

- **Cycle Detection in a Directed Graph**

  - A cycle in a directed graph can exist with just 2 vertices (`A -> B -> A`).

  - **Techniques to detect cycles:**

    - **DFS with Recursion Stack (Back Edge Detection)**: If a node is visited again while still in the recursion stack, a cycle is detected.
    
    - **Topological Sorting (Kahn's Algorithm - BFS)**: If the graph contains a cycle, it is **not possible** to get a valid topological order.

- **Detecting Negative Weight Cycles**

  - **Bellman-Ford Algorithm** is used to detect negative weight cycles in weighted graphs.

**Examples:**

- [Redundant Connection](https://leetcode.com/problems/redundant-connection/description/) - Find the redundant connection in a graph that results in a cycle.


**4. Topological Sort & Directed Acyclic Graphs (DAG)**

- Kahn’s Algorithm(Specific Topological Sort Algorithm) 

**Examples:**

- [Course Schedule](https://leetcode.com/problems/course-schedule/) - Determine if it's possible to finish all courses given the prerequisite constraints.

- [Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) - Find the order of courses to take to finish all courses given the prerequisite constraints.

- [Sequence Reconstruction](https://leetcode.com/problems/sequence-reconstruction/) - Reconstruct the original sequence from a set of sequences, ensuring that they are in the correct order.

- [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/solution/) - Determine the order of letters in an alien language based on a list of words.

- [Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) - Find the longest increasing path in a matrix.


**5. Union Find**

**Examples:**

- [Largest Component Size by Common Factor](https://leetcode.com/problems/largest-component-size-by-common-factor/) - Find the largest connected component of nodes that share a common factor.

- [Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/) - Find the maximum number of stones that can be removed in a grid using Union Find.

**6. Minimum Spanning Tree (MST)**

- **Kruskal's Algorithm**  

  - Uses edges, sorts them, and adds them one by one to form the MST

- **Prim's Algorithm** 

  - Uses nodes, expanding the MST from a starting node

**Examples:**

- [Connecting Cities With Minimum Cost](https://leetcode.com/problems/connecting-cities-with-minimum-cost/) - Connect all cities with the minimum cost, ensuring no cycles and a minimum spanning tree.

- [Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) - Connect all points with the minimum cost, ensuring all points are part of a minimum spanning tree.


**7. Shortest Path Algorithms**

**`BFS(Unweighted graph)`**

**Examples:**

- [Word Ladder I](https://leetcode.com/problems/word-ladder/) - Find the shortest transformation sequence from one word to another using a dictionary.

  - Problem can be represented as `unweighted and directed graph`, Use `BFS` to find the shortest path between two nodes

    ```
    graph = {
        "hit": ["hot"],
        "hot": ["hit", "dot", "lot"],
        "dot": ["hot", "dog"],
        "lot": ["hot", "log"],
        "dog": ["dot", "cog"],
        "log": ["lot", "cog"],
        "cog": ["dog", "log"]
    }
    ```

  - Transform each char from `a-z` and check if it is present in the dictionary to find the to find the shortest path from beginWord to endWord
  - We can use Bidirectional BFS to optimize the solution

- [Word Ladder II](https://leetcode.com/problems/word-ladder-ii/) - Find all the shortest transformation sequences from one word to another.

  - Use BFS from `beginWord` to `endWord` to construct the reverse graph
    ```
      graph = {
        "hot": ["hit"],
        "dot": ["hot"],
        "lot": ["hot"],
        "dog": ["dot"],
        "log": ["lot"],
        "cog": ["dog", "log"]
        }
    ```
  - Use backtracking and DFS to find all the shortest paths from `endWord` to `beginWord`

- [Rotating Oranges](https://leetcode.com/problems/rotting-oranges/description/)  

- [Cut Off Trees for Golf Event](https://leetcode.com/problems/cut-off-trees-for-golf-event/) - Minimize the number of steps required to cut off trees in a golf course.

**`Dijkstra's Algorithm(Weighted graph with positive weights)`** 

**Examples:**

- [Path With Maximum Minimum Value](https://leetcode.com/problems/path-with-maximum-minimum-value/) - Find the path in a graph where the minimum value on the path is maximized.

- [Network Delay Time](https://leetcode.com/problems/network-delay-time/) - Calculate the time it takes for all nodes to receive a signal from a source node.

- [Path with Maximum Probability](https://leetcode.com/problems/path-with-maximum-probability/) - Find the path with the highest probability in a graph.

- [Path With Minimum Effort](https://leetcode.com/problems/path-with-minimum-effort/) - Find the path with the smallest possible maximum effort in a 2D grid.

- [Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) - Find the cheapest price for flights within a given number of stops.


**`Floyd-Warshall Algorithm(Weighted graph with negative weights)`**

**Examples:**

- [Find the City With the Smallest Number of Neighbors at a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/) - Find the city with the smallest number of neighboring cities at or below a certain distance threshold.

- [Network Delay Time](https://leetcode.com/problems/network-delay-time/) - Calculate the time it takes for all nodes to receive a signal from a source node, considering all pairwise shortest paths.


**`Bellman-Ford Algorithm(Weighted graph with negative weights)`**

**Examples:**

- [Network Delay Time](https://leetcode.com/problems/network-delay-time/) - Calculate the time it takes for all nodes to receive a signal from a source node, considering edge weights in the graph.


**8. Graph Coloring**

  - Solves the problem of assigning colors to vertices in a graph such that no two adjacent vertices share the same color.

**9 .Connectivity and Bridges** 

  - Identifies whether a graph is connected and finds critical edges (bridges) whose removal would disconnect the graph

**10. Flow and Matching Problems**

  - Focuses on finding optimal flows through a network or perfect matchings in bipartite graphs.

---

## Trie

**1. Basic Trie Implementation**

**Examples:**

- [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/) - Build a Trie with insert, search, and prefix-check operations.

- [Design Add and Search Words Data Structure](https://leetcode.com/problems/design-add-and-search-words-data-structure/) - Implement a Trie that supports adding words and searching words with `.` as a wildcard.


**2. Word Search and Prefix Matching**

**Examples:**

- [Word Search II](https://leetcode.com/problems/word-search-ii/) - Find all valid words in a grid using a word dictionary (Trie + DFS).

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

---


## Bit Manipulation



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

- Convert `n * m` matrix to an array : `a[row * m + col] = matrix[row][col]` where `n = matrix.length` and `m = matrix[0].length`

- Convert array to `n * m` matrix : `matrix[i / m][i % m] = a[i]` where `n = matrix.length` and `m = matrix[0].length`

- Grid Number = `(row / 3) * 3 + (col / 3)`

---