package threads;

import Utils.ConsoleHelper;
import bin.Card;
import exceptions.InsufficientFundsOnTheCardException;

import java.util.Random;

public class Decreaser extends Thread {
    private Card card;
    private ConsoleHelper consoleHelper;

    public Decreaser(Card card) {
        this.card = card;
        this.consoleHelper = ConsoleHelper.getInstance();
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!interrupted()) {
            int money = random.nextInt(1000);
            try {
                card.shootMoney(money);
                String text = "Выдано %d рублей. Остаток по карте составляет: %d руб.";
                consoleHelper.write(String.format(text, money, card.getMoney()));
            } catch (InsufficientFundsOnTheCardException e) {
                consoleHelper.write(String.format("Запрошено %d. %s", money, e.getMessage()));
            }
        }

    }
}
