package de.alexbrumbart.aoc23;

import java.util.ArrayList;
import java.util.List;

public record CubeGame(int id, List<int[]> pulls) {
    public boolean possibleWith(int r, int g, int b) {
        for (var pull : pulls) {
            if (pull[0] > r || pull[1] > g || pull[2] > b)
                return false;
        }

        return true;
    }

    public int[] minNeeded() {
        int[] min = new int[3];
        for (var pull : pulls) {
            if (pull[0] > min [0])
                min[0] = pull[0];

            if (pull[1] > min [1])
                min[1] = pull[1];

            if (pull[2] > min [2])
                min[2] = pull[2];
        }

        return min;
    }

    public static CubeGame readInputLine(String input) {
        var inputs = input.split(":");
        var pulls = inputs[1].split(";");

        List<int[]> list = new ArrayList<>();
        for (var pull : pulls) {
            int[] counts = new int[3]; // ordered in rgb

            var l = pull.split(",");
            for (var s : l) {
                if (s.contains("red")) {
                    s = s.replaceAll("[a-z]", "");
                    counts[0] = Integer.parseInt(s.strip());
                } else if (s.contains("green")) {
                    s = s.replaceAll("[a-z]", "");
                    counts[1] = Integer.parseInt(s.strip());
                } else {
                    s = s.replaceAll("[a-z]", "");
                    counts[2] = Integer.parseInt(s.strip());
                }
            }

            list.add(counts);
        }

        return new CubeGame(Integer.parseInt(inputs[0].replaceAll("[a-z, A-Z]", "").strip()), list);
    }
}
