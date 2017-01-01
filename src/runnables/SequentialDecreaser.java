package runnables;

import Utils.ConsoleHelper;
import bin.Card;
import exceptions.InsufficientFundsOnTheCardException;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SequentialDecreaser implements Runnable {
    private Card card;
    private ConsoleHelper consoleHelper;
    private Lock lock;
    private Condition condition;

    public SequentialDecreaser(Card card, Lock lock, Condition condition) {
        this.card = card;
        this.consoleHelper = ConsoleHelper.getInstance();
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!Thread.interrupted()) {
            int money = random.nextInt(1000);

            try {
                lock.lock();
                try {
                    condition.signalAll();
                    card.shootMoney(money);
                    String text = "Выдано %d рублей. Остаток по карте составляет: %d руб.";
                    consoleHelper.write(String.format(text, money, card.getMoney()));
                } catch (InsufficientFundsOnTheCardException e) {
                    consoleHelper.write(String.format("Запрошено %d. %s", money, e.getMessage()));
                }
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }
    }
}
