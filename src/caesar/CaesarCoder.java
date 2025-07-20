package caesar;

import file.FileProcessor;
import file.FilenameValidator;

import java.util.List;

public class CaesarCoder {
    private CaesarCipher caesarCipher;
    private FilenameValidator validator;
    private FileProcessor fileProcessor;

    public CaesarCoder() {
        this.caesarCipher = new CaesarCipher(new CaesarAlphabet());
        this.validator = new FilenameValidator();
        this.fileProcessor = new FileProcessor();
    }

    public void encrypt(String inputFileName, String outputFileName, int key) {
        validator.validateForReading(inputFileName);
        validator.validateForWriting(outputFileName);

        List<String> sourceLines = fileProcessor.readFile(inputFileName);
        for (String sourceline : sourceLines) {
            String encryptedLine = caesarCipher.encrypt(sourceline, key);
            fileProcessor.appendToFile(outputFileName, encryptedLine);
        }
    }

    public void decrypt(String inputFileName, String outputFileName, int key) {
        validator.validateForReading(inputFileName);
        validator.validateForWriting(outputFileName);

        List<String> sourceLines = fileProcessor.readFile(inputFileName);
        for (String sourceline : sourceLines) {
            String decryptedLine = caesarCipher.decrypt(sourceline, key);
            fileProcessor.appendToFile(outputFileName, decryptedLine);
        }
    }
}
