import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

/** Array based list.
 *  @author Josh Hug
 */

public class AList <Item> {
    private Item[] items;
    private int size;

    /** Creates an empty list. */
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
    /** Inserts X into the back of the list. */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = x;
        size += 1;
    }

    /** Returns the item from the back of the list. */
    public Item getLast() {
        return items[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public Item get(int i) {
        if (i < 100) {
            return items[i];
        }
        return null;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public Item removeLast() {
        Item deleted = items[size - 1]; // or use getLast()
        items[size - 1] = null; // correct, but unnecessary
        size -= 1;
        return deleted;
    }

    public static void main(String[] args) {
        int[] result = {5, 9, 14, 15};
        for (int i = 0; i < result.length; i ++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
} 