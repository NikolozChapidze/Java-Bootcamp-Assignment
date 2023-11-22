package dev.omedia.bootcamp.io;

import dev.omedia.bootcamp.annotations.CSV;
import dev.omedia.bootcamp.annotations.Column;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * დამხმარე კლასი csv ფაილიდან მონაცემების წასაკითხად.
 *
 * @param <D> მონაცემების მატარებელი კლასი. შესაბამის კლასს თავზე უნდა ეწეროს @CSV ანოტაცია
 */
public class CSVReader<D> {
    private final Class<? extends D> clazz;

    public CSVReader(Class<? extends D> clazz) {
        this.clazz = clazz;
    }

    /**
     * @return წაკითხული ობიექტების კოლექციას
     * @throws IOException თუ მოხდა io შეცდომა
     */
    public List<D> readData() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(clazz.getAnnotation(CSV.class).source()))) {
            return lines.skip(1)
                    .map(line -> getObject(line.split(","), clazz))
                    .collect(Collectors.toList());
        }
    }


    /**
     * კონკრეტული ხაზზე ჩაწერილი მონაცემს ამოიკითხავს და დააბრუნებს ჯავას ობიექტად.<br />
     * თუ არ დახვდა ხაზზე მონაცემი, <code>Optional</code> იქნება ცარიელი.
     *
     * @param lineIndex ხაზის ინდექსი, საიდანაც მონაცემი უნდა ამოვიკითხოთ. 0 - header.
     * @return შესაბამის კლასის ობიექტს,
     * @throws IllegalArgumentException თუ <code>lineIndex <= 0</code>.
     * @throws IOException              თუ მოხდა io შეცდომა
     */
    public Optional<D> readLine(long lineIndex) throws IOException {
        if (lineIndex <= 0) {
            throw new IllegalArgumentException("incorrect line index");
        }
        try (Stream<String> lines = Files.lines(Path.of(clazz.getAnnotation(CSV.class).source()))) {
            return lines.skip(lineIndex)
                    .limit(1)
                    .map(line -> getObject(line.split(","), clazz))
                    .findFirst();
        }
    }

    private String[] getFieldTypes(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .limit(1)
                    .map(s -> s.split(","))
                    .findFirst()
                    .orElseThrow();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param fromIndex საწყისი ხაზის ინდექსი
     * @param toIndex   საბოლოო ხახის ინდექსი (მდე)
     * @return კონკრეტულ ხაზების შუალედში წაკითხული ობიექტების კოლექციას
     * @throws IllegalArgumentException თუ <code>fromIndex <= 0 || fromIndex >= toIndex</code>.
     * @throws IOException              თუ მოხდა io შეცდომა
     */
    public List<D> readLines(long fromIndex, long toIndex) throws IOException {
        if (fromIndex <= 0 || fromIndex >= toIndex) {
            throw new IllegalArgumentException("incorrect indexes");
        }
        try (Stream<String> lines = Files.lines(Path.of(clazz.getAnnotation(CSV.class).source()))) {
            return lines.limit(toIndex)
                    .skip(fromIndex)
                    .map(line -> getObject(line.split(","), clazz))
                    .collect(Collectors.toList());
        }
    }

    private D getObject(String[] values, Class<? extends D> clazz) {
        Field[] fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class)).toArray(Field[]::new);
        Class<?>[] fieldTypes = Arrays.stream(fields)
                .map(Field::getType).toArray(Class[]::new);

        try {
            Constructor<? extends D> constructor = clazz.getConstructor(fieldTypes);
            Object[] object = new Object[values.length];
            for (int i = 0; i < values.length; i++) {
                object[i] = castObject(fieldTypes[i].getSimpleName(), values[i], fields[i].getAnnotation(Column.class).dataFormat());
            }
            return constructor.newInstance(object);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Object castObject(String typeName, String obj, String dateFormat) {
        switch (typeName) {
            case "int":
            case "Integer":
                return Integer.valueOf(obj);
            case "double":
            case "Double":
                return Double.valueOf(obj);
            case "long":
            case "Long":
                return Long.valueOf(obj);
            case "LocalDateTime":
                return LocalDateTime.parse(obj, DateTimeFormatter.ofPattern(dateFormat));
            case "String":
                return obj;
            default:
                try {
                    Class<?> aClass = Class.forName(typeName);
                    Constructor<?> constructor = aClass.getConstructor(String.class);
                    return constructor.newInstance(obj);
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                         InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
        }
    }
}
