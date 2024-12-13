package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Part1 extends PuzzleSolver {

    public static final long EXPECTED = 1928;
    public static final String TEST_INPUT = """
            2333133121414131402
            """;

    @Override
    public long solve(String input) {
        List<Integer> fileIds = new ArrayList<>();

        int fileId = 0;
        for (int i = 0; i < input.trim().length(); i++) {
            var number = Integer.parseInt(String.valueOf(input.charAt(i)));
            if (i % 2 == 0) {
                for (int x = 0; x < number; x++) fileIds.add(fileId);
                fileId += 1;
            } else {
                for (int x = 0; x < number; x++) fileIds.add(-1);
            }
        }

        int left = 0;
        int right = fileIds.size() - 1;

        while (left <= right) {
            if (fileIds.get(left) >= 0) {
                left += 1;
                continue;
            }

            if (fileIds.get(right) == -1) {
                right -= 1;
                continue;
            }

            fileIds.set(left, fileIds.get(right));
            fileIds.set(right, -1);
        }

        long result = 0;
        for (int i = 0; i < fileIds.size(); i++) {
            if (fileIds.get(i) == -1) break;

            result += fileIds.get(i) * i;
        }

        return result;
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day09Part1().solve());
    }

}
