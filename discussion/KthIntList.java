import java.util.Iterator;
import java.util.NoSuchElementException;
public class KthIntList implements Iterator<Integer> {
    public int k;
    private IntList curList;
    private boolean hasNext;
    public KthIntList(IntList I, int k) {
        this.k = k;
        this.curList = I;
        this.hasNext = true;
    }
    public boolean hasNext() {
        return this.hasNext;
    }
    public Integer next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Integer ans = curList.first;
        for (Integer i = 0; i < k; i++) {
            curList = curList.rest;
        }
        this.hasNext = curList.size() > 0;
        return ans;
    }
}
