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
        // Step 2: pad all insufficient strings
        String[] ans = new String[asciis.length];
        System.arraycopy(asciis, 0, ans, 0, ans.length);
        for (int i = 0; i < asciis.length; i++) {
            if (asciis[i].length() < maxLen) {
                int toPad = maxLen - asciis[i].length();
                for (int j = 0; j < toPad; j++) {
                    ans[i] += (char) 0;
                }
            }
        }
        // Step 3: use iteration to sort
        for (int i = 0; i < maxLen; i++) {
            sortHelperLSD(ans, maxLen - i - 1);
        }
        // Step 4: revert to states before padding
        for (int i = 0; i < asciis.length; i++) {
            ans[i] = ans[i].replaceAll(String.valueOf((char) 0), "");
        }
        return ans;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        String[] sorted = new String[asciis.length];
        System.arraycopy(asciis, 0, sorted, 0, sorted.length);
        int[] input = new int[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            char toCompute = sorted[i].charAt(index);
            input[i] = (int) toCompute;
        }
        int[] output = CountingSort.betterCountingSort(input);
        for (int i = 0; i < asciis.length; i++) {
            for (int j = 0; j < asciis.length; j++) {
                if (sorted[j] != null && output[i] == (int) sorted[j].charAt(index)) {
                    asciis[i] = new String(sorted[j]);
                    sorted[j] = null;
                    break;
                }
            }
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
