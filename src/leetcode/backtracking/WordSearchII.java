package leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
 * LC 212 - Word Search II
 *
 * Given an m x n board of characters and a list of strings words, return all
 * words from the list that can be found in the board.
 *
 * A word must be constructed from letters of sequentially adjacent cells
 * (horizontally or vertically). The same cell may not be used more than once per word.
 *
 * Example:
 *   Input:  board=[["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
 *           words=["oath","pea","eat","rain"]
 *   Output: ["eat","oath"]
 *
 * Constraints:
 *   m == board.length,  n == board[i].length
 *   1 <= m, n <= 12
 *   board[i][j] is a lowercase English letter.
 *   1 <= words.length <= 3 * 10^4
 *   1 <= words[i].length <= 10
 *
 * Approach: Trie + DFS backtracking.
 *   1. Build a Trie from wordDict — allows prefix pruning during DFS.
 *   2. From every cell, DFS in 4 directions matching characters in the Trie.
 *   3. If current char not in Trie child → prune entire branch immediately.
 *   4. If Trie node has a word → found it; set node.word=null to avoid duplicates.
 *   5. Mark cell '#' before recursing, restore after (backtrack).
 * Time: O(m*n * 4 * 3^(L-1)) where L = max word length   Space: O(W*L) for Trie
 */
public class WordSearchII {


    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Store the full word at the end node
    }

    /**
     * Helper method to build the Trie from the dictionary of words.
     */
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                int index = c - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new TrieNode();
                }
                node = node.children[index];
            }
            node.word = w; // Store the complete word at the final node
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> results = new ArrayList<>();
        TrieNode root = buildTrie(words);

        // Iterate through every cell on the board to start the search
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                backtrack(board, r, c, root, results);
            }
        }

        return results;
    }

    void backtrack(char[][] board, int r, int c, TrieNode parentNode, List<String> results) {
        // 1. Constraint Check (Boundary & Visited):
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || board[r][c] == '#') {
            return; // Stop if out of bounds or already visited.
        }

        char ch = board[r][c];
        TrieNode currentNode = parentNode.children[ch - 'a'];

        // 2. Trie Pruning: Is the current path a valid prefix?
        if (currentNode == null) {
            return; // The path "prefix + ch" does not exist in the Trie. Prune this entire branch.
        }

        // 3. Found a Word? (Goal Condition)
        if (currentNode.word != null) {
            results.add(currentNode.word);
            // CRITICAL: De-duplicate to avoid finding the same word from different paths.
            // By setting the word to null, we ensure it's not added again.
            currentNode.word = null;
        }

        // 4. Choose & Explore: Mark the cell and recurse in all directions.
        board[r][c] = '#'; // Mark as visited.

        backtrack(board, r + 1, c, currentNode, results); // Down
        backtrack(board, r - 1, c, currentNode, results); // Up
        backtrack(board, r, c + 1, currentNode, results); // Right
        backtrack(board, r, c - 1, currentNode, results); // Left

        // 5. Unchoose (Backtrack): Revert the cell to its original state.
        board[r][c] = ch;
    }



    // Main method for testing
    static void main(String[] args) {
        WordSearchII solver = new WordSearchII();
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> foundWords = solver.findWords(board, words);
        System.out.println("Found words: " + foundWords); // Expected output: [oath, eat] or [eat, oath]
    }
}