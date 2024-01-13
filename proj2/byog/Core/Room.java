package byog.Core;

public class Room extends Floor {
    public Room(Position position, int w, int h, int seed) {
        super(position, w, h, seed);
    }

    @Override
    public void randomSize() {
        int widthLimit = WIDTH - position.x - 1;
        int heightLimit = HEIGHT - position.y - 1;
        width = 2 + RANDOM.nextInt(Math.min(7, widthLimit - 1));
        height = 1 + RANDOM.nextInt(Math.min(5, heightLimit - 1));
    }
}
