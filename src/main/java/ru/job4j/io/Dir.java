package ru.job4j.io;

import java.io.File;

public class Dir {
    /**
     * Программа выводит имя, размер файлов содержащихся в директории
     * Проверка существования файла: if (!file.exists()...
     * Проверка, что файл - это директория: if (!file.isDirectory()...
     * Получение списка файлов в директории: for (File subfile...
     */
    public static void main(String[] args) {
        File file = new File("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("File name: %s", subfile.getName()));
            System.out.println(String.format("File size: %s", subfile.length()));
        }
    }
}
