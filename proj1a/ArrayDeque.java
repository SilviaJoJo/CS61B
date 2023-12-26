public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    private int minusOne(int index) {
        if (index > 0) {
            return index - 1;
        }
        return items.length - 1;
    }
    private int plusOne(int index) {
        if (index < items.length - 1) {
            return index + 1;
        }
        return 0;
    }
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int start = capacity / 4;
        for (int i = 0; i < size; i++) {
            newArray[i + start] = get(i);
        }
        nextFirst = minusOne(start);
        nextLast = nextFirst + size + 1;
        items = newArray;
    }
    public void addFirst(T item) {
        if (size == items.length - 1) {
            resize(2 * items.length);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }
    public void addLast(T item) {
        if (size == items.length - 1) {
            resize(2 * items.length);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i).toString() + " ");
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ans = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        size--;
        nextFirst = plusOne(nextFirst);
        if (items.length >= 16 && items.length / size >= 4) {
            resize(items.length / 2);
        }
        return ans;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T ans = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        size--;
        nextLast = minusOne(nextLast);
        if (items.length >= 16 && items.length / size >= 4) {
            resize(items.length / 2);
        }
        return ans;
    }
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        } else if (nextFirst + index + 1 < items.length) {
            return items[nextFirst + index + 1];
        } else {
            return items[nextFirst + index + 1 - items.length];
        }
    }
}
