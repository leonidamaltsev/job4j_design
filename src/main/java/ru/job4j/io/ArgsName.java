package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    /**
     * Программа принимает массив параметров и разбивает их на пары: ключ, значение
     */

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Invalid key %s", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No arguments entered");
        }
        for (String arg : args) {
            validate(arg);
            String[] pair = arg.split("=", 2);
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    private static void validate(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException(
                    String.format("Incorrect argument: '%s' should starts with '-' sign.", arg));
        }
        if (arg.startsWith("-=")) {
            throw new IllegalArgumentException(
                    String.format("Incorrect argument: '%s' should contains a key.", arg));
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException(
                    String.format("Incorrect argument: '%s' should contains an '=' sign.", arg));
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
