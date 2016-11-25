package exceptions;

/**
 * Created by ermakov on 25.11.2016.
 */
public class AccountBlockedException extends Exception {
    public AccountBlockedException() {
        super("Аккаунт заблокирован");
    }
}
