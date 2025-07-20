package file;

import file.exception.FileProcessingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileProcessor {

    private static final StandardOpenOption[] FILE_WRITE_OPTIONS =
            {StandardOpenOption.CREATE, StandardOpenOption.APPEND};

    public List<String> readFile(String filename) {
        try {
            Path filePath = Path.of(filename);
            return Files.readAllLines(filePath);
        } catch (IOException | InvalidPathException ex) {
            throw new FileProcessingException(ex.getMessage(), ex);
        }
    }

    public void appendToFile(String filename, String content) {
        try {
            Path filePath = Path.of(filename);
            Files.writeString(filePath, content, FILE_WRITE_OPTIONS);
        } catch (IOException ex) {
            throw new FileProcessingException(ex.getMessage(), ex);
        }
    }
}
