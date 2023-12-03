package de.alexbrumbart.aoc23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Main {
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {
        System.out.println("Tag 1, A1: " + day01A());
        System.out.println("Tag 1, A2: " + day01B());
        System.out.println("Tag 2, A1: " + day02A());
        System.out.println("Tag 2, A2: " + day02B());
        System.out.println("Tag 3, A1: " + day03A());
        System.out.println("Tag 3, A2: " + day03B());
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
}
