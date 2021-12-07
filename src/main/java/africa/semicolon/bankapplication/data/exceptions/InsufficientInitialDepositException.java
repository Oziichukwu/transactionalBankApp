package africa.semicolon.bankapplication.data.exceptions;

public class InsufficientInitialDepositException extends BankApplicationException {
    public InsufficientInitialDepositException() {
    }

    public InsufficientInitialDepositException(String message) {
        super(message);
    }

    public InsufficientInitialDepositException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientInitialDepositException(Throwable cause) {
        super(cause);
    }
}
