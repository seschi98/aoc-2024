package de.seschi98.aoc24.support;

import java.io.IOException;
import java.io.InputStream;

public abstract class PuzzleSolver {

    public String getInputFromFile() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(getClass().getSimpleName().toLowerCase().split("part")[0] + ".txt")) {
            if (is == null) {
                throw new RuntimeException("Failed to read file: InputStream is null");
            }

            return new String(is.readAllBytes());
        } catch (IOException ex) {
            throw new RuntimeException("Failed to read file: ", ex);
        }
    }

    public long solve() {
        return solve(getInputFromFile());
    }

    public abstract long solve(String input);

}
