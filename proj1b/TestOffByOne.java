import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the AutoGrader might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b')); // regular 1
        assertTrue(offByOne.equalChars('r', 'q')); // regular 2
        assertTrue(offByOne.equalChars('&', '%')); // non-alphabetical
        assertTrue(offByOne.equalChars('A', 'B')); // capital letter
        assertFalse(offByOne.equalChars('a', 'e')); // regular 1
        assertFalse(offByOne.equalChars('z', 'a')); // regular 2
        assertFalse(offByOne.equalChars('a', 'a')); // equal
        assertFalse(offByOne.equalChars('a', 'B')); // lower and upper case
    }
}
