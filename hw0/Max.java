public class Max {
    /** Returns the maximum value from m. */
    public static int max(int[] m) {
        int maxValue = m[0];
        int index = 0;
        while (index < m.length) {
            if (m[index] > maxValue) {
                maxValue = m[index];
            }
            index ++;
        }
        return maxValue;
    }
    public static int forMax(int[] m) {
        int value = m[0];
        for (int i = 0; i < m.length; i ++) {
            if (value < m[i]) {
                value = m[i];
            }
        }
        return value;
    }
    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(forMax(numbers));
    }
}