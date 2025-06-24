package com.espol.group05.grupo_05.Utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public class Literacy {
    
    public static <T> DoubleCircularList<T> readFile(String filePath, Function<String[], T> mapper, String delimiter) throws IOException {
        DoubleCircularList<T> result = new DoubleCircularList<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.filter(line -> !line.trim().isEmpty())
                .map(line -> {
                    String[] parts = line.split(delimiter);
                    return mapper.apply(parts);
                })
                .forEach(result::add);
        }
    
        return result;
    }

    public static <E> void writeFile(DoubleCircularList<E> list,
                                    String filePath,
                                    Function<E, String> toStringConverter,
                                    String delimiter) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            boolean firstElement = true;

            for (E element : list) {
                if (!firstElement) {
                    writer.write(delimiter);
                }
                writer.write(toStringConverter.apply(element));
                firstElement = false;
            }
        }
    }

}