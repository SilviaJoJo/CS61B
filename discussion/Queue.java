import java.util.Stack;

public class Queue<T> {
    private final Stack<T> stack1;
    private final Stack<T> stack2;

    public Queue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    public void push(T item) {
        stack1.push(item);
    }
    public T poll() { // Take care of the return type!
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop(); // But there might still be no elements at all ...
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.push(1);
        queue.push(3);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
