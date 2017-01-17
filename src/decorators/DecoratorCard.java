package decorators;

import exceptions.AccountBlockedException;
import exceptions.IncorrectPinException;
import exceptions.InsufficientFundsOnTheCardException;

import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DecoratorCard implements Card {
    private Lock lockMoney = new ReentrantLock(true);
    private AbstractCard abstractCard;

    public DecoratorCard(AbstractCard abstractCard) {
        this.abstractCard = abstractCard;
    }

    @Override
    public boolean isBlockedCard() {
        return abstractCard.isBlockedCard();
    }

    @Override
    public String putMoney(int money) {
        String result = "";
        try {
            lockMoney.lock();
            result = abstractCard.putMoney(money);
        } finally {
            lockMoney.unlock();
        }
        return result;
    }

    @Override
    public String shootMoney(int money) throws InsufficientFundsOnTheCardException {
        String result = "";
        try {
            lockMoney.lock();
            result = abstractCard.shootMoney(money);
        } finally {
            lockMoney.unlock();
        }
        return result;
    }

    @Override
    public boolean checkPin(int pin) throws IncorrectPinException, AccountBlockedException {
        return abstractCard.checkPin(pin);
    }

    @Override
    public void newPin(int oldPin, int newPin) throws IncorrectPinException, AccountBlockedException {
        abstractCard.newPin(oldPin, newPin);
    }

    @Override
    public String getNumberCard() {
        return abstractCard.getNumberCard();
    }

    @Override
    public int getMoney() {
        try {
            lockMoney.lock();
            return abstractCard.getMoney();
        } finally {
            lockMoney.unlock();
        }
    }

    @Override
    public int getPin() {
        return abstractCard.getPin();
    }

    @Override
    public Calendar getHowLongBlockedCard() {
        return abstractCard.getHowLongBlockedCard();
    }

    @Override
    public int getCountIncorrectPin() {
        return abstractCard.getCountIncorrectPin();
    }
}
