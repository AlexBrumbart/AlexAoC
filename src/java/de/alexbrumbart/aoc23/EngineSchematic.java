package de.alexbrumbart.aoc23;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("java:S6218")
public record EngineSchematic(char[][] schematic) {
    private static final Set<Character> chars = new HashSet<>(List.of('*', '#', '+', '-', '=', '/', '$', '%', '@', '&'));
    private static final Set<Character> numbers = new HashSet<>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

    public Collection<Integer> findParts() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < schematic.length; ++i) {
            for (int j = 0; j < schematic[0].length; ++j) {
                char c = schematic[i][j];

                if (chars.contains(c)) {
                    list.addAll(findPart(i, j));
                }
            }
        }

        return list;
    }

    private List<Integer> findPart(int i, int j) {
        List<Integer> list = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                int i2 = i + x;
                int j2 = j + y;

                if (i2 < 0 || i2 > schematic.length || j2 < 0 || j2 > schematic[0].length)
                    continue;

                if (numbers.contains(schematic[i2][j2])) {
                    readFoundPart(list, i2, j2);
                }
            }
        }

        return list;
    }

    private void readFoundPart(List<Integer> list, int i, int j) {
        int a = j;
        while (a > 0 && numbers.contains(schematic[i][a - 1])) {
            --a;
        }

        StringBuilder builder = new StringBuilder();
        while (a < schematic[0].length && numbers.contains(schematic[i][a])) {
            builder.append(schematic[i][a]);
            schematic[i][a] = ' ';
            ++a;
        }

        if (!builder.isEmpty())
            list.add(Integer.parseInt(builder.toString()));
    }

    public Collection<Map.Entry<Integer, Integer>> findGears() {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < schematic.length; ++i) {
            for (int j = 0; j < schematic[0].length; ++j) {
                char c = schematic[i][j];

                if (c == '*') {
                    var gear = findGear(i, j);
                    if (gear != null)
                        list.add(gear);
                }
            }
        }

        return list;
    }

    private Map.Entry<Integer, Integer> findGear(int i, int j) {
        List<Integer> list = new ArrayList<>();
        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                int i2 = i + x;
                int j2 = j + y;

                if (i2 < 0 || i2 > schematic.length || j2 < 0 || j2 > schematic[0].length)
                    continue;

                if (numbers.contains(schematic[i2][j2])) {
                    readFoundPart(list, i2, j2);
                }
            }
        }

        return list.size() == 2 ? new AbstractMap.SimpleEntry<>(list.get(0), list.get(1)) : null;
    }

    public static EngineSchematic readInput(List<String> inputs) {
        char[][] schematic = new char[inputs.size()][inputs.get(0).length()];
        for (int i = 0; i < inputs.size(); ++i) {
            var s = inputs.get(i);
            for (int j = 0; j < s.length(); ++j) {
                char c = s.charAt(j);
                schematic[i][j] = c == '.' ? ' ' : c;
            }
        }

        return new EngineSchematic(schematic);
    }
}
