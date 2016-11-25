package exceptions;

/**
 * Created by ermakov on 25.11.2016.
 */
public class IncorrectPinException extends Exception {
    public IncorrectPinException() {
        super("¬веден не верный пин-код");
    }
}
