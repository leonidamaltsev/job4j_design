package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {
    private static final int PUT_TO_CACHE = 1;
    private static final int GET_FROM_CACHE = 2;
    private static final String SET_DIR = "Укажите кэшируемую директорию: ";
    private static final String SET_FILE_NAME = "Укажите название файла: ";
    private static final String MENU = """
            1. Нажмите 1, чтобы загрузить содержимое файла в кэш.
            2. Нажмите 2, чтобы получить содержимое файла из кэша.
            3. Нажмите любую другую клавишу, чтобы выйти из программы.
            """;

    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static void main(String[] args) throws IOException {
        System.out.println(SET_DIR);
        String dirPath = getInput();
        AbstractCache<String, String> dirFileCache = new DirFileCache(dirPath);
        while (true) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(getInput());
            if (userChoice == PUT_TO_CACHE) {
                System.out.println(SET_FILE_NAME);
                String fileName = getInput();
                dirFileCache.put(fileName, dirFileCache.get(fileName));
            } else if (userChoice == GET_FROM_CACHE) {
                System.out.println(SET_FILE_NAME);
                String fileName = getInput();
                System.out.println(dirFileCache.get(fileName));
            } else {
                break;
            }
        }
    }
}
