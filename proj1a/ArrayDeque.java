public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    };
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int start = size/2;
        for (int i = 0; i < size; i ++) {
            newArray[i + start] = items[i];
        }
        nextFirst = start - 1;
        nextLast = start + size;
        items = newArray;
    }
    public void addFirst(T item){
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextFirst] = item;
        nextFirst --;
        size ++;
    };
    public void addLast(T item){
        if (size == items.length) {
            resize(2 * size);
        }
        items[nextLast] = item;
        nextLast ++;
        size ++;
    };
    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }
        return false;
    };
    public int size(){
        return size;
    };
    public void printDeque(){
        for (int i = nextFirst + 1; i < nextLast; i ++) {
            System.out.print(items[i].toString() + " ");
        }
    };

    public T removeFirst(){
        T ans = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        size --;
        nextFirst ++;
        if (items.length >= 16 && size/ items.length < 0.25) {
            resize(items.length / 2);
        }
        return ans;
    };
    public T removeLast(){
        T ans = items[nextLast - 1];
        items[nextLast - 1] = null;
        size --;
        nextLast --;
        if (items.length >= 16 && size / items.length < 0.25) {
            resize(items.length / 2);
        }
        return ans;
    };
    public T get(int index){
        return items[index];
    };
}