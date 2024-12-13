package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Part1 extends PuzzleSolver {

    public static final long EXPECTED = 3749;
    public static final String TEST_INPUT = """
            190: 10 19
            3267: 81 40 27
            83: 17 5
            156: 15 6
            7290: 6 8 6 15
            161011: 16 10 13
            192: 17 8 14
            21037: 9 7 18 13
            292: 11 6 16 20
            """;

    private boolean equasionIsRight(Long test, List<Long> numbers, Long acc) {
        if (numbers.isEmpty()) return Objects.equals(test, acc);

        return equasionIsRight(test, numbers.subList(1, numbers.size()), acc + numbers.getFirst())
                || equasionIsRight(test, numbers.subList(1, numbers.size()), acc * numbers.getFirst());
    }

    @Override
    public long solve(String input) {
        return Arrays.stream(input.split("\\n"))
                .mapToLong(equation -> {
                    var spl = equation.split(":\\s+");
                    long test = Long.parseLong(spl[0]);
                    List<Long> numbers = Arrays.stream(spl[1].split("\\s+")).map(Long::parseLong).toList();

                    if(equasionIsRight(test, numbers.subList(1, numbers.size()), numbers.getFirst()))
                    {
                        return test;
                    }

                    return 0;
                })
                .sum();
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day07Part1().solve());
    }

}
