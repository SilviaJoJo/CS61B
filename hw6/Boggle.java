import java.io.File;
import java.util.*;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "trivial_words.txt";
    // to store strings from dictionary file
    private static Trie trie;
    // to store chars from input board file
    private static char[][] chars;
    // to store all valid strings in input board file
    private static final Comparator<String> STRCOMPARATOR = (s1, s2) -> { // advice from ChatGPT
        if (s1.length() != s2.length()) {
            return s2.length() - s1.length();
        } else {
            return s1.compareTo(s2);
        }
    };
    // Previously I used priority queue, but later I changed it to treeSet to eliminate duplicates
    private static TreeSet<String> treeSet;
    private static final int[] NEIGHBOURSX = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int[] NEIGHBOURSY = {-1, -1, -1, 0, 0, 1, 1, 1};

    private static void checkKValidity(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException();
        }
    }
    private static void checkFileExists(String boardFilePath) {
        File file = new File(boardFilePath);
        if (!file.exists()) {
            throw new IllegalArgumentException();
        }
    }
    private static void checkRectangular(String[] board) {
        int standard = board[0].length();
        for (String s: board) {
            if (s.length() != standard) {
                throw new IllegalArgumentException();
            }
        }
    }
    private static char[][] convertTo2DCharArray(String[] board) {
        char[][] charArray = new char[board.length][board[0].length()];
        for (int i = 0; i < board.length; i++) {
            charArray[i] = board[i].toCharArray();
        }
        return charArray;
    }

    private static boolean isNeighbourAvailable(int i, int j, int[][] isVisited) {
        return (i >= 0 && i < chars.length && j >= 0 && j < chars[0].length && isVisited[i][j] == 0);
    }
    /** @ Source: ZhiYuan Ma 2017
     * I think the most difficult part is to design the function signature
     * since chars[][] and treeSet is used and modified by all calls, they should be class attributes
     * to keep track of the current string, we can mimic tail recursion, passing it as an argument
     * after we are done with the current char, we should reset the isVisited back */
    private static void searchForString(String pre, int[][] isVisited, int i, int j) {
        pre += chars[i][j];
        isVisited[i][j] = 1;
        if (pre.length() >= 3 && trie.existsString(pre)) {
            treeSet.add(pre);
        }
        for (int index = 0; index < NEIGHBOURSX.length; index++) {
            int nextX = i + NEIGHBOURSX[index];
            int nextY = j + NEIGHBOURSY[index];
            // preCheck improves performance!
            if (isNeighbourAvailable(nextX, nextY, isVisited) && trie.existsPrefix(pre + chars[nextX][nextY])) {
                searchForString(pre, isVisited, nextX, nextY);
            }
        }
        // done with the current char; reset back to unvisited
        isVisited[i][j] = 0;
    }

    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        // check error case1: whether k is positive
        checkKValidity(k);
        // check error case2: whether the dictionary file exists
        checkFileExists(boardFilePath);
        In dict = new In(dictPath);
        String[] dictionary = dict.readAllLines();
        // check error case3: whether the input board is rectangular
        In boardFile = new In(boardFilePath);
        String[] board = boardFile.readAllLines();
        checkRectangular(board);
        chars = convertTo2DCharArray(board);

        // store all strings from dictionary into a trie
        // debug -> insert failed! -> now fixed
        trie = new Trie();
        for (String s : dictionary) {
            trie.insert(s);
        }

        // start from each char in boardChars, and store qualified strings
        int m = chars.length;
        int n = chars[0].length;
        int[][] isVisited = new int[m][n];
        treeSet = new TreeSet<>(STRCOMPARATOR);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                searchForString("", isVisited, i, j);
            }
        }

        // retrieve answer
        List<String> ans = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (treeSet.isEmpty()) {
                break;
            }
            ans.add(treeSet.pollFirst());
        }
        treeSet = null;
        return ans;
    }

    public static void main(String[] args) {
        String boardFilePath = "exampleBoard2.txt";
        List<String> ans = solve(20, boardFilePath);
        for (String s : ans) {
            System.out.print(s + " ");
        }
    }
}
