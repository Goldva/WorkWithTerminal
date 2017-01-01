package runnables;

import Utils.ConsoleHelper;
import bin.Card;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SequentialIncreaser implements Runnable {
    private Card card;
    private ConsoleHelper consoleHelper;
    private Lock lock;
    private Condition condition;

    public SequentialIncreaser(Card card, Lock lock, Condition condition) {
        this.card = card;
        this.consoleHelper = ConsoleHelper.getInstance();
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (!Thread.interrupted()) {
            try {
                lock.lock();
                condition.signalAll();
                int money = random.nextInt(1000);
                card.putMoney(money);
                String text = "Начислено %d рублей, остаток по карте составляет: %d руб.";
                consoleHelper.write(String.format(text, money, card.getMoney()));
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
