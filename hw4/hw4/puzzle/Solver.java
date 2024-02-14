package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Stack;

public class Solver {
    private class SearchNode implements Comparable<SearchNode> {
        WorldState state;
        int moves;
        SearchNode previous;
        public SearchNode(WorldState state, int moves, SearchNode previous) {
            this.state = state;
            this.moves = moves;
            this.previous = previous;
        }
        public int compareTo(SearchNode other) {
            return Integer.compare(moves + state.estimatedDistanceToGoal(),
                    other.moves + other.state.estimatedDistanceToGoal());
        }
    }
    MinPQ<SearchNode> states;
    WorldState start;
    int moves;
    ArrayList<WorldState> solution;

    public Solver(WorldState initial) {
        states = new MinPQ<>();
        states.insert(new SearchNode(initial, 0, null));
        start = initial;
        moves = 0;
        solution = new ArrayList<>();
        // the key here is that we shouldn't modify the instance attributes
        // (moves and solutions) every time we try a path
        // we should only store the temp results in MinPQ
        // and modify the instance attributes in the end altogether
        // so that a wrong trial will not have an effect
        while (!states.min().state.isGoal()) {
            SearchNode curr = states.delMin();
            for (WorldState s : curr.state.neighbors()) {
                if (curr.previous == null || s != curr.previous) {
                    states.insert(new SearchNode(s, curr.moves + 1, curr));
                }
            }
        }
        solution();
    }

    public int moves() {
        // when we exit the loop in the constructor
        // the final state (which is goal) is still stored in our MinPQ
        // from which we can derive the total moves
        return states.min().moves;
    }

    public Iterable<WorldState> solution() {
        // from the final state we can trace back to its parents and ancestors
        Stack<WorldState> trace = new Stack<>();
        SearchNode helper = states.min();
        trace.push(states.min().state);
        while (true) {
            WorldState curr = trace.pop();
            solution.add(curr);
            if (curr == start) {
                return solution;
            } else {
                helper = helper.previous;
                trace.push(helper.state);
            }
        }
    }
}
