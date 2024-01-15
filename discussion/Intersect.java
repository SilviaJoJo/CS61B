import java.util.HashSet;

public class Intersect {
    public static int[] intersect(int[] A, int[] B) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int element : A) {
            set1.add(element);
        }
        for (int element : B) {
            if (set1.contains(element)) {
                set2.add(element);
            }
        }
        int[] ans = new int[set2.size()];
        int index = 0;
        for (int element : set2) {
            ans[index] = element;
            index++;
        }
        return ans;
    }
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4};
        int[] B = {3, 4, 5, 6};
        int[] result = intersect(A, B);
        for (int element : result) {
            System.out.println(element);
        }
    }
}
