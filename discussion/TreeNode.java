public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public static boolean isBSTGood(TreeNode T) {
        return isBSTHelper(T, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isBSTHelper(TreeNode T, int minVal, int maxVal) {
        if (T == null) {
            return true;
        }
        if (T.val < minVal || T.val > maxVal) {
            return false;
        }
        return isBSTHelper(T.left, minVal, T.val) && isBSTHelper(T.right, T.val, maxVal);
    }
}
