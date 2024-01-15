import java.util.HashSet;

public class Union {
    public static int[] union(int[] arr1, int[] arr2) {
        // The method should run in O(N + M) time
        // Hint: use ADT
        HashSet<Integer> set = new HashSet<>();
        for (int element : arr1) {
            set.add(element);
        }
        for (int element : arr2) {
            set.add(element);
        }
        int[] ans = new int[set.size()];
        int index = 0;
        for (int element : set) {
            ans[index] = element;
            index++;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4};
        int[] B = {3, 4, 5, 6};
        int[] result = union(A, B);
        for (int element : result) {
            System.out.println(element);
        }
    }
}