package byog.Core;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;

public class Room extends Floor{
    public Room(Position position, int w, int h, int seed) {
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

    @Override
    public void randomSize() {
        int widthLimit = WIDTH - position.x - 1;
        int heightLimit = HEIGHT - position.y - 1;
        width = 2 + RANDOM.nextInt(Math.min(7, widthLimit - 1));
        height = 1 + RANDOM.nextInt(Math.min(5, heightLimit - 1));
    }
}
