package ru.job4j.io;

import java.io.FileOutputStream;
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
            int[][] str = multiple(9);
            for (int[] multiple : str) {
                out.write(Arrays.toString(multiple).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
