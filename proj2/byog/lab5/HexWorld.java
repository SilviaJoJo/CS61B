package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    public static void fillWithNothingTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
    public static void addHexagon(TETile[][] tiles, int s, int x, int y) {
        TETile pattern = randomTile();
        // I have supposed that the given position is the left lower corner
        for (int i = y + s; i < y + 2 * s; i++) {
            for (int j = (i - y) + x - 2 * s + 1; j < (y - i) + x + 3 * s - 1; j++) {
                tiles[j][i] = pattern;
            }
        }
        // draw the lower half
        for (int i = y; i < y + s; i++) {
            for (int j = x - (i - y); j < (i - y) + x + s; j++) {
                tiles[j][i] = pattern;
            }
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.SAND;
            default: return Tileset.FLOWER;
        }
    }

    public static void tesselateHexagon(TETile[][] tiles, int s, int x, int y) {
        // build the middle column of 5 hexagons
        for (int i = 0; i < 5; i++) {
            addHexagon(tiles, s, x, y - i * 2 * s);
        }
        // build the left column of 4 hexagons
        for (int i = 0; i < 4; i ++) {
            addHexagon(tiles, s, x - 2 * s + 1, y - s - i * 2 * s);
        }
        // build the right column of 4 hexagons
        for (int i = 0; i < 4; i ++) {
            addHexagon(tiles, s, x + 2 * s - 1, y - s - i * 2 * s);
        }
        // build the left column of 3 hexagons
        for (int i = 0; i < 3; i++) {
            addHexagon(tiles, s, x - 4 * s + 2, y - 2 * s - i * 2 * s);
        }
        // build the right column of 5 hexagons
        for (int i = 0; i < 3; i++) {
            addHexagon(tiles, s, x + 4 * s - 2, y - 2 * s - i * 2 * s);
        }
    }
    public static void main(String[] args) {
        // initialize a render engine and generate an array
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] tiles = new TETile[WIDTH][HEIGHT];
        fillWithNothingTiles(tiles);
        // draw a hexagon at a given index with a given size
        int x = 20;
        int y = 35;
        int size = 3;
        tesselateHexagon(tiles, size, x, y);
        //display
        ter.renderFrame(tiles);
    }
}
