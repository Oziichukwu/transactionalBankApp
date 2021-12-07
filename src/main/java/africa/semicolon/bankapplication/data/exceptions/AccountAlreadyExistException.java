package africa.semicolon.bankapplication.data.exceptions;

public class AccountAlreadyExistException extends BankApplicationException {
    public AccountAlreadyExistException() {
    }

    public AccountAlreadyExistException(String message) {
        super(message);
    }

    public AccountAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
