package dev.omedia.bootcamp.io;

import dev.omedia.bootcamp.Data;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CSVReaderTest {
    private final static Path PATH = Path.of(Data.SOURCE_FILE);

    @Before
    public void beforeEach() throws IOException {
        Files.write(PATH, "id\n1\n2\n3\n4".getBytes(), TRUNCATE_EXISTING, CREATE);
    }

    @Test
    public void testReadData() throws IOException {

        final CSVReader<Data> reader = new CSVReader<>(Data.class);
        final List<Data> data = reader.readData();

        final int id = data.get(0).getId();
        final int expected = 1;

        assertEquals(expected, id);
    }

    @Test
    public void testReadLine() throws IOException {
        final CSVReader<Data> reader = new CSVReader<>(Data.class);
        final Optional<Data> data = reader.readLine(1L);

        assertTrue(data.isPresent());

        final int id = data.get().getId();
        final int expected = 1;

        assertEquals(expected, id);
    }

    @Test
    public void testReadLines() throws IOException {

        final CSVReader<Data> reader = new CSVReader<>(Data.class);
        final List<Data> data = reader.readLines(1L, 3L);

        final int size = data.size();
        final int expected = 2;

        assertEquals(expected, size);
    }
}