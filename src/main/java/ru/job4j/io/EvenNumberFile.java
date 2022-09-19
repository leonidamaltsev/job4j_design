package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            int number;
            for (String line : lines) {
                number = Integer.parseInt(line);
                if (number % 2 == 0) {
                    System.out.println(number + " - even");
                } else {
                    System.out.println(number + " - not even");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
