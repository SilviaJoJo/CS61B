import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void testIsSameNumber() {
        int i = 0;
        int j = 0;
        while (i < 500 && j < 500) {
            assertTrue(Flik.isSameNumber(i, j));
            i ++;
            j ++;
        }
    }
}