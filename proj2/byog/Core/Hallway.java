package byog.Core;

import java.util.HashSet;
import java.util.Random;

public class Hallway extends Floor{
    public Hallway(Position position, int w, int h, int seed) {
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
    // TO DO5: testing
    public void randomSize() {
        int widthLimit = WIDTH - position.x - 1;
        int heightLimit = HEIGHT - position.y - 1;
        int length = 2 + RANDOM.nextInt(Math.min(10, Math.min(widthLimit - 1, heightLimit - 1)));
        int shape = RANDOM.nextInt(2);
        if (shape == 0) {
            width = 1;
            height = length;
        } else {
            width = length;
            height = 1;
        }
    }
}
