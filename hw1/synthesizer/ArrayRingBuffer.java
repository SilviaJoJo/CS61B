package synthesizer;

import java.util.Iterator;
import java.util.NoSuchElementException;
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int plusOne(int index) {
        if (index == capacity - 1) {
            return 0;
        }
        return index + 1;
    }

    private int minusOne(int index) {
        if (index == 0) {
            return capacity - 1;
        }
        return index - 1;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring buffer underflow");
        }
        T ans = rb[first];
        rb[first] = null;
        first = plusOne(first);
        fillCount--;
        return ans;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Ring buffer overflow");
        }
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int index;
        public ArrayRingBufferIterator() {
            index = first;
        }
        public boolean hasNext() {
            return index != last;
        }
        public T next() {
            index++;
            return rb[minusOne(index)];
        }
    }
}
