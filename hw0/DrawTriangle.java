public class DrawTriangle {
    public static void drawTriangle(int N) {
        String line = "";
        int count = 0;
        while (count < N) {
            line = line + "*";
            System.out.println(line);
            count ++;
        }
    }
    public static void main(String[] args) {
        drawTriangle(5);
    }
}
