package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moves;
        private SearchNode previous;
        private int priority;
        public SearchNode(WorldState state, int moves, SearchNode previous) {
            this.state = state;
            this.moves = moves;
            this.previous = previous;
            this.priority = this.moves + this.state.estimatedDistanceToGoal();
        }
        public int compareTo(SearchNode other) {
            return Integer.compare(this.priority, other.priority);
        }
    }
    private MinPQ<SearchNode> states;
    private ArrayDeque<WorldState> solution;

    public Solver(WorldState initial) {
        states = new MinPQ<>();
        states.insert(new SearchNode(initial, 0, null));
        // the key here is that we shouldn't modify the instance attributes
        // (moves and solutions) every time we try a path
        // we should only store the temp results in MinPQ
        // and modify the instance attributes in the end altogether
        // so that a wrong trial will not have an effect
        while (!states.min().state.isGoal()) {
            SearchNode curr = states.delMin();
            for (WorldState s : curr.state.neighbors()) {
                // Big Bug Point Here!
                // remember to change "==" to ".equals()"
                // otherwise other WorldState may contain the same value
                // for which the check is also meaningless
                if (curr.previous == null || !s.equals(curr.previous.state)) {
                    states.insert(new SearchNode(s, curr.moves + 1, curr));
                }
            }
        }
        solution = new ArrayDeque<>();
        // from the final state we can trace back to its parents and ancestors
        SearchNode curr = states.min();
        while (curr != null) {
            solution.addFirst(curr.state);
            curr = curr.previous;
        }
        System.out.println(this.moves());
        System.out.println(solution.size());
    }

    public int moves() {
        // when we exit the loop in the constructor
        // the final state (which is goal) is still stored in our MinPQ
        // from which we can derive the total moves
        return states.min().moves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }
}
