package ru.job4j.io;

import java.io.*;

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
        try (BufferedReader read = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean startWrite = false;
            while (read.ready()) {
                String[] s = read.readLine().split(" ", 2);
                boolean isAvailable = Integer.parseInt(s[0]) < 400;
                if (!isAvailable == !startWrite) {
                    startWrite = !startWrite;
                    out.append(s[1]).append(';')
                            .append((startWrite ? "" : System.lineSeparator()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("data/server.log", "data/unavailable.csv");
    }
}
