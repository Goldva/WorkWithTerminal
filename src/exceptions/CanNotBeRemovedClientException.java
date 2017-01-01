package exceptions;

public class CanNotBeRemovedClientException extends Exception {
    public CanNotBeRemovedClientException() {
        super("Нельзя удалить клиента, пока у него есть хотябы одна карта");
    }
}
