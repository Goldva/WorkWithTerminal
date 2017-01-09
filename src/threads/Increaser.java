package threads;

import Utils.ConsoleHelper;
import decorators.DecoratorCard;

import java.util.Random;

public class Increaser extends Thread {
    private DecoratorCard card;
    private ConsoleHelper consoleHelper;

    public Increaser(DecoratorCard card) {
        this.card = card;
        this.consoleHelper = ConsoleHelper.getInstance();
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!interrupted()) {
            int money = random.nextInt(1000);

            String text = card.putMoney(money);
            consoleHelper.write(text);
        }
    }
}
