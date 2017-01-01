package bin;

import exceptions.AccountBlockedException;
import exceptions.IncorrectPinException;
import exceptions.InsufficientFundsOnTheCardException;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Card implements Serializable{
    private String numberCard;
    private int pin;
    private volatile int money;
    private Calendar howLongBlockedCard;
    private int countIncorrectPin;

    private final Object lockMoney = new Object();

    public Card(String numberCard, int pin) {
        this.pin = pin;
        this.money = 0;
        this.numberCard = numberCard;
        howLongBlockedCard = new GregorianCalendar();
        countIncorrectPin = 0;
    }

    public Card(String numberCard, int pin, int money, Calendar howLongBlockedCard, int countIncorrectPin) {
        this.numberCard = numberCard;
        this.pin = pin;
        this.money = money;
        this.howLongBlockedCard = howLongBlockedCard;
        this.countIncorrectPin = countIncorrectPin;
    }

    public boolean isBlockedCard(){
        boolean block = new GregorianCalendar().before(howLongBlockedCard);
        if (countIncorrectPin == 3 && !block)
            countIncorrectPin = 0;
        return block;
    }

    public void blockTheCard(){
        howLongBlockedCard = new GregorianCalendar();
        howLongBlockedCard.add(Calendar.MINUTE, 1);
    }

    public void putMoney(int money){
        synchronized (lockMoney) {
            this.money += money;
        }
    }

    public void shootMoney(int money) throws InsufficientFundsOnTheCardException {
        synchronized (lockMoney) {
            if (this.money < money)
                throw new InsufficientFundsOnTheCardException();
            this.money -= money;
        }
    }

    public boolean checkPin(int pin) throws IncorrectPinException, AccountBlockedException {
        if (isBlockedCard())
            throw new AccountBlockedException();
        if (!(pin == this.pin)) {
            if (++countIncorrectPin == 3) {
                blockTheCard();
                throw new AccountBlockedException();
            }
            throw new IncorrectPinException();
        }
        countIncorrectPin = 0;
        return true;
    }

    public void newPin(int oldPin, int newPin) throws IncorrectPinException, AccountBlockedException {
        checkPin(oldPin);
        this.pin = newPin;
}


    public String getNumberCard() {
        return numberCard;
    }

    public int getMoney() {
        synchronized (lockMoney) {
            return money;
        }
    }

    public int getPin() {
        return pin;
    }

    public Calendar getHowLongBlockedCard() {
        return howLongBlockedCard;
    }

    public int getCountIncorrectPin() {
        return countIncorrectPin;
    }
}
