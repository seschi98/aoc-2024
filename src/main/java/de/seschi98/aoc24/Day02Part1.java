package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Part1 extends PuzzleSolver {

    public static final long EXPECTED = 2;
    public static final String TEST_INPUT = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            """;

    enum Direction {
        INCREASING, DECREASING;
    }

    private boolean isSafe(Integer[] report) {
        Direction direction = null;
        for (int i = 1; i < report.length; i++) {
            if (Math.abs(report[i - 1] - report[i]) > 3) return false;
            if (Objects.equals(report[i - 1], report[i])) return false;

            Direction thisDirection = report[i - 1] < report[i] ? Direction.INCREASING : Direction.DECREASING;
            if (direction != null && direction != thisDirection) return false;

            direction = thisDirection;
        }

        return true;
    }

    @Override
    public long solve(String input) {
        return Arrays.stream(input.split("\\n")).map(
                l -> Arrays.stream(l.split("\\s+")).map(Integer::parseInt).toArray(Integer[]::new)
        ).filter(this::isSafe).count();
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day02Part1().solve());
    }

}
