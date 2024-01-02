import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testRandom() {
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
            } else {
                Integer int1 = sad.removeLast();
                Integer int2 = ads.removeLast();
                message += "removeLast()\n";
                assertEquals(message, int1, int2);
            }
            for (int j = 0; j < sad.size(); j++) {
                assertEquals(message, ads.get(j), sad.get(j));
            }
        }
    }
}
