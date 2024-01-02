public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.isEmpty()) {
            return true;
        }
        if (deque.removeFirst() == deque.removeLast()) {
            return isPalindromeHelper(deque);
        }
        return deque.isEmpty();
    }
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    private boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.isEmpty()) {
            return true;
        }
        Character front = deque.removeFirst();
        Character back = deque.removeLast();
        if (front == null || back == null) {
            return true;
        } else if (cc.equalChars(front, back)) {
            return isPalindromeHelper(deque, cc);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }
}
