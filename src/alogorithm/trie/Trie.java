package alogorithm.trie;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode dictionary = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (dictionary.children[ch - 'a'] == null) {
                dictionary.children[ch - 'a'] = new TrieNode();
            }
            dictionary = dictionary.children[ch - 'a'];
        }
        dictionary.isWord = true;
    }


    public boolean search(String word) {
        TrieNode dictionary = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (dictionary.children[ch - 'a'] == null) {
                return false;
            }
            dictionary = dictionary.children[ch - 'a'];
        }
        return dictionary.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode dictionary = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (dictionary.children[ch - 'a'] == null) {
                return false;
            }
            dictionary = dictionary.children[ch - 'a'];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cat");
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app"));
    }

}
