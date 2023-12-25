public class LinkedListDeque<T> {
    private class TNode {
        private T stuff;
        private TNode previous;
        private TNode next;

        public TNode(T s, TNode p, TNode n) {
            stuff = s;
            previous = p;
            next = n;
        }
    }
    private TNode sentinel;
    private int size;
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public void addFirst(T item) {
        TNode newNode = new TNode(item, sentinel, sentinel.next);
        sentinel.next = newNode;
        newNode.next.previous = newNode;
        size++;
    };
    public void addLast(T item) {
        TNode newNode = new TNode(item, sentinel.previous, sentinel);
        sentinel.previous = newNode;
        newNode.previous.next = newNode;
        size++;
    };
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    };
    public int size() {
        return size;
    };
    public void printDeque() {
        TNode current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.stuff.toString() + " ");
            current = current.next;
        }
    };
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ans = sentinel.next.stuff;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size--;
        return ans;
    };
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T ans = sentinel.previous.stuff;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size--;
        return ans;
    };
    public T get(int index) {
        TNode current = sentinel.next;
        while (index >= 0) {
            if (current == sentinel) {
                return null;
            }
            current = current.next;
            index--;
        }
        return current.previous.stuff;
    };
    private T getRecurHelper(TNode t, int index) {
        if (index == 0 && t == sentinel) {
            return null;
        } else if (index == 0) {
            return t.stuff;
        }
        return getRecurHelper(t.next, index - 1);
    }
    public T getRecursive(int index) {
        return getRecurHelper(sentinel.next, index);
    };
}
