package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Part1 extends PuzzleSolver {

    public static final long EXPECTED = 41;
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
                loc -> new Location(loc.x, loc.y - 1)
        ),

        EAST(
                '>',
                loc -> new Location(loc.x + 1, loc.y)
        ),

        SOUTH(
                'v',
                loc -> new Location(loc.x, loc.y + 1)
        ),

        WEST(
                '<',
                loc -> new Location(loc.x - 1, loc.y)
        );

        private final char symbol;
        private final Function<Location, Location> facing;

        Direction(char symbol, Function<Location, Location> facing) {
            this.symbol = symbol;
            this.facing = facing;
        }

        static Direction fromSymbol(char symbol) {
            return Arrays.stream(Direction.values()).filter(d -> d.symbol == symbol).findFirst().orElseThrow();
        }

        public char getSymbol() {
            return symbol;
        }

        public Function<Location, Location> getFacing() {
            return facing;
        }

        public Direction turn() {
            int ord = this.ordinal();
            int next = (ord + 1) % Direction.values().length;
            return Direction.values()[next];
        }
    }

    record Location(int x, int y) {
    }

    @Override
    public long solve(String input) {
        char[][] map = Arrays.stream(input.split("\\n")).map(String::toCharArray).toArray(char[][]::new);
        List<Character> directionChars = Arrays.stream(Direction.values()).map(Direction::getSymbol).toList();

        Set<Location> visited = new HashSet<>();

        Location loc = null;
        Direction direction = null;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (directionChars.contains(map[i][j])) {
                    loc = new Location(j, i);
                    visited.add(loc);
                    direction = Direction.fromSymbol(map[i][j]);
                }
            }
        }

        while (true) {
            Location facing = direction.getFacing().apply(loc);

            // exit if block facing is outside the mao
            if (facing.x >= map[0].length || facing.x < 0 || facing.y >= map.length || facing.y < 0) break;

            if (map[facing.y][facing.x] == '#') {
                direction = direction.turn();
                continue;
            }

            loc = facing;
            visited.add(facing);
        }

        return visited.size();
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day06Part1().solve());
    }

}
