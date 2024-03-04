/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // Step 1: find the max length
        int maxLen = 0;
        for (String ascii : asciis) {
            if (ascii.length() > maxLen) {
                maxLen = ascii.length();
            }
        }
        // Step 2: use iteration to sort
        // Previously I padded before this stage, but turned out to be inefficient
        String[] ans = new String[asciis.length];
        System.arraycopy(asciis, 0, ans, 0, ans.length);
        for (int i = 0; i < maxLen; i++) {
            sortHelperLSD(ans, maxLen - i - 1);
        }
        return ans;
    }

    private static int charAtOrPad(String item, int index) {
        if (index >= item.length()) {
            return 0;
        } else {
            return item.charAt(index);
        }
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        // If I directly call CountingSort here, the runtime will be terrible
        // So, instead, I should mimic counting sort and write a new version
        int max = 256;
        int[] counts = new int[max + 1];
        for (String item : asciis) {
            counts[charAtOrPad(item, index)]++;
        }
        int[] starts = new int[max + 1];
        int position = 0;
        for (int i = 0; i < starts.length; i++) {
            starts[i] = position;
            position += counts[i];
        }
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            String item = asciis[i];
            int place = charAtOrPad(item, index);
            sorted[starts[place]] = item;
            starts[place]++;
        }
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted[i];
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] test = {"rudimentary", "about", "quite", "hello"};
        test = sort(test);
        for (String item : test) {
            System.out.print(item + " ");
        }
    }
}
