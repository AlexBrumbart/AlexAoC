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
        System.out.println(day01A());
        System.out.println(day01B());
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
}
