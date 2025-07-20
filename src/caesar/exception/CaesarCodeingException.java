package caesar.exception;

public class CaesarCodeingException extends RuntimeException {

    String reason;

    public CaesarCodeingException(String reason) {
        this.reason = reason;
    }

    public CaesarCodeingException(String reason, Throwable cause) {
        super(cause);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
