import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BinaryTrie implements Serializable {
    private static class BinaryTrieNode implements Comparable<BinaryTrieNode>, Serializable {
        // added "Serializable" here, otherwise cannot be written
        private final Character character;
        private final int freq;
        private final BinaryTrieNode left;
        private final BinaryTrieNode right;
        public BinaryTrieNode(Character character, int freq,
                              BinaryTrieNode left, BinaryTrieNode right) {
            this.character = character;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        private boolean isLeaf() {
            assert ((left == null && right == null) || (left != null && right != null));
            return left == null;
        }
        @Override
        public int compareTo(BinaryTrieNode other) {
            return this.freq - other.freq;
        }
    }
    private final BinaryTrieNode node;
    private static int totalNodes;
    private static final HashMap<Character, BitSequence> TABLE = new HashMap<>();
    // constructor! not a method
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        // insert all characters into a priority queue
        PriorityQueue<BinaryTrieNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            Character character = entry.getKey();
            Integer frequency = entry.getValue();
            pq.add(new BinaryTrieNode(character, frequency, null, null));
        }
        totalNodes = pq.size();
        // merge two smallest trees until reach the root
        while (pq.size() >= 2) {
            BinaryTrieNode left = pq.poll();
            BinaryTrieNode right = pq.poll();
            BinaryTrieNode merged = new BinaryTrieNode('\0', left.freq + right.freq, left, right);
            pq.add(merged);
        }
        // out of loop, there is only one item in the priority queue
        node = pq.poll();
    }
    public static int getTotalNodes() {
        return totalNodes;
    }
    public Match longestPrefixMatch(BitSequence querySequence) {
        int currIndex = 0;
        BitSequence bs = new BitSequence();
        BinaryTrieNode currNode = node;
        while (currNode != null) {
            if (currNode.isLeaf()) {
                return new Match(bs, currNode.character);
            }
            int currBit = querySequence.bitAt(currIndex);
            bs = bs.appended(currBit);
            if (currBit == 0) {
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }
            currIndex++;
        }
        return null; // in case of no match at all
    }
    public Map<Character, BitSequence> buildLookupTable() {
        BitSequence bs = new BitSequence();
        tableHelper(bs, node);
        return TABLE;
    }
    private static void tableHelper(BitSequence preBS, BinaryTrieNode currNode) {
        if (currNode.isLeaf()) {
            TABLE.put(currNode.character, preBS);
        } else {
            tableHelper(preBS.appended(0), currNode.left);
            tableHelper(preBS.appended(1), currNode.right);
        }
    }
}
