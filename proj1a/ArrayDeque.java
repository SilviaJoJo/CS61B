public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    };
    private int minusOne(int index) {
        if (index > 0) {
            return index - 1;
        }
        return items.length - 1;
    }
    private int plusOne(int index) {
        if (index < items.length - 1) {
            return index - 1;
        }
        return 0;
    }
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int start = size / 2;
        for (int i = 0; i < size; i++) {
            newArray[i + start] = items[i];
        }
        nextFirst = start - 1;
        nextLast = start + size;
        items = newArray;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    };
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
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
        int index = nextFirst;
        for (int i = 0; i < size; i++) {
            System.out.print(items[index].toString() + " ");
            index = plusOne(index);
        }
    };

    public T removeFirst() {
        T ans = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        size--;
        nextFirst = plusOne(nextFirst);
        if (items.length >= 16 && size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return ans;
    };
    public T removeLast() {
        T ans = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        size--;
        nextLast = minusOne(nextLast);
        if (items.length >= 16 && size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return ans;
    };
    public T get(int index) {
        if (index < 0) {
            return null;
        } else if (nextFirst + index + 1 < items.length) {
            return items[nextFirst + index + 1];
        } else {
            return items[nextFirst + index + 1 - items.length];
        }
    };
}
