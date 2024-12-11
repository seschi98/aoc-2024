package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Part1 extends PuzzleSolver {

    public static final long EXPECTED = 18;
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

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 'X') continue;

                // right
                if (j + 3 < matrix[i].length && matrix[i][j + 1] == 'M' && matrix[i][j + 2] == 'A' && matrix[i][j + 3] == 'S')
                    count += 1;

                // left
                if (j - 3 >= 0 && matrix[i][j - 1] == 'M' && matrix[i][j - 2] == 'A' && matrix[i][j - 3] == 'S')
                    count += 1;

                // down
                if (i + 3 < matrix.length && matrix[i + 1][j] == 'M' && matrix[i + 2][j] == 'A' && matrix[i + 3][j] == 'S')
                    count += 1;

                // up
                if (i - 3 >= 0 && matrix[i - 1][j] == 'M' && matrix[i - 2][j] == 'A' && matrix[i - 3][j] == 'S')
                    count += 1;

                // down right
                if (j + 3 < matrix[i].length && i + 3 < matrix.length && matrix[i + 1][j + 1] == 'M' && matrix[i + 2][j + 2] == 'A' && matrix[i + 3][j + 3] == 'S')
                    count += 1;

                // down left
                if (j - 3 >= 0 && i + 3 < matrix.length && matrix[i + 1][j - 1] == 'M' && matrix[i + 2][j - 2] == 'A' && matrix[i + 3][j - 3] == 'S')
                    count += 1;

                // up right
                if (j + 3 < matrix[i].length && i - 3 >= 0 && matrix[i - 1][j + 1] == 'M' && matrix[i - 2][j + 2] == 'A' && matrix[i - 3][j + 3] == 'S')
                    count += 1;

                // up left
                if (j - 3 >= 0 && i - 3 >= 0 && matrix[i - 1][j - 1] == 'M' && matrix[i - 2][j - 2] == 'A' && matrix[i - 3][j - 3] == 'S')
                    count += 1;

            }
        }

        return count;
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day04Part1().solve());
    }

}
