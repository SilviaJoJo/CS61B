package byog.Core;
import java.util.HashSet;
import java.util.Random;

public abstract class Floor {
    public Position position; //the left lower corner coordinate
    // size of this Floor object
    protected int width;
    protected int height;
    // size of this whole background
    protected int WIDTH;
    protected int HEIGHT;
    protected int SEED;
    protected static Random RANDOM;
    protected HashSet<Position> positions;
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public HashSet<Position> positions() {
        return positions;
    }
    public abstract void randomSize();

    // TO DO3: testing
    public HashSet<Position> surrounding() {
        HashSet<Position> surr = new HashSet<>();
        for (int j = position.y - 1; j < position.y + height + 1; j++) {
            surr.add(new Position(position.x - 1, j));
        }
        for (int j = position.y - 1; j < position.y + height + 1; j++) {
            surr.add(new Position(position.x + width, j));
        }
        for (int i = position.x - 1; i < position.x + width + 1; i++) {
            surr.add(new Position(i, position.y - 1));
        }
        for (int i = position.x - 1; i < position.x + width + 1; i++) {
            surr.add(new Position(i, position.y + height));
        }
        return surr;
    }
}
