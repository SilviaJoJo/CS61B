import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.Queue;

public class SeamCarver {
    private int width;
    private int height;
    private Picture myPicture;
    public SeamCarver(Picture picture) {
        width = picture.width();
        height = picture.height();
        myPicture = picture;
    }
    public Picture picture() {
        return myPicture;
    }                      // current picture
    public     int width() {
        return width;
    }                        // width of current picture
    public     int height() {
        return height;
    }                       // height of current picture
    private int nextX(int x) {
        return (x + 1) % width;
    }
    private int lastX(int x) {
        return (x + width - 1) % width;
    }
    private int nextY(int y) {
        return (y + 1) % height;
    }
    private int lastY(int y) {
        return (y + height - 1) % height;
    }
    public  double energy(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int[] rightX = {myPicture.get(nextX(x), y).getRed(),
                myPicture.get(nextX(x), y).getGreen(), myPicture.get(nextX(x), y).getBlue()};
        int[] leftX = {myPicture.get(lastX(x), y).getRed(),
                myPicture.get(lastX(x), y).getGreen(), myPicture.get(lastX(x), y).getBlue()};
        int[] lowerY = {myPicture.get(x, nextY(y)).getRed(),
                myPicture.get(x, nextY(y)).getGreen(), myPicture.get(x, nextY(y)).getBlue()};
        int[] upperY = {myPicture.get(x, lastY(y)).getRed(),
                myPicture.get(x, lastY(y)).getGreen(), myPicture.get(x, lastY(y)).getBlue()};
        int deltaX = (rightX[0] - leftX[0]) * (rightX[0] - leftX[0]) + (rightX[1] - leftX[1]) *
                (rightX[1] - leftX[1]) + (rightX[2] - leftX[2]) * (rightX[2] - leftX[2]);
        int deltaY = (lowerY[0] - upperY[0]) * (lowerY[0] - upperY[0]) + (lowerY[1] - upperY[1]) *
                (lowerY[1] - upperY[1]) + (lowerY[2] - upperY[2]) * (lowerY[2] - upperY[2]);
        return deltaX + deltaY;
    }           // energy of pixel at column x and row y
    public   int[] findHorizontalSeam() {
        // transpose myPicture (non-destructively)
        Picture transposed = new Picture(height, width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                transposed.set(i, j, myPicture.get(j, i));
            }
        }
        SeamCarver sc = new SeamCarver(transposed);
        int[] ans = sc.findVerticalSeam();
        return ans;
    }           // sequence of indices for horizontal seam

    public   int[] findVerticalSeam() {
        int[] ans = new int[height];
        double minCost = Integer.MAX_VALUE;
        double currCost = 0;
        Queue<int[]> queue = new Queue<>();
        for (int i = 0; i < width; i++) {
            int[] initialTrace = {i};
            queue.enqueue(initialTrace);
        }
        while (!queue.isEmpty()) {
            int[] currTrace = queue.dequeue();
            if (currTrace.length < height) {
                int lastPosition = currTrace[currTrace.length - 1];
                if (lastPosition < width - 1) {
                    int[] newTrace = new int[currTrace.length + 1];
                    System.arraycopy(currTrace, 0, newTrace, 0, currTrace.length);
                    newTrace[newTrace.length - 1] = lastPosition + 1;
                    queue.enqueue(newTrace);
                }
                if (lastPosition > 0) {
                    int[] newTrace = new int[currTrace.length + 1];
                    System.arraycopy(currTrace, 0, newTrace, 0, currTrace.length);
                    newTrace[newTrace.length - 1] = lastPosition - 1;
                    queue.enqueue(newTrace);
                }
                int[] newTrace = new int[currTrace.length + 1];
                System.arraycopy(currTrace, 0, newTrace, 0, currTrace.length);
                newTrace[newTrace.length - 1] = lastPosition;
                queue.enqueue(newTrace);
            } else {
                for (int i = 0; i < height; i++) {
                    currCost += energy(currTrace[i], i);
                }
                if (currCost < minCost) {
                    minCost = currCost;
                    System.arraycopy(currTrace, 0, ans, 0, height);
                }
                currCost = 0;
            }
        }
        return ans;
    }             // sequence of indices for vertical seam
    public    void removeHorizontalSeam(int[] seam) {
        if (seam.length != width) {
            throw new java.lang.IllegalArgumentException();
        }
        myPicture = SeamRemover.removeHorizontalSeam(myPicture, seam);
    }  // remove horizontal seam from picture
    public    void removeVerticalSeam(int[] seam) {
        if (seam.length != width) {
            throw new java.lang.IllegalArgumentException();
        }
        myPicture = SeamRemover.removeVerticalSeam(myPicture, seam);
    }    // remove vertical seam from picture
}
