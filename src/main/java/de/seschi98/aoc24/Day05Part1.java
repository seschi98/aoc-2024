package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Part1 extends PuzzleSolver {

    public static final long EXPECTED = 143;
    public static final String TEST_INPUT = """
            47|53
            97|13
            97|61
            97|47
            75|29
            61|13
            75|53
            29|13
            97|29
            53|29
            61|53
            97|53
            61|29
            47|13
            75|47
            97|75
            47|61
            75|61
            47|29
            75|13
            53|13
            
            75,47,61,53,29
            97,61,53,29,13
            75,29,13
            75,97,47,61,53
            61,13,29
            97,13,75,29,47
            """;

    @Override
    public long solve(String input) {
        String[] parts = input.split("\\n{2,}");

        Map<Integer, List<Integer>> beforeRules = new HashMap<>();
        Arrays.stream(parts[0].split("\\n")).forEach(line -> {
            var s = line.split("\\|");
            beforeRules.computeIfAbsent(Integer.parseInt(s[0]), (k) -> new ArrayList<>()).add(Integer.parseInt(s[1]));
        });


        return Arrays.stream(parts[1].split("\\n")).mapToLong(update -> {
            List<Integer> numbers = Arrays.stream(update.split(",")).map(Integer::parseInt).toList();

            if (numbers.stream().allMatch(
                    n -> {
                        if (!beforeRules.containsKey(n)) return true;

                        return beforeRules.get(n).stream().allMatch(other -> {
                            if (!numbers.contains(other)) return true;

                            return numbers.indexOf(n) < numbers.indexOf(other);
                        });
                    }
            )) {
                return numbers.get((numbers.size() - 1) / 2);
            }

            return 0;
        }).sum();
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day05Part1().solve());
    }

}