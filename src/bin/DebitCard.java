package bin;

import decorators.AbstractCard;
import exceptions.AccountBlockedException;
import exceptions.IncorrectPinException;
import exceptions.InsufficientFundsOnTheCardException;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DebitCard extends AbstractCard implements Serializable {
    public DebitCard(String numberCard, int pin) {
        this.pin = pin;
        this.money = 0;
        this.numberCard = numberCard;
        this.howLongBlockedCard = new GregorianCalendar();
        this.countIncorrectPin = 0;
    }

    public DebitCard(String numberCard, int pin, int money, Calendar howLongBlockedCard, int countIncorrectPin) {
        this.numberCard = numberCard;
        this.pin = pin;
        this.money = money;
        this.howLongBlockedCard = howLongBlockedCard;
        this.countIncorrectPin = countIncorrectPin;
    }

    @Override
    public boolean isBlockedCard() {
        boolean block = new GregorianCalendar().before(howLongBlockedCard);
        if (countIncorrectPin == 3 && !block)
            countIncorrectPin = 0;
        return block;
    }

    private void blockTheCard() {
        howLongBlockedCard = new GregorianCalendar();
        howLongBlockedCard.add(Calendar.MINUTE, 1);
    }

    @Override
    public String putMoney(int money) {
        this.money += money;
        String text = "Начислено %d рублей, остаток по карте составляет: %d руб.";
        return String.format(text, money, getMoney());
    }

    @Override
    public String shootMoney(int money) throws InsufficientFundsOnTheCardException {
        if (this.money < money)
            throw new InsufficientFundsOnTheCardException();
        this.money -= money;
        String text = "Выдано %d рублей. Остаток по карте составляет: %d руб.";
        return String.format(text, money, getMoney());
    }

    @Override
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

    @Override
    public void newPin(int oldPin, int newPin) throws IncorrectPinException, AccountBlockedException {
        checkPin(oldPin);
        this.pin = newPin;
    }

    @Override
    public String getNumberCard() {
        return numberCard;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public int getPin() {
        return pin;
    }

    @Override
    public Calendar getHowLongBlockedCard() {
        return howLongBlockedCard;
    }

    @Override
    public int getCountIncorrectPin() {
        return countIncorrectPin;
    }
}
