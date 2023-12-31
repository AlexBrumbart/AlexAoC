package de.alexbrumbart.aoc23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;

public final class Main {
    @SuppressWarnings({"java:S106", "java:S125"})
    public static void main(String[] args) {
//        System.out.println("Tag 1, A1: " + day01A());
//        System.out.println("Tag 1, A2: " + day01B());
//        System.out.println("Tag 2, A1: " + day02A());
//        System.out.println("Tag 2, A2: " + day02B());
//        System.out.println("Tag 3, A1: " + day03A());
//        System.out.println("Tag 3, A2: " + day03B());
//        System.out.println("Tag 4, A1: " + day04A());
//        System.out.println("Tag 4, A2: " + day04B());
//        System.out.println("Tag 5, A1: " + day05A());
//        System.out.println("Tag 5, A2: " + day05B());
        System.out.println("Tag 6, A1: " + day06A());
        System.out.println("Tag 6, A2: " + day06B());
    }

    public static List<String> readInput(String name) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Main.class.getResourceAsStream(name))))) {
            List<String> list = new ArrayList<>();
            while (reader.ready())
                list.add(reader.readLine());

            return list;
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    public static int day01A() {
        var input = readInput("day1_1.txt");

        return input.parallelStream().map(s -> s.replaceAll("[a-z,A-Z]", "")).mapToInt(s -> {
            char a = s.charAt(0);
            char b = s.charAt(s.length() - 1);

            return Integer.parseInt(String.valueOf(a) + b);
        }).sum();
    }

    public static int day01B() {
        var input = readInput("day1_2.txt");

        return input.parallelStream()
                .map(s -> s.replace("one", "o1e"))
                .map(s -> s.replace("two", "t2o"))
                .map(s -> s.replace("three", "t3e"))
                .map(s -> s.replace("four", "f4r"))
                .map(s -> s.replace("five", "f5e"))
                .map(s -> s.replace("six", "s6x"))
                .map(s -> s.replace("seven", "s7n"))
                .map(s -> s.replace("eight", "e8t"))
                .map(s -> s.replace("nine", "n9e"))
                .map(s -> s.replaceAll("[a-z,A-Z]", "")).mapToInt(s -> {
                    char a = s.charAt(0);
                    char b = s.charAt(s.length() - 1);

                    return Integer.parseInt(String.valueOf(a) + b);
                }).sum();
    }

    public static int day02A() {
        var input = readInput("day2_1.txt");

        return input.stream().map(CubeGame::readInputLine).filter(cubeGame -> cubeGame.possibleWith(12, 13, 14)).mapToInt(CubeGame::id).sum();
    }

    public static int day02B() {
        var input = readInput("day2_2.txt");

        return input.stream().map(CubeGame::readInputLine).map(CubeGame::minNeeded).mapToInt(i -> i[0] * i[1] * i[2]).sum();
    }

    public static int day03A() {
        var input = readInput("day3_1.txt");

        return EngineSchematic.readInput(input).findParts().stream().mapToInt(value -> value).sum();
    }

    public static int day03B() {
        var input = readInput("day3_2.txt");

        return EngineSchematic.readInput(input).findGears().stream().mapToInt(value -> value.getValue() * value.getKey()).sum();
    }

    public static Object day04A() {
        var input = readInput("day4_1.txt");

        return input.stream()
                .map(s -> s.split(":")[1])
                .map(s -> {
                    var split = s.split("\\|");

                    return new AbstractMap.SimpleEntry<>(
                            Arrays.stream(split[0].split(" ")).filter(s1 -> !s1.isEmpty()).map(Integer::parseInt).toList(),
                            Arrays.stream(split[1].split(" ")).filter(s1 -> !s1.isEmpty()).map(Integer::parseInt).toList());
                })
                .mapToInt(value -> {
                    int i = 0;
                    List<Integer> keys = value.getKey();
                    List<Integer> values = value.getValue();

                    for (int j : values) {
                        if (keys.contains(j))
                            i = (i == 0) ? 1 : i * 2;
                    }

                    return i;
                }).sum();
    }

    public static int day04B() {
        var input = readInput("day4_2.txt");
        int[] counts = new int[input.size()];
        Arrays.fill(counts, 1);

        var list = input.stream()
                .map(s -> s.split(":")[1])
                .map(s -> {
                    var split = s.split("\\|");

                    return new AbstractMap.SimpleEntry<>(
                            Arrays.stream(split[0].split(" ")).filter(s1 -> !s1.isEmpty()).map(Integer::parseInt).toList(),
                            Arrays.stream(split[1].split(" ")).filter(s1 -> !s1.isEmpty()).map(Integer::parseInt).toList());
                }).toList();

        int a = 0;
        for (var x : list) {
            int i = 0;
            List<Integer> keys = x.getKey();
            List<Integer> values = x.getValue();

            for (int j : values) {
                if (keys.contains(j))
                    ++i;
            }

            for (int b = 1; b <= i; ++b) {
                if (a + b < input.size()) {
                    counts[a + b] += counts[a];
                }
            }

            ++a;
        }

        int count = 0;
        for (int i : counts)
            count += i;

        return count;
    }

    @SuppressWarnings("java:S127")
    public static long day05A() {
        var input = readInput("day5_1.txt");

        List<Long> seeds = new ArrayList<>();
        var seedStrings = input.get(0).split(" ");
        for (int i = 1; i < seedStrings.length; i++) {
            seeds.add(Long.parseLong(seedStrings[i]));
        }

        input.remove(0);

        List<AlmanacMap> maps = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (int i = 2; i < input.size(); i++) {
            var s = input.get(i);
            if (s.isEmpty()) {
                maps.add(AlmanacMap.readInputLine(temp));
                temp.clear();
                i++;

                continue;
            }

            temp.add(s);
        }

        maps.add(AlmanacMap.readInputLine(temp));

        long lowest = Long.MAX_VALUE;
        for (var i : seeds) {
            long l = i;
            for (var map : maps) {
                l = map.findMapping(l);
            }

            if (l < lowest)
                lowest = l;
        }

        return lowest;
    }

    @SuppressWarnings("java:S127")
    public static long day05B() {
        var input = readInput("day5_1.txt");

        List<Long> seeds = new ArrayList<>();
        var seedStrings = input.get(0).split(" ");
        for (int i = 1; i < seedStrings.length; i++) {
            seeds.add(Long.parseLong(seedStrings[i]));
        }

        input.remove(0);

        List<AlmanacMap> maps = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        for (int i = 2; i < input.size(); i++) {
            var s = input.get(i);
            if (s.isEmpty()) {
                maps.add(AlmanacMap.readInputLine(temp));
                temp.clear();
                i++;

                continue;
            }

            temp.add(s);
        }

        maps.add(AlmanacMap.readInputLine(temp));

        long lowest = Long.MAX_VALUE;
        for (int i = 0; i < seeds.size(); i += 2) {
            long start = seeds.get(i);
            long range = seeds.get(i + 1);

            lowest = Math.min(lowest, LongStream.range(start, start + range).parallel().map(operand -> {
                long l = operand;
                for (var map : maps) {
                    l = map.findMapping(l);
                }

                return l;
            }).reduce(Long.MAX_VALUE, Math::min));
        }

        return lowest;
    }

    public static int day06A() {
        var input = readInput("day6_1.txt");

        var line1 = Arrays.stream(input.get(0).split(" ")).filter(s -> !s.isEmpty()).toList();
        var line2 = Arrays.stream(input.get(1).split(" ")).filter(s -> !s.isEmpty()).toList();
        List<int[]> races = new ArrayList<>();
        for (int i = 1; i < line1.size(); i++) {
            races.add(new int[]{Integer.parseInt(line1.get(i)), Integer.parseInt(line2.get(i))});
        }

        int totalSolutions = 1;
        for (var race : races) {
            int solutions = 0;
            for (int i = 0; i <= race[0]; i++) {
                if (i * (race[0] - i) > race[1])
                    solutions++;
            }

            totalSolutions *= solutions;
        }

        return totalSolutions;
    }

    public static int day06B() {
        var input = readInput("day6_2.txt");

        var time = Integer.parseInt(input.get(0).split(":")[1].replace(" ", ""));
        var goal = Long.parseLong(input.get(1).split(":")[1].replace(" ", ""));

        int solutions = 0;
        for (long i = 0; i <= time; i++) {
            if (i * (time - i) > goal)
                solutions++;
        }

        return solutions;
    }
}
