package byog.Core;
import java.util.HashSet;
import java.util.Random;

public abstract class Floor {
    protected Position position; //the left lower corner coordinate
    // size of this Floor object
    protected int width;
    protected int height;
    // size of this whole background
    protected int WIDTH;
    protected int HEIGHT;
    protected int SEED;
    protected static Random RANDOM;
    protected HashSet<Position> positions;
    public Floor(Position position, int w, int h, int seed) {
        this.position = position;
        WIDTH = w;
        HEIGHT = h;
        SEED = seed;
        RANDOM = new Random(SEED);
        randomSize();
        positions = new HashSet<>();
        for (int i = position.x; i < position.x + width; i++) {
            for (int j = position.y; j < position.y + height; j++) {
                positions.add(new Position(i, j));
            }
        }
    }
    
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
