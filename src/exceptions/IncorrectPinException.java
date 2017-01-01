package exceptions;

public class IncorrectPinException extends Exception {
    public IncorrectPinException() {
        super("Введен не корректный пин-код");
    }
}
