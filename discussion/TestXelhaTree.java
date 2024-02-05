import java.util.ArrayList;
import java.util.List;

public class TestXelhaTree {
    public static class IntTree {
        public int item;
        public IntTree left, right;
    }

    public static IntTree createXelhaTree(List<Integer> x) {
        return null;
    }

    /** If x is null, returns largest possible integer 2147483647 */
    private static int getItem(IntTree x) {
        if (x == null) {
            return Integer.MAX_VALUE;
        }
        return x.item;
    }

    public static boolean isAHeap(IntTree xt) {
        // Bug1: the input xt may be null
        if (xt == null) {
            return true;
        }
        IntTree[] toCheck = {xt.left, xt.right};
        for (IntTree t : toCheck) {
            if (t != null && (!isAHeap(t) || getItem(xt) > getItem(t))) {
                return false;
            }
        }
        return true;
    }

    public static void getTreeValues(IntTree xt, List<Integer> treeValues) {
        if (xt == null) {
            return;
        }
        getTreeValues(xt.left, treeValues);
        treeValues.add(getItem(xt));
        getTreeValues(xt.right, treeValues);
    }

    public static boolean validXelhaTree(IntTree xt, List<Integer> vals) {
        List<Integer> treeValues = new ArrayList<Integer>();
        /* getTreeValues adds all values in xt to treeValues */
        getTreeValues(xt, treeValues);
        return isAHeap(xt) && treeValues.equals(vals);
    }
}
