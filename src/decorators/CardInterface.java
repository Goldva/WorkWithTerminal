package decorators;

import exceptions.AccountBlockedException;
import exceptions.IncorrectPinException;
import exceptions.InsufficientFundsOnTheCardException;

import java.util.Calendar;

public interface CardInterface {
    boolean isBlockedCard();

    String putMoney(int money);

    String shootMoney(int money) throws InsufficientFundsOnTheCardException;

    boolean checkPin(int pin) throws IncorrectPinException, AccountBlockedException;

    void newPin(int oldPin, int newPin) throws IncorrectPinException, AccountBlockedException;

    String getNumberCard();

    int getMoney();

    int getPin();

    Calendar getHowLongBlockedCard();

    int getCountIncorrectPin();

}
