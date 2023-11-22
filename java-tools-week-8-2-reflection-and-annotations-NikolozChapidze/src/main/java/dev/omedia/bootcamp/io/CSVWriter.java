package dev.omedia.bootcamp.io;

import dev.omedia.bootcamp.annotations.CSV;
import dev.omedia.bootcamp.annotations.Column;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVWriter<D> {
    private final Class<? extends D> clazz;

    public CSVWriter(Class<? extends D> clazz) {
        this.clazz = clazz;
    }

    /**
     * მხოლოდ ამ მონაცემებს შეინახავს ფაილში.<br />
     * პირველი ხაზი იქნება header row.
     *
     * @param data მონაცემები რისი ჩაწერაც csv ფაილში გვინდა.
     * @throws IOException - თუ მოხდა io შეცდომა
     */
    public void writeData(Iterable<D> data) throws IOException {
        Path path = Path.of(clazz.getAnnotation(CSV.class).source());
        Files.write(path, (String.join(",", getHeaders()) + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        data.forEach(d -> {
            try {
                Files.write(path, (convertData(d) + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * არსებულ მონახემებს მიამატებს გადმოცემულ მონაცემებს.<br />
     * header rows არ დაამატებს.
     *
     * @param data მონაცემები რისი ჩაწერაც csv ფაილში გვინდა.
     * @throws IOException - თუ მოხდა io შეცდომა
     */
    public void appendData(Iterable<D> data) throws IOException {
        Path path = Path.of(clazz.getAnnotation(CSV.class).source());
        data.forEach(d -> {
            try {
                Files.write(path, (convertData(d) + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * არსებულ მონახემებს მიამატებს გადმოცემულ მონაცემს.<br />
     * header rows არ დაამატებს.
     *
     * @param data მონაცემები რისი ჩაწერაც csv ფაილში გვინდა.
     * @throws IOException - თუ მოხდა io შეცდომა
     */
    public void appendData(D data) throws IOException {
        Path path = Path.of(clazz.getAnnotation(CSV.class).source());
        try {
            Files.write(path, (convertData(data) + "\n").getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getHeaders() {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.toList());
    }

    private String convertData(D data) {
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());
        List<String> fieldNames = fields.stream().map(Field::getName).collect(Collectors.toList());

        String[] values = fields.stream().map(f -> {
            f.setAccessible(true);
            try {
                return f.get(data).toString();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).toArray(String[]::new);

        return getHeaders().stream().map(h -> values[fieldNames.indexOf(h)]).collect(Collectors.joining(","));
    }

}
