package consoleui;

import caesar.CaesarCoder;
import caesar.exception.CaesarCodeingException;
import file.exception.FileProcessingException;
import consoleui.exception.InvalidUserInputException;

import java.util.Scanner;

public class ConsoleDialogue {

    private static final String WELCOME_MESSAGE = "Welcome to the console!";
    private static final String OPERATION_PATTRN = "%d - %s;";
    private static final String TRY_AGAIN_COMMAND = "again";

    private final Scanner in;

    private final CaesarCoder caesarCoder;

    public ConsoleDialogue() {
        in = new Scanner(System.in);
        caesarCoder = new CaesarCoder();
    }

    public void start(){
        showMenu();
        Operation operation = readOperation();
        processOperation(operation);
    }

    private void showMenu() {
        System.out.println(WELCOME_MESSAGE);
        System.out.println("Choose next option to continue");
        for (Operation operation : Operation.values()) {
            String message = String.format(OPERATION_PATTRN, operation.getNumber(), operation.getDescription());
            System.out.println(message);
        }
    }

private Operation readOperation() {
    boolean shouldTryAgain = false;
    do {
        try {
            int option = readInt();
            return Operation.getByNumber(option);
        } catch (IllegalArgumentException | InvalidUserInputException ex) {
            System.out.println("Operation number is wrong");
            System.out.println("Reason: " + ex.getMessage());
            System.out.println("Ener 'again' to try again and something other for exit");

            String input = readString();
            if (TRY_AGAIN_COMMAND.equalsIgnoreCase(input)) {
                shouldTryAgain = true;
            }
        }
    } while (shouldTryAgain);
    return Operation.EXIT;
}

private void processOperation(Operation operation) {
        switch (operation) {
            case EXIT -> processExit();
            case ENCRYPTION -> processEncryptionOperation();
            case DECRYPTION -> processDecryptionOperation();
        }
}

private void processEncryptionOperation() {
    System.out.println("Enter filenname which you want to encrypt");
    String inputFilename = readString();
    System.out.println("Enter filename which will be used for result saving");
    String outputFilename = in.nextLine();
    System.out.println("Enter key");
    int key = readInt();
    try {
        caesarCoder.encrypt(inputFilename, outputFilename, key);
        System.out.println("Done");
    } catch (FileProcessingException | CaesarCodeingException ex) {
        System.err.println("Error happened. Reason: " + ex.getMessage());
        ex.printStackTrace();
    }
}

private void processDecryptionOperation() {
        System.out.println("Enter filename which you want to decrypt");
        String inputFilename = readString();
        System.out.println("Enter filename which will be used for result saving");
        String outputFilename = readString();
        System.out.println("Enter key");
        int key = readInt();
    try {
        caesarCoder.decrypt(inputFilename, outputFilename, key);
        System.out.println("Done");
    } catch (FileProcessingException | CaesarCodeingException ex) {
        System.err.println("Error happened. Reason: " + ex.getMessage());
        ex.printStackTrace();
    }
    }

    private void processExit() {
        System.out.println("Goodbye");
}

private int readInt() {
        String input = in.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new InvalidUserInputException("Integer value is wrong", ex);
        }
}

private String readString() {
        return in.nextLine();
    }

}
