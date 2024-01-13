public class BadIntegerStack {
    private class Node {
        private Integer value;
        private Node prev;
        private Node(Integer v, Node p) {
            value = v;
            prev = p;
        }
    }
    private Node top;
    public boolean isEmpty() {
        return top == null;
    }
    public void push(Integer num) {
        top = new Node(num, top);
    }
    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        Integer ans = top.value;
        top = top.prev;
        return ans;
    }
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return top.value;
    }

    public static void main(String[] args) {
        try {
            BadIntegerStack bis = new BadIntegerStack();
            bis.peek();
        }
        catch (NullPointerException e) {
            System.out.println("Success");
        }
        BadIntegerStack bis = new BadIntegerStack();
        bis.push(1);
        bis.top.prev = bis.top; // public -> dangerous!
        while (!bis.isEmpty()) { // the stack's previous is never null
            bis.pop();
        }
        System.out.println("This stack appears finitely tall!");
    }
}
