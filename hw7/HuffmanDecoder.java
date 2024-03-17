public class HuffmanDecoder {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java HuffmanEncoder <input_file> <output_file>");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];
        // Read the Huffman coding trie.
        ObjectReader or = new ObjectReader(inputFile);
        BinaryTrie binaryTrie = (BinaryTrie) or.readObject();
        int totalSymbols = (Integer) or.readObject();
        // Read the massive bit sequence corresponding to the original txt.
        BitSequence assembled = (BitSequence) or.readObject();
        // Repeat until there are no more symbols:
        char[] symbols = new char[totalSymbols];
        for (int i = 0; i < totalSymbols; i++) {
            // Perform a longest prefix match on the massive sequence.
            Match match = binaryTrie.longestPrefixMatch(assembled);
            // Record the symbol in some data structure.
            char symbol = match.getSymbol();
            symbols[i] = symbol;
            // Create a new bit sequence containing the remaining unmatched bits.
            BitSequence prefix = match.getSequence();
            assembled = assembled.allButFirstNBits(prefix.length());
        }
        // Write the symbols in some data structure to the specified file.
        FileUtils.writeCharArray(outputFile, symbols);
    }
}
