package leetcode.graph.shortestPath.UG;

import java.util.*;

/**
 * 127. Word Ladder
 * https://leetcode.com/problems/word-ladder/
 *
 * A transformation sequence from word beginWord to word endWord using a dictionary
 * wordList is a sequence beginWord -> s1 -> s2 -> ... -> sk such that:
 *   - Every adjacent pair of words differs by a single letter.
 *   - Every si (1 <= i <= k) is in wordList. beginWord does not need to be in wordList.
 *   - sk == endWord
 *
 * Return the number of words in the shortest transformation sequence, or 0 if none exists.
 *
 * Example 1:
 *   Input: beginWord = "hit", endWord = "cog",
 *          wordList = ["hot","dot","dog","lot","log","cog"]
 *   Output: 5  (hit -> hot -> dot -> dog -> cog)
 *
 * Example 2:
 *   Input: beginWord = "hit", endWord = "cog",
 *          wordList = ["hot","dot","dog","lot","log"]
 *   Output: 0  (endWord "cog" is not in wordList)
 *
 * Constraints:
 *   1 <= beginWord.length <= 10
 *   endWord.length == beginWord.length
 *   1 <= wordList.length <= 5000
 *   All words have the same length and consist of lowercase English letters.
 *
 * ---
 * Approach 1: BFS (standard)
 *   Treat each word as a node. Two words are connected if they differ by 1 char.
 *   BFS from beginWord finds the shortest path (fewest transformations).
 *   For each word, try all 26 letter substitutions at each position -> O(26 * L) neighbors.
 *
 * Approach 2: Bidirectional BFS
 *   BFS from both beginWord and endWord simultaneously. Expand the smaller frontier.
 *   When the two frontiers meet, we have the shortest path.
 *   Much faster in practice: O(b^(d/2)) vs O(b^d) where b=branching, d=depth.
 *
 * Time:  O(N * L * 26) where N = wordList size, L = word length
 * Space: O(N * L)
 */
public class WordLadder {

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordset = new HashSet<>(wordList);
        if (!wordset.contains(endWord))
            return 0;

        int level = 1;
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                assert !queue.isEmpty();
                char[] wordChar = queue.poll().toCharArray();

                for (int j = 0; j < wordChar.length; j++) {
                    char original = wordChar[j];
                    for (char c = 'a'; c <= 'z'; c++) {

                        if (original == c)
                            continue;
                        wordChar[j] = c;
                        String newWord = String.valueOf(wordChar);

                        if (newWord.equals(endWord))
                            return level + 1;

                        if (wordset.contains(newWord)) {
                            queue.add(newWord);
                            wordset.remove(newWord);
                        }
                    }
                    wordChar[j] = original;
                }
            }
            level++;
        }
        return 0;
    }

    public static int ladderLengthBiDirectional(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int level = 1;

        while (!beginSet.isEmpty()) {

            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextLevel = new HashSet<>();
            for (String word : beginSet) {
                char[] wordChars = word.toCharArray();

                for (int i = 0; i < wordChars.length; i++) {
                    char original = wordChars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original)
                            continue;

                        wordChars[i] = c;
                        String newWord = String.valueOf(wordChars);

                        if (endSet.contains(newWord))
                            return level + 1;

                        if (wordSet.contains(newWord)) {
                            nextLevel.add(newWord);
                            wordSet.remove(newWord);
                        }
                    }

                    wordChars[i] = original;
                }
            }

            // Move to the next level
            beginSet = nextLevel;
            level++;
        }

        return 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = List.of("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(ladderLength(beginWord, endWord, wordList));
        System.out.println(ladderLengthBiDirectional(beginWord, endWord, wordList));
    }

}
