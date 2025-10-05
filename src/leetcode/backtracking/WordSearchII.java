package leetcode.backtracking;


import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 212: Word Search II
 *
 * Problem Statement:
 * Given an m x n grid of characters `board` and a list of strings `words`, return all words from the list that can be found in the grid.
 *
 * A word can be formed by letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * Example:
 * Input:
 * board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
 * words = ["oath","pea","eat","rain"]
 *
 * Output: ["eat","oath"]
 *
 * Approach:
 * This problem is a classic application of combining a Trie with a Backtracking (DFS) approach.
 * 1. Build a Trie from the list of `words`. The Trie allows for efficient prefix checking.
 * 2. Iterate through each cell of the `board` and start a DFS from that cell.
 * 3. The DFS function will explore adjacent cells, building a word character by character.
 * 4. At each step, we check if the current path forms a valid prefix in our Trie. If it doesn't, we prune the search by backtracking immediately, which is a massive optimization.
 * 5. If a complete word is found, add it to the results and mark it in the Trie to avoid duplicates.
 */
public class WordSearchII {


    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Store the full word at the end node
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
    public static void main(String[] args) {
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