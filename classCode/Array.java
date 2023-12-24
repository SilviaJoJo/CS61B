public class Array {
    public static int[] insert(int[] arr, int item, int position) {
        int[] newArr = new int[arr.length + 1];
        if (position == 0) {
            newArr[0] = item;
            System.arraycopy(arr, 0, newArr, 1, arr.length);
        }
        else if (position >= arr.length) {
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            newArr[arr.length] = item;
        }
        else {
            System.arraycopy(arr, 0, newArr, 0, position);
            newArr[position] = item;
            System.arraycopy(arr, position, newArr, position + 1, arr.length - position);
        }
        return newArr;
    }

    public static void reverse(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        for (int i = 0; i <= (arr.length / 2 - 1); i ++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }

    public static int[] replicate(int[] arr) {
        int size = 0;
        for (int i = 0; i < arr.length; i ++) {
            size += arr[i];
        }
        int[] ans = new int[size];
        int index = 0;
        for (int i = 0; i < arr.length; i ++) {
            for (int j = 0; j < arr[i]; j ++) {
                ans[index + j] = arr[i];
            }
            index += arr[i];
        }
        return ans;
    }

    public static int[] flatten(int[][] x) {
        int totalLength = 0;
        for (int i = 0; i < x.length; i ++) {
            totalLength += x[i].length;
        }
        int[] a = new int[totalLength];
        int aIndex = 0;
        for (int i = 0; i < x.length; i ++) {
            for (int j = 0; j < x[i].length; j ++) {
                a[aIndex] = x[i][j];
                aIndex ++;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        //int[] input = {3, 2, 1};
        //int[] result = Array.insert(input, 6, 2);
        //int[] result = replicate(input);
        int[][] input = {{1, 2, 3}, {}, {7, 8}};
        int[] result = flatten(input);
        for (int i = 0; i < result.length; i ++) {
            System.out.print(result[i] + " ");
        }
        System.out.println();
    }
}