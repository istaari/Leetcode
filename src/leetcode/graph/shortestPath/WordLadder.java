package leetcode.graph.shortestPath;

import java.util.*;

public class WordLadder {


    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordset = new HashSet<>(wordList);
        if (!wordset.contains(endWord)) return 0;

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

                        if (original == c) continue;
                        wordChar[j] = c;
                        String newWord = String.valueOf(wordChar);

                        if (newWord.equals(endWord)) return level + 1;

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
        if (!wordSet.contains(endWord)) return 0;

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
                        if (c == original) continue;

                        wordChars[i] = c;
                        String newWord = String.valueOf(wordChars);

                        if (endSet.contains(newWord)) return level + 1;

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
