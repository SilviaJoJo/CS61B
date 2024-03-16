import java.util.HashMap;

/** @ Source: ChatGPT */
public class Trie {
    public static class TrieNode {
        private boolean isEnd;
        private HashMap<Character, TrieNode> children;

        public TrieNode(Character c) {
            isEnd = false;
            children = new HashMap<>();
        }
    }
    private final TrieNode root;

    public Trie() {
        root = new TrieNode('\0');
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                TrieNode child = new TrieNode(ch);
                node.children.put(ch, child);
            }
            node = node.children.get(ch);
        }
        node.isEnd = true;
    }

    public boolean existsString(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        // make sure we reach an end
        return node.isEnd;
    }

    public boolean existsPrefix(String prefix) {
        if (!prefix.isEmpty()) {
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    return false;
                }
                node = node.children.get(ch);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String dictPath = "words.txt";
        In dict = new In(dictPath);
        String[] dictionary = dict.readAllLines();
        Trie trie = new Trie();
        for (String s : dictionary) {
            trie.insert(s);
        }
        System.out.println(trie.existsString("humane")); // returns true
        System.out.println(trie.existsPrefix("hu")); // returns true
        trie.insert("app");
        System.out.println(trie.existsString("app")); // returns true
    }
}
