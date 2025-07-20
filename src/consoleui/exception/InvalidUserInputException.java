package consoleui.exception;

public class InvalidUserInputException extends RuntimeException {
    String reason;

    public InvalidUserInputException(String reason) {
        this.reason = reason;
    }

    public InvalidUserInputException(String reason, Throwable cause) {
        super(cause);
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }
}
