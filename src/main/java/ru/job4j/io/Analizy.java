package ru.job4j.io;

import java.io.*;
import java.util.List;


public class Analizy {
    /**
     * Метод unavailable находит диапазоны не работы сервера
     * Диапазоном считается последовательность статусов 400, 500
     * Сервер работает при статусе 400 или 500
     * Сервер не работает при статусе 200 или 300
     * Файл server.log содержит последовательность диапазонов
     * Результат анализа записан в файл unavailable.csv
     */
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            PrintWriter out = new PrintWriter(new FileOutputStream(target));
            List<String> lines = read.lines().toList();
            boolean startWrite = false;
            for (String line : lines) {
                boolean isAvailable = line.startsWith("200") || line.startsWith("300");
                if (!isAvailable && !startWrite) {
                    out.write(String.format("%s;", line.split(" ")[1]));
                    startWrite = true;
                } else if (isAvailable && startWrite) {
                    out.write(String.format("%s;%n", line.split(" ")[1]));
                    startWrite = false;
                }
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("data/server.log", "data/unavailable.csv");
    }
}
