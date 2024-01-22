package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] thresholds;
    private int N;
    private int T;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        thresholds = new double[T];
        this.N = N;
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation per = pf.make(N);
            int trials = 0;
            while (!per.percolates()) {
                per.open(StdRandom.uniform(N), StdRandom.uniform(N));
                trials++;
            }
            thresholds[i] = (double) trials / (N * N);
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
