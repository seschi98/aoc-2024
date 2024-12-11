package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Part2 extends PuzzleSolver {

    public static final long EXPECTED = 48;
    public static final String TEST_INPUT = """
            xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
            """;

    @Override
    public long solve(String input) {
        Pattern p = Pattern.compile("(mul\\((?<a>\\d+),(?<b>\\d+)\\))|(?<do>do\\(\\))|(?<dont>don't\\(\\))");

        Matcher matcher = p.matcher(input);
        long result = 0;
        boolean active = true;
        while(matcher.find()) {
            if(matcher.group("do") != null) {
                active = true;
                continue;
            }
            if(matcher.group("dont") != null) {
                active = false;
                continue;
            }

            if(!active) continue;

            result += Long.parseLong(matcher.group("a")) * Long.parseLong(matcher.group("b"));
        }

        return result;
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day03Part2().solve());
    }

}
