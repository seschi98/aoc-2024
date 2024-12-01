package de.seschi98.aoc24.template;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProblemTemplate extends PuzzleSolver {

    public static final long EXPECTED = 0;
    public static final String TEST_INPUT = """
            """;

    @Override
    public long solve(String input) {
        return 0;
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new ProblemTemplate().solve());
    }

}
