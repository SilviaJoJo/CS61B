public class FindSum {
    public static boolean findSum(int[] A, int x) {
        int first = 0;
        int last = A.length - 1;
        while (first < last) {
            if (A[first] + A[last] == x) {
                return true;
            } else if (A[first] + A[last] < x) {
                first++;
            } else {
                last--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 6};
        System.out.println(findSum(A, 1));
    }
}
