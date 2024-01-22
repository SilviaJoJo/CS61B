package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int[][] items;
    private int openSites;
    private WeightedQuickUnionUF ufForP; // check percolates
    private WeightedQuickUnionUF ufForF; // check isFull
    private int topSite;
    private int bottomSite;
    // one trap here is that bottomSite is only used for percolates
    // and, it will hinder the differentiation of isFull
    // so, we keep one uf with it and another without it


    // create N-by-N grid, with all sites initially blocked
    private int getIndex(int row, int col) {
        return row * N + col;
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
        ufForP = new WeightedQuickUnionUF(N * N + 2);
        ufForF = new WeightedQuickUnionUF(N * N + 1);
        topSite = N * N;
        bottomSite = N * N + 1;
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
                ufForP.union(getIndex(row, col), getIndex(row - 1, col));
                ufForF.union(getIndex(row, col), getIndex(row - 1, col));
            }
            if (row < N - 1 && isOpen(row + 1, col)) {
                ufForP.union(getIndex(row, col), getIndex(row + 1, col));
                ufForF.union(getIndex(row, col), getIndex(row + 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                ufForP.union(getIndex(row, col), getIndex(row, col - 1));
                ufForF.union(getIndex(row, col), getIndex(row, col - 1));
            }
            if (col < N - 1 && isOpen(row, col + 1)) {
                ufForP.union(getIndex(row, col), getIndex(row, col + 1));
                ufForF.union(getIndex(row, col), getIndex(row, col + 1));
            }
            if (row == 0) {
                ufForP.union(getIndex(row, col), topSite);
                ufForF.union(getIndex(row, col), topSite);
            }
            if (row == N - 1) {
                ufForP.union(getIndex(row, col), bottomSite);
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
        return ufForF.connected(getIndex(row, col), topSite);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }
    // does the system percolate?
    public boolean percolates() {
        return ufForP.connected(topSite, bottomSite);
    }
    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(3, 4);
        percolation.open(2, 4);
        percolation.open(2, 2);
        percolation.open(2, 3);
        percolation.open(0, 2);
        percolation.open(1, 2);
        percolation.open(4, 4);
        percolation.open(4, 0);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(percolation.items[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(percolation.percolates());
        System.out.println(percolation.isFull(0, 4));
        Percolation percolation1 = new Percolation(1);
        percolation1.open(0, 0);
        System.out.println(percolation1.items[0][0]);
        System.out.println(percolation1.percolates());
    }
}
