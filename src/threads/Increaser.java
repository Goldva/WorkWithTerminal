package threads;

import Utils.ConsoleHelper;
import bin.Card;

import java.util.Random;

public class Increaser extends Thread {
    private Card card;
    private ConsoleHelper consoleHelper;

    public Increaser(Card card) {
        this.card = card;
        this.consoleHelper = ConsoleHelper.getInstance();
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!interrupted()) {
            int money = random.nextInt(1000);
            card.putMoney(money);
            String text = "Начислено %d рублей, остаток по карте составляет: %d руб.";
            consoleHelper.write(String.format(text, money, card.getMoney()));
        }
    }
}
