package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Part2 extends PuzzleSolver {

    public static final long EXPECTED = 6;
    public static final String TEST_INPUT = """
            ....#.....
            .........#
            ..........
            ..#.......
            .......#..
            ..........
            .#..^.....
            ........#.
            #.........
            ......#...
            """;

    enum Direction {
        NORTH(
                '^',
                loc -> new LocationFacing(loc.x, loc.y - 1, loc.facing)
        ),

        EAST(
                '>',
                loc -> new LocationFacing(loc.x + 1, loc.y, loc.facing)
        ),

        SOUTH(
                'v',
                loc -> new LocationFacing(loc.x, loc.y + 1, loc.facing)
        ),

        WEST(
                '<',
                loc -> new LocationFacing(loc.x - 1, loc.y, loc.facing)
        );

        private final char symbol;
        private final Function<LocationFacing, LocationFacing> facing;

        Direction(char symbol, Function<LocationFacing, LocationFacing> facing) {
            this.symbol = symbol;
            this.facing = facing;
        }

        static Direction fromSymbol(char symbol) {
            return Arrays.stream(Direction.values()).filter(d -> d.symbol == symbol).findFirst().orElseThrow();
        }

        public char getSymbol() {
            return symbol;
        }

        public Function<LocationFacing, LocationFacing> getFacing() {
            return facing;
        }

        public Direction turn() {
            int ord = this.ordinal();
            int next = (ord + 1) % Direction.values().length;
            return Direction.values()[next];
        }
    }

    record LocationFacing(int x, int y, Direction facing) {
    }

    @Override
    public long solve(String input) {
        char[][] map = Arrays.stream(input.split("\\n")).map(String::toCharArray).toArray(char[][]::new);
        List<Character> directionChars = Arrays.stream(Direction.values()).map(Direction::getSymbol).toList();

        LocationFacing startingLoc = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (directionChars.contains(map[i][j])) {
                    startingLoc = new LocationFacing(j, i, Direction.fromSymbol(map[i][j]));
                }
            }
        }

        long count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == startingLoc.y && j == startingLoc.x) continue;

                Set<LocationFacing> visited = new HashSet<>();

                LocationFacing loc = startingLoc;
                visited.add(loc);

                while (true) {
                    LocationFacing facing = loc.facing.getFacing().apply(loc);

                    // exit if block facing is outside the mao
                    if (facing.x >= map[0].length || facing.x < 0 || facing.y >= map.length || facing.y < 0) break;

                    if (map[facing.y][facing.x] == '#' || (facing.y == i && facing.x == j)) {
                        loc = new LocationFacing(loc.x, loc.y, loc.facing.turn());
                        continue;
                    }

                    if (visited.contains(facing)) {
                        count += 1;
                        break;
                    }

                    loc = facing;
                    visited.add(facing);
                }
            }
        }

        return count;
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day06Part2().solve());
    }

}
