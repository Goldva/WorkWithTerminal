package exceptions;

public class InsufficientFundsOnTheCardException extends Exception {
    public InsufficientFundsOnTheCardException() {
        super("Недостаточно средств на карте");
    }
}
