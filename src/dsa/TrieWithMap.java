package dsa;

import java.util.HashMap;


 class TrieWithMap {

    private final TrieNode root;

    private static class TrieNode {
        HashMap<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }
    }

     public TrieWithMap() {
         root = new TrieNode();
     }

     //-------------------------------Insertion--------------------------------//
    // Inserts an element into the trie.
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        current.isWord = true;
    }

    //-------------------------------Searching--------------------------------//
    // Search an element in the trie.
    public boolean search(String word) {
        TrieNode node = getNode(word);
        return node != null && node.isWord;
    }

    //-------------------------------Starts With --------------------------------//
    // Find starts with substring in trie
    public boolean startsWith(String prefix) {
        return getNode(prefix) != null;
    }

    private TrieNode getNode(String str) {
        TrieNode current = root;
        for (char ch : str.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public static void main(String[] args) {
        TrieWithMap trie = new TrieWithMap();
        trie.insert("cat");
        System.out.println(trie.search("apple")); // false
        System.out.println(trie.startsWith("app")); // false
        System.out.println(trie.search("cat")); // true
        System.out.println(trie.startsWith("ca")); // true
    }
}
