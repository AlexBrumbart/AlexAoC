package de.alexbrumbart.aoc23;

import java.util.ArrayList;
import java.util.List;

public record AlmanacMap(List<long[]> mappings) {
    public long findMapping(long l) {
        for (var longs : mappings) {
            if (l >= longs[1] && l < longs[1] + longs[2]) {
                l += (longs[0] - longs[1]);

                break;
            }
        }

        return l;
    }

    public static AlmanacMap readInputLine(List<String> inputs) {
        List<long[]> mappings = new ArrayList<>();
        for (var s : inputs) {
            var a = s.split(" ");
            mappings.add(new long[] {Long.parseLong(a[0]), Long.parseLong(a[1]), Long.parseLong(a[2])});
        }

        return new AlmanacMap(mappings);
    }
}
