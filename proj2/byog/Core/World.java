package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.HashSet;
public class World {
    private HashSet<Position> floors;
    private HashSet<Position> walls;
    private int WIDTH;
    private int HEIGHT;
    private static int SEED;
    private static Random RANDOM;

    public World(int seed) {
        floors = new HashSet<>();
        walls = new HashSet<>();
        WIDTH = 50;
        HEIGHT = 50;
        SEED = seed;
        RANDOM = new Random(SEED);
    }
    public static void fillWithNothingTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }
    private boolean isOverlapped(HashSet<Position> set1, HashSet<Position> set2) {
        for (Position p2 : set2) {
            for (Position p1 : set1) {
                if (p2.x == p1.x && p2.y == p1.y) {
                    return true;
                }
            }
        }
        return false;
    }
    private void removeDuplicates(HashSet<Position> set1, HashSet<Position> set2) {
        HashSet<Position> duplicates = new HashSet<>();
        for (Position p2 : set2) {
            for (Position p1 : set1) {
                if (p2.x == p1.x && p2.y == p1.y) {
                    duplicates.add(p1);
                }
            }
        }
        set1.removeAll(duplicates);
    }
    public void generateWorld(Floor floor) {
        Floor structure = floor;
        for (int i = 1; i < WIDTH - 3; i++) {
            floors.addAll(structure.positions());
            walls.addAll(structure.surrounding());
            int nextX = structure.position.x + 1;
            while (i < WIDTH - 3) {
                int nextY = 1 + RANDOM.nextInt(HEIGHT - 3);
                int shape = RANDOM.nextInt(4);
                if (shape == 1) {
                    structure = new Room(new Position(nextX, nextY), WIDTH, HEIGHT, SEED + i);
                } else {
                    structure = new Hallway(new Position(nextX, nextY), WIDTH, HEIGHT, SEED + i);
                }
                if (isOverlapped(floors, structure.positions())) {
                    break;
                }
            }
        }
        removeDuplicates(walls, floors);
    }

    public TETile[][] visualizeWorld(TETile[][] tiles) {
        fillWithNothingTiles(tiles);
        WIDTH = tiles.length;
        HEIGHT = tiles[0].length;
        Floor start = new Room(new Position(1, 15), WIDTH, HEIGHT, SEED);
        generateWorld(start);
        for (Position p : floors) {
            tiles[p.x][p.y] = Tileset.FLOOR;
        }
        boolean locked = false;
        for (Position position : walls) {
            if (position.x == WIDTH / 2 && !locked) {
                tiles[position.x][position.y] = Tileset.LOCKED_DOOR;
                locked = true;
            } else {
                tiles[position.x][position.y] = Tileset.WALL;
            }
        }
        return tiles;
    }
}
