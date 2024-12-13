    package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

    public class Day08Part2 extends PuzzleSolver {

        public static final long EXPECTED = 34;
        public static final String TEST_INPUT = """
                ............
                ........0...
                .....0......
                .......0....
                ....0.......
                ......A.....
                ............
                ............
                ........A...
                .........A..
                ............
                ............
                """;

        record Position(int x, int y) {}

        @Override
        public long solve(String input) {
            Map<Character, List<Position>> antennas = new HashMap<>();


            var lines = input.split("\\n");
            int height = lines.length;
            int width = lines[0].length();
            for (int y = 0; y < height; y++) {
                var chars = lines[y].toCharArray();

                for (int x = 0; x < width; x++) {
                    if (chars[x] == '.') continue;

                    antennas.computeIfAbsent(chars[x], (c) -> new ArrayList<>()).add(new Position(x, y));
                }
            }

            Set<Position> antionodes = new HashSet<>();
            for (char frequency : antennas.keySet()) {
                for (int i = 0; i < antennas.get(frequency).size(); i++) {
                    for (int j = 0; j < antennas.get(frequency).size(); j++) {
                        if (i == j) continue;

                        var a1 = antennas.get(frequency).get(i);
                        var a2 = antennas.get(frequency).get(j);

                        var dX = a2.x() - a1.x();
                        var dY = a2.y() - a1.y();

                        antionodes.add(a1);
                        antionodes.add(a2);

                        while (!((a2.x() + dX) >= width || (a2.x() + dX) < 0 || (a2.y() + dY) >= height || (a2.y() + dY) < 0)) {
                            var antionde = new Position(a2.x() + dX, a2.y() + dY);
                            antionodes.add(antionde);
                            a2 = antionde;
                        }
                    }
                }
            }

            return antionodes.size();
        }

        @Test
        void test() {
            assertEquals(EXPECTED, solve(TEST_INPUT));
        }

        public static void main(String[] args) {
            System.out.println(new Day08Part2().solve());
        }

    }
