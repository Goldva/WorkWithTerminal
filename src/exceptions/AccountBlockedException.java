package exceptions;

public class AccountBlockedException extends Exception {
    public AccountBlockedException() {
        super("Аккаунт заблокирован");
    }
}
