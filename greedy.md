## 1. **Minimize “Cost” or “Resource” Used**  
- **Earliest Finish Time**  
  - Intervals: pick the one that ends soonest so you preserve the largest “time‑budget” for later.  
- **Smallest Weight**  
  - Packing/Knapsack variants where weights are the limiting factor: pick the item that uses up the least capacity first (only safe when all values are equal).  
- **Tightest Deadline (Least Slack)**  
  - Scheduling with deadlines: pick the job whose deadline minus its processing time is smallest (i.e. the one you can’t delay).

## 2. **Maximize “Gain” or “Value” Gained**  
- **Highest Profit First**  
  - Job sequencing: sort jobs by profit descending, schedule the most lucrative ones earliest.  
- **Largest Value-to-Cost Ratio**  
  - Fractional knapsack: pick the item with the highest value/weight ratio.  
- **Biggest “Jump” or “Savings”**  
  - Huffman coding: always merge the two smallest frequencies because that yields the biggest savings later in code-length.

## 3. **Balance or “Median‑Type” Choices**  
- **Median of Points**  
  - Minimizing sum of absolute deviations on a line—greedy choice is to “move toward the median”.  
- **Balance Sets**  
  - When you want to split into two groups with as-equal-as-possible sums, you might sort descending and then always add the next largest to the currently smaller-sum bucket.

## 4. **“Next Best” via a Priority Queue**  
- **Maintain a Heap of Candidates**  
  - e.g. when selecting K smallest/largest dynamically, or when you’re rounding off multiple choices but want the next best one each time.  
- **Dynamic Greedy**  
  - Sometimes you sort once, but then you push/pop from a heap to decide “who goes next” as new constraints appear.

## 5. **Graph‑Based Local Moves**  
- **Lightest Edge**  
  - Kruskal’s MST: pick the globally smallest edge that doesn’t create a cycle.  
- **Cheapest Augmenting Path**  
  - In some flows or matching problems: you repeatedly pick the cheapest residual path/cycle to augment.

## 6. **Two‑Pointer / Window‑Based Moves**  
- **Shrink or Expand to Keep a Property**  
  - E.g. find the smallest window covering all characters of a pattern: move right to include, then move left to drop extraneous chars.  
- **Pairing Extremes**  
  - E.g. to minimize the maximum pair sum, sort and pair smallest with largest.

## 7. **Local Exchange Perspective**  
- **What Would You Swap?**  
  - Ask: “If I had a non‑greedy first pick in an optimal solution, can I swap in my greedy pick without hurting the result?” That swap tells you what local move to choose.