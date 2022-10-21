package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    /**
     * "Консольный чат": пользователь вводит фразу, программа выводит случайную фразу в ответ.
     * Программа замолкает если вводится слово "стоп", если вводится слово "продолжить",
     * программа снова отвечает. Программа прекращает работать при вводе слова "закончить".
     * Запись диалога происходит в текстовый лог.
     * @run содержит логику чата;
     * @readPhrases читает фразы из файла;
     * @saveLog() сохраняет лог чата в файл.
     */
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> log = new ArrayList<>();


    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> answers = readPhrases();
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        boolean check = true;
        Random random = new Random();
        String s = "";
        System.out.println("Введите фразу");
        while (!OUT.equals(s)) {
            s = rd.readLine();
            log.add(s);
            if (STOP.equals(s)) {
                check = false;
            }
            if (check) {
                String botAnswer = answers.get(random.nextInt(answers.size()));
                System.out.println(botAnswer);
                log.add(botAnswer);
            }
            if (CONTINUE.equals(s)) {
                check = true;
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(
                path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("./data/log.txt", "./data/botAnsw.txt");
        cc.run();
    }
}
