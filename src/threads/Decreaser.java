package threads;

import Utils.ConsoleHelper;
import decorators.DecoratorCard;
import exceptions.InsufficientFundsOnTheCardException;

import java.util.Random;

public class Decreaser extends Thread {
    private DecoratorCard card;
    private ConsoleHelper consoleHelper;

    public Decreaser(DecoratorCard card) {
        this.card = card;
        this.consoleHelper = ConsoleHelper.getInstance();
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!interrupted()) {
            int money = random.nextInt(1000);
            try {

                String text = card.shootMoney(money);
                consoleHelper.write(text);
            } catch (InsufficientFundsOnTheCardException e) {
                consoleHelper.write(String.format("Запрошено %d. %s", money, e.getMessage()));
            }
        }

    }
}
