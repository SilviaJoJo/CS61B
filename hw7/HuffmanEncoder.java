import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        HashMap<Character, Integer> frequencyTable = new HashMap<>();
        for (char c : inputSymbols) {
            if (!frequencyTable.containsKey(c)) {
                frequencyTable.put(c, 1);
            } else {
                int originalFreq = frequencyTable.get(c);
                frequencyTable.put(c, originalFreq + 1);
            }
        }
        return frequencyTable;
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java HuffmanEncoder <input_file>");
            return;
        }
        String inputFile = args[0];
        String outputFile = inputFile + ".huf";
        // read the file as 8 bits symbols
        char[] symbols = FileUtils.readFile(inputFile);
        // build frequency table
        Map<Character, Integer> frequencyTable = buildFrequencyTable(symbols);
        // use frequency table to construct a binary decoding trie
        BinaryTrie binaryTrie = new BinaryTrie(frequencyTable);
        // write the binary decoding trie into the .huf file
        ObjectWriter ow = new ObjectWriter(outputFile);
        ow.writeObject(binaryTrie);
        ow.writeObject(symbols.length);
        // use binary trie to create lookup table for encoding
        Map<Character, BitSequence> lookupTable = binaryTrie.buildLookupTable();
        // create a list of bit-sequences
        List<BitSequence> bss = new LinkedList<>();
        // traverse each symbol and add the corresponding bit-sequence
        for (char symbol : symbols) {
            if (lookupTable.containsKey(symbol)) {
                bss.add(lookupTable.get(symbol));
            }
        }
        // assemble all the bit-sequences into a huge one
        BitSequence assembled = BitSequence.assemble(bss);
        // write the huge bit sequence to the .huf file
        ow.writeObject(assembled);
    }
}
