package exceptions;

public class IncorrectPinException extends Exception {
    public IncorrectPinException() {
        super("¬веден не корректный пин-код");
    }
}
