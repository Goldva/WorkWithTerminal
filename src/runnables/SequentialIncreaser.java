package runnables;

import Utils.ConsoleHelper;
import decorators.DecoratorCard;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SequentialIncreaser implements Runnable {
    private DecoratorCard card;
    private ConsoleHelper consoleHelper;
    private Lock lock;
    private Condition condition;

    public SequentialIncreaser(DecoratorCard card, Lock lock, Condition condition) {
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
                String text = card.putMoney(money);
                consoleHelper.write(text);
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
