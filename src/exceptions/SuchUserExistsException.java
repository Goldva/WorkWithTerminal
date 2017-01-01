package exceptions;

public class SuchUserExistsException extends Exception {
    public SuchUserExistsException() {
        super("Клиент с таким именем уже существует");
    }
}
