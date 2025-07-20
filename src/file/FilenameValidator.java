package file;

import file.exception.FileProcessingException;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class FilenameValidator {

    private static final List<String> FORBITTEN_DIRS_FILES =
            List.of("Windows", "Program Files", "DELL");

    public void validateForWriting(String filename) {
        Path path = validatePath(filename);

        if (Files.exists(path)) {
            if (Files.isDirectory(path)) {
                throw new FileProcessingException("File " + path + " is directory");
            }

            if (!Files.isWritable(path)) {
                throw new FileProcessingException("File " + path + " is not accessible for writing");
            }
        }
    }

    public void validateForReading(String filename) {
        Path path = validatePath(filename);

        if (Files.notExists(path)) {
            throw new FileProcessingException("File " + path + " doesn't exist");
        }
        if (Files.isDirectory(path)) {
            throw new FileProcessingException("File " + path + " is directory");
        }
        if (!Files.isReadable(path)) {
            throw new FileProcessingException("File " + path + " is not readable");
        }
    }

    private Path validatePath(String filename) {
        for (String pathPath : filename.split(System.getProperty("file.separator"))) {
            if (FORBITTEN_DIRS_FILES.contains(pathPath)) {
                throw new FileProcessingException("File " + pathPath + " path is forbidden");
            }
        }
        try {
            Path path = Path.of(filename);
            return path;
        } catch (InvalidPathException ex) {
            throw new FileProcessingException("Invalid path. Reason " + ex.getMessage());
        }
    }
}
