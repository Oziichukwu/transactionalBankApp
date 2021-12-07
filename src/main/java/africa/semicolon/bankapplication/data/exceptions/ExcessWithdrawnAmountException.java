package africa.semicolon.bankapplication.data.exceptions;

public class ExcessWithdrawnAmountException extends BankApplicationException {
    public ExcessWithdrawnAmountException() {
    }

    public ExcessWithdrawnAmountException(String message) {
        super(message);
    }

    public ExcessWithdrawnAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcessWithdrawnAmountException(Throwable cause) {
        super(cause);
    }
}
