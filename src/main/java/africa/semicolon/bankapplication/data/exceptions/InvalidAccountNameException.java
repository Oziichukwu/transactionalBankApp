package africa.semicolon.bankapplication.data.exceptions;

public class InvalidAccountNameException extends BankApplicationException {
    public InvalidAccountNameException() {
    }

    public InvalidAccountNameException(String message) {
        super(message);
    }

    public InvalidAccountNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidAccountNameException(Throwable cause) {
        super(cause);
    }
}
