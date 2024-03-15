import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private int width;
    private int height;
    private Picture myPicture;
    public SeamCarver(Picture picture) {
        width = picture.width();
        height = picture.height();
        myPicture = new Picture(picture);
    }
    public Picture picture() {
        // cannot directly return the pointer
        // otherwise our myPicture may be modified!
        return new Picture(myPicture);
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
        int deltaX = (rightX[0] - leftX[0]) * (rightX[0] - leftX[0]) + (rightX[1] - leftX[1])
                * (rightX[1] - leftX[1]) + (rightX[2] - leftX[2]) * (rightX[2] - leftX[2]);
        int deltaY = (lowerY[0] - upperY[0]) * (lowerY[0] - upperY[0]) + (lowerY[1] - upperY[1])
                * (lowerY[1] - upperY[1]) + (lowerY[2] - upperY[2]) * (lowerY[2] - upperY[2]);
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

    /** My previous solution used Queue for iteration.
     * It turned out to work fine, but timed out... */
    public   int[] findVerticalSeam() {
        // store e and M according to tutorial
        int[][] e = new int[width][height];
        int[][] M = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                e[i][j] = (int) energy(i, j);
            }
        }
        for (int i = 0; i < width; i++) {
            M[i][0] = e[i][0];
        }
        for (int j = 1; j < height; j++) {
            for (int i = 0; i < this.width(); i++) {
                M[i][j] = e[i][j] + M[i][j - 1];
                if (i > 0 && e[i][j] + M[i - 1][j - 1] < M[i][j]) {
                    M[i][j] = e[i][j] + M[i - 1][j - 1];
                }
                if (i < width - 1 && e[i][j] + M[i + 1][j - 1] < M[i][j]) {
                    M[i][j] = e[i][j] + M[i + 1][j - 1];
                }
            }
        }
        // find the min cost and index
        int[] seam = new int[height];
        seam[height - 1] = 0;
        for (int i = 0; i < width; i++) {
            if (M[i][height - 1] < M[seam[height - 1]][height - 1]) {
                seam[height - 1] = i;
            }
        }
        // trace back
        for (int j = this.height() - 2; j >= 0; j--) {
            int i = seam[j + 1];
            if (seam[j + 1] > 0 && M[seam[j + 1] - 1][j] < M[i][j]) {
                i = seam[j + 1] - 1;
            }
            if (seam[j + 1] < width - 1 && M[seam[j + 1] + 1][j] < M[i][j]) {
                i = seam[j + 1] + 1;
            }
            seam[j] = i;
        }
        return seam;
    }             // sequence of indices for vertical seam
    public    void removeHorizontalSeam(int[] seam) {
        if (seam.length != width) {
            throw new java.lang.IllegalArgumentException();
        }
        myPicture = new Picture(SeamRemover.removeHorizontalSeam(myPicture, seam));
        // seamRemover only modifies myPicture (and returns the modified version)
        // we have to update width and height manually
        width = myPicture.width();
        height = myPicture.height();
    }  // remove horizontal seam from picture
    public    void removeVerticalSeam(int[] seam) {
        if (seam.length != height) {
            throw new java.lang.IllegalArgumentException();
        }
        myPicture = new Picture(SeamRemover.removeVerticalSeam(myPicture, seam));
        width = myPicture.width();
        height = myPicture.height();
    }    // remove vertical seam from picture
}
