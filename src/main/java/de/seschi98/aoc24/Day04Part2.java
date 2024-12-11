package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Part2 extends PuzzleSolver {

    public static final long EXPECTED = 9;
    public static final String TEST_INPUT = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
            """;

    @Override
    public long solve(String input) {
        char[][] matrix = Arrays.stream(input.split("\\n")).map(String::toCharArray).toArray(char[][]::new);

        long count = 0;

        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if (matrix[i][j] != 'A') continue;

                if (((matrix[i - 1][j - 1] == 'M' && matrix[i + 1][j + 1] == 'S') || (matrix[i - 1][j - 1] == 'S' && matrix[i + 1][j + 1] == 'M'))

                        &&

                        ((matrix[i - 1][j + 1] == 'M' && matrix[i + 1][j - 1] == 'S') || (matrix[i - 1][j + 1] == 'S' && matrix[i + 1][j - 1] == 'M'))) {
                    count += 1;
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
        System.out.println(new Day04Part2().solve());
    }

}
