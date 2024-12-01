package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Part2 extends PuzzleSolver {

    public static final Long EXPECTED = 31L;
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
        List<Integer> left = new ArrayList<>();
        Map<Integer, Integer> rightAmounts = new ConcurrentHashMap<>();

        input.lines().map(
                l -> l.split("\\s+")
        ).forEach(l -> {
            left.add(Integer.parseInt(l[0]));
            rightAmounts.put(Integer.parseInt(l[1]), rightAmounts.getOrDefault(Integer.parseInt(l[1]), 0) + 1);
        });

        int similarityScore = 0;
        for (int i = 0; i < left.size(); i++) {
            int number = left.get(i);
            similarityScore += number * rightAmounts.getOrDefault(number, 0);
        }

        return similarityScore;
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day01Part2().solve());
    }

}
