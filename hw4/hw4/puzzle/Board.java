package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int[][] world;
    private int N;
    private static final int BLANK = 0;
    public Board(int[][] tiles) {
        N = tiles.length;
        world = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                world[i][j] = tiles[i][j];
            }
        }
    }

    private void checkValidity(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }
    public int tileAt(int i, int j) {
        checkValidity(i, j);
        return world[i][j];
    }

    public int size() {
        return N;
    }

    /** @source:JoshHug */
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private int goalI(int num) {
        return (num - 1) / N;
    }
    private int goalJ(int num) {
        return (num - 1) % N;
    }
    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // by looking into the example in tutorial
                // we can know that hamming doesn't count the blank block
                if (tileAt(i, j) != 0 && tileAt(i, j) != N * i + j + 1) {
                    count++;
                }
            }
        }
        return count;
    }
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = tileAt(i, j);
                // Only non-blank blocks can be moved
                if (num != 0) {
                    count += Math.abs(goalI(num) - i) + Math.abs(goalJ(num) - j);
                }
            }
        }
        return count;
    }
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        // mimic the standard implementation of equals
        if (y == this) {
            return true;
        }
        if (y == null || getClass() != y.getClass()) {
            return false;
        }
        Board a = (Board) y;
        if (a.size() != size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = 0; j < size(); j++) {
                if (tileAt(i, j) != a.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    // one trick here is the hashCode function must be modified accordingly
    // so even if it's not required by the tutorial
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result *= 3;
                result += tileAt(i, j);
            }
        }

        return result;
    }
    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int n = size();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
}
