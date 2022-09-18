package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ResultFile {

    public static int[][] multiple(int size) {
        int[][] multiple = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                multiple[i][j] = (i + 1) * (j + 1);
            }
        }
        return multiple;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multiple.txt")) {
            out.write(Arrays.deepToString(ResultFile.multiple(4)).getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
