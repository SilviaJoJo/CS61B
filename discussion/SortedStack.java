import java.util.Stack;
import java.lang.Integer;

public class SortedStack<Item extends Comparable<Item>>{ // copied this line from solution
    private Stack<Item> stack1;
    private Stack<Item> stack2;

    public SortedStack() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    public void push(Item item) {
        // Step1: move all the small elements in stack1 to stack2
        while (!stack1.isEmpty() && stack1.peek().compareTo(item) < 0) {
            stack2.push(stack1.pop());
        }
        // Step2: push item to stack1
        stack1.push(item);
        // Step3: move all the elements in stack2 to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
    public Item poll() {
        return stack1.pop();
    }

    public static void main(String[] args) {
        SortedStack<Integer> ss = new SortedStack<>();
        ss.push(10);
        ss.push(4);
        ss.push(8);
        ss.push(2);
        ss.push(14);
        ss.push(3);
        for (int i = 0; i < 6; i++) {
            System.out.println(ss.poll());
        }
    }
}
