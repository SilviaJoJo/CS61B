import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the AutoGrader might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome1() {
        assertFalse(palindrome.isPalindrome("cat"));
    }

    @Test
    public void testisPalindrome2() {
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testisPalindrome3() {
        assertTrue(palindrome.isPalindrome("a"));
    }

    @Test
    public void testisPalindrome4() {
        assertTrue(palindrome.isPalindrome("inTni"));
    }

    @Test
    public void testisPalindrome5() {
        assertTrue(palindrome.isPalindrome("elle"));
    }

    @Test
    public void testisPalindrome6() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("a", offByOne));
        assertFalse(palindrome.isPalindrome("flattered", offByOne));
    }
}
