package africa.semicolon.bankapplication.data.exceptions;

public class AccountDoesNotExistException extends BankApplicationException {

    public AccountDoesNotExistException() {
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
