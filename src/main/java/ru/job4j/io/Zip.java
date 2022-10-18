package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    /**
     * Архивирование папки: -d=c:\project\job4j\ в папку: -o=project.zip,
     * с исключением из архивации файлов с расширением class: -e=.class,
     * при этом в качестве ключа передаётся расширение этих файлов.
     * В программе проверяется присутствие аргументов и существование архивируемой директории.
     */

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validate(ArgsName argsName) {
        Path path = Paths.get(argsName.get("d"));
        if (!Files.exists(path)) {
            throw new IllegalArgumentException(String.format("Not exists %s", path));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("Not directory: %s", path));
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException(String.format("Exclusion must start from . : %s", argsName.get("e")));
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("Target file must be zip: %s", argsName.get("o")));
        }
        return false;
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Three arguments are expected");
        }
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        if (validate(argsName)) {
            Path path = Paths.get(argsName.get("d"));
            List<Path> sources = Search.search(
                    path, p -> !p.toFile().getName().endsWith(argsName.get("e")));
            File out = Paths.get(argsName.get("o")).toFile();
            zip.packFiles(sources, out);
        }
    }
}
