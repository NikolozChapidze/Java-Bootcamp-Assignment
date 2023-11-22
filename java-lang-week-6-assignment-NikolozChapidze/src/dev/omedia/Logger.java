package dev.omedia;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Logger {
    private static final String errorLogFile = "src/resources/error.log";

    public static void logError(String errorMessage) {
        try {
            Files.write(Paths.get(errorLogFile)
                    , String.format("%s\n",errorMessage).getBytes()
                    , StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
