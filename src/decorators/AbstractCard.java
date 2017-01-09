package decorators;

import exceptions.AccountBlockedException;
import exceptions.IncorrectPinException;
import exceptions.InsufficientFundsOnTheCardException;

import java.util.Calendar;

public abstract class AbstractCard {
    protected String numberCard;
    protected int pin;
    protected volatile int money;
    protected Calendar howLongBlockedCard;
    protected int countIncorrectPin;


    public abstract boolean isBlockedCard();

    public abstract String putMoney(int money);

    public abstract String shootMoney(int money) throws InsufficientFundsOnTheCardException;

    public abstract boolean checkPin(int pin) throws IncorrectPinException, AccountBlockedException;

    public abstract void newPin(int oldPin, int newPin) throws IncorrectPinException, AccountBlockedException;

    public abstract String getNumberCard();

    public abstract int getMoney();

    public abstract int getPin();

    public abstract Calendar getHowLongBlockedCard();

    public abstract int getCountIncorrectPin();

}
