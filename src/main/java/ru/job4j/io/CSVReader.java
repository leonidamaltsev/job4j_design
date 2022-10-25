package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CSVReader {
    
    public static void handle(ArgsName argsName) throws Exception {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String outFile = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");
        validate(argsName);
        int[] index = new int[filter.length];
        StringBuilder stb = new StringBuilder();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(delimiter);
                for (int i = 0; i < filter.length; i++) {
                    for (int j = 0; j < data.length; j++) {
                        if (filter[i].equals(data[j])) {
                            index[i] = j;
                        }
                    }
                }
                for (int i : index) {
                    stb.append(data[i]).append(delimiter);
                }
                stb.deleteCharAt(stb.length() - 1).append(System.lineSeparator());
            }
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(outFile))) {
            if ("stdout".equals(outFile)) {
                System.out.println(stb);
            } else {
                pw.print(stb);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException("The delimiter should be \";\"");
        }
        if (argsName.get("out").isEmpty()) {
            throw new IllegalArgumentException("Out should be \"stdout\"");
        }
        if (argsName.get("filter").isBlank()) {
            throw new IllegalArgumentException("Filter doesn't exist");
        }
    }

    public static void main(String[] args) throws Exception {
        handle(ArgsName.of(args));
    }
}
