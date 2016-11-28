import exceptions.IncorrectPinException;
import exceptions.InsufficientFundsOnTheCardException;

import java.util.Arrays;

public class Card {
    private int pin;
    private int money;
    private String numberCard;

    public Card(String numberCard, int pin) {
        this.pin = pin;
        this.money = 0;
        this.numberCard = numberCard;
    }

    public void putMoney(int money){
        this.money += money;
        System.out.println(String.format("Начислено %d рублей", money));
    }

    public void shootMoney(int pin, int money) throws InsufficientFundsOnTheCardException, IncorrectPinException {
        checkPin(pin);
        if (this.money < money)
            throw new InsufficientFundsOnTheCardException();
        this.money -= money;
        System.out.println(String.format("Выдано %d рублей", money));
    }

    public void checkPin(int pin) throws IncorrectPinException {
        if (!(pin == this.pin))
            throw new IncorrectPinException();
    }

    public void newPin(int oldPin, int newPin) throws IncorrectPinException {
        checkPin(oldPin);
        this.pin = newPin;
}


    public String getNumberCard() {
        return numberCard;
    }
}
