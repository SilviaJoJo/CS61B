package hw2;
import static org.junit.Assert.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.awt.*;

public class Percolation {
    private int N;
    private int[][] items;
    private int openSites;
    private WeightedQuickUnionUF uf;
    private int topSite;
    private int bottomSite;

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
        uf = new WeightedQuickUnionUF(N * N + 2); // according to the spoilers
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
            if (row == 0) {
                uf.union(getIndex(row, col), topSite);
            } else if (row == N - 1) {
                uf.union(getIndex(row, col), bottomSite);
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
        return uf.connected(getIndex(row, col), topSite);
    }
    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }
    // does the system percolate?
    public boolean percolates() {
        return uf.connected(topSite, bottomSite);
    }
    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(5);
        percolation.open(3, 4);
        percolation.open(2, 4);
        percolation.open(2, 2);
        percolation.open(2, 3);
//      percolation.open(0, 2);
        percolation.open(1, 2);
        assertFalse(percolation.percolates());
        percolation.open(4, 4);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(percolation.items[i][j] + " ");
                assertFalse(percolation.isFull(i, j));
            }
            System.out.println();
        }
    }
}
