import java.util.NoSuchElementException;
public class SLListVista extends SLList{
    @Override
    public int indexOf(int x) {
        int index = super.indexOf(x);
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return index;
    }
}
