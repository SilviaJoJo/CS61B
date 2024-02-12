package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean cyclesFound = false;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        for (int i = 0; i < this.maze.V(); i++) {
            distTo[i] = 0;
            edgeTo[i] = i;
            dfsCheck(i);
            if (cyclesFound) {
                return;
            }
        }
    }

    // Helper methods go here
    private void dfsCheck(int v) {
        marked[v] = true;
        announce();
        for (int w: this.maze.adj(v)) {
            if (marked[w] && w != edgeTo[v]) {
                edgeTo[w] = v;
                announce();
                cyclesFound = true;
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                dfsCheck(w);
            } else if (w == this.maze.V() - 1) {
                return;
            }
        }
    }
}

