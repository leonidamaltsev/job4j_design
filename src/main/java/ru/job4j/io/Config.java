package ru.job4j.io;

import ru.job4j.collection.SimpleList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String str;
            while ((str = in.readLine()) != null) {
                if (!str.isEmpty() && !str.startsWith("#")) {
                    int rsl = str.indexOf("=");
                    if (rsl <= 0 || rsl == str.length() - 1) {
                        throw new IllegalArgumentException("Нарушение шаблона в строке" + str);
                    }
                    String key = str.substring(0, rsl);
                    String value = str.substring(rsl + 1);
                    values.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new NoSuchElementException("Wrong key");
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public Map<String, String> getValues() {
        return values;
    }
}
