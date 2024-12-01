package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Part1 extends PuzzleSolver {

    public static final Long EXPECTED = 11L;
    public static final String TEST_INPUT = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;

    @Override
    public long solve(String input) {
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();

        input.lines().map(
                l -> l.split("\\s+")
        ).forEach(l -> {
            left.add(Long.parseLong(l[0]));
            right.add(Long.parseLong(l[1]));
        });

        left.sort(Comparator.naturalOrder());
        right.sort(Comparator.naturalOrder());

        long totalDistance = 0;
        for (int i = 0; i < left.size(); i++) {
            totalDistance += Math.abs(left.get(i) - right.get(i));
        }

        return totalDistance;
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day01Part1().solve());
    }

}
