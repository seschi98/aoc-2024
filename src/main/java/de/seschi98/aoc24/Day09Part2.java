package de.seschi98.aoc24;

import de.seschi98.aoc24.support.PuzzleSolver;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Part2 extends PuzzleSolver {

    public static final long EXPECTED = 2858;
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

            int id = fileIds.get(right);
            int size = 0;
            int currentIndexRight = right;
            while (fileIds.get(currentIndexRight) == id) {
                currentIndexRight -= 1;
                size += 1;
            }

            int tempLeft = left;
            int beginOfSpace = -1;
            int availableSize = 0;
            while (tempLeft <= right) {
                if (availableSize >= size) break;
                if (fileIds.get(tempLeft) == -1) {
                    if(beginOfSpace == -1) beginOfSpace = tempLeft;

                    availableSize += 1;
                    tempLeft += 1;
                    continue;
                }

                availableSize = 0;
                tempLeft += 1;
                beginOfSpace = -1;
            }

            if (availableSize < size) {
                right = right - size;
                continue;
            }

            for (int i = 0; i < size; i++) {
                fileIds.set(beginOfSpace + i, id);
                fileIds.set(right - i, -1);
            }

            right = right - size;
        }

        long result = 0;
        for (int i = 0; i < fileIds.size(); i++) {
            if (fileIds.get(i) == -1) continue;

            result += fileIds.get(i) * i;
        }

        return result;
    }

    @Test
    void test() {
        assertEquals(EXPECTED, solve(TEST_INPUT));
    }

    public static void main(String[] args) {
        System.out.println(new Day09Part2().solve());
    }

}
