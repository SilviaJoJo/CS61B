package hw2;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    private int[][] items;
    private int N;
    private int openSites;
    private WeightedQuickUnionUF uf;
    private int topNode;
    private int bottomNode;

    private int getIndex(int row, int col) {
        return row * items.length + col;
    }

    public Percolation(int N) {
        // check validity of N
        if (N < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        // set up N by N blocked grid pane
        this.N = N;
        items = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                items[i][j] = 0;
            }
        }
        // connect topNode and bottomNode
        uf = new WeightedQuickUnionUF(N * N + 2); // according to the spoilers
        topNode = N * N;
        bottomNode = N * N + 1;
        for (int i = 0; i < N; i++) {
            uf.union(i, topNode);
            uf.union(N * (N - 1) + i, bottomNode);
        }
        openSites = 0;
    }
    private void argumentCheck(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        argumentCheck(row, col);
        if (!isOpen(row, col)) {
            items[row][col] = 1;
            openSites++;
            // check for upper & lower & left & right neighbour
            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(getIndex(row, col), getIndex(row - 1, col));
            }
            if (row < N - 1 && isOpen(row + 1, col)) {
                uf.union(getIndex(row, col), getIndex(row + 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(getIndex(row, col), getIndex(row, col - 1));
            }
            if (col < N - 1 && isOpen(row, col + 1)) {
                uf.union(getIndex(row, col), getIndex(row, col + 1));
            }
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        argumentCheck(row, col);
        return items[row][col] > 0;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        argumentCheck(row, col);
        return uf.connected(getIndex(row, col), topNode);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }
    // does the system percolate?
    public boolean percolates() {
        return uf.connected(topNode, bottomNode);
    }
    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        assertFalse(percolation.percolates());
        percolation.open(0, 0);
        percolation.open(1, 1);
        percolation.open(0, 1);
        assertFalse(percolation.isOpen(1, 0));
        percolation.open(3, 4);
        percolation.open(2, 4);
        percolation.open(2, 2);
        percolation.open(2, 2);
        percolation.open(2, 3);
        percolation.open(0, 2);
        percolation.open(1, 2);
        assertTrue(percolation.isFull(2, 2));
        percolation.open(4, 4);
        assertTrue(percolation.percolates());
    }
}
