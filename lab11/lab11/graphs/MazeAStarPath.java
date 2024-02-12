package lab11.graphs;

import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    private class Node implements Comparable<Node> {
        private int value;
        private int priority;
        public Node(int value, int priority) {
            this.value = value;
            this.priority = priority;
        }
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        for (int i = 1; i < maze.V(); i++) {
            distTo[i] = Integer.MAX_VALUE;
        }
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }


    /** Performs an A star search from vertex s. */
    private void astar(int src) {
        // very similar to the implementation of bfs
        // the only modification is to change the normal queue to priority queue
        // which requires defining a new Comparator class and priority
        PriorityQueue<Node> fringe = new PriorityQueue<>();
        fringe.add(new Node(src, 0));
        marked[src] = true;
        while (!fringe.isEmpty()) {
            Node node = fringe.poll();
            announce();
            int v = node.value;
            int priority = node.priority;
            if (v == t) {
                break;
            }
            for (int w: maze.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    fringe.add(new Node(w, distTo[w] + h(w)));
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

