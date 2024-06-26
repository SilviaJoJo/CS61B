import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        //LinkedListDeque<Integer> sad = new LinkedListDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 1000; i++) {
            double NumberBetween0And4 = 4 * StdRandom.uniform();
            if (NumberBetween0And4 < 1) {
                sad.addFirst(i);
                ads.addFirst(i);
                message += "addFirst(" + Integer.toString(i) + ")\n";
            } else if (NumberBetween0And4 < 2) {
                sad.addLast(i);
                ads.addLast(i);
                message += "addLast(" + Integer.toString(i) + ")\n";
            } else if (NumberBetween0And4 < 3) {
                Integer int1 = sad.removeFirst();
                Integer int2 = ads.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, int1, int2);
                // It seems that null is not allowed
                // we should only check for the return number
            } else {
                Integer int1 = sad.removeLast();
                Integer int2 = ads.removeLast();
                message += "removeLast()\n";
                assertEquals(message, int1, int2);
                // same here
            }
        }
    }
}
