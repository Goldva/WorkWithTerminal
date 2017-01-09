import bin.Card;
import decorators.DecoratorCard;
import runnables.SequentialDecreaser;
import runnables.SequentialIncreaser;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Card card = new Card("4333223", 4444);
        Lock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
//        Decreaser decreaser = new Decreaser(new DecoratorCard(card));
//        Increaser increaser = new Increaser(new DecoratorCard(card));
        Thread increaser = new Thread(new SequentialIncreaser(new DecoratorCard(card), lock, condition));
        Thread decreaser = new Thread(new SequentialDecreaser(new DecoratorCard(card), lock, condition));

        decreaser.setPriority(Thread.MAX_PRIORITY);
        increaser.setPriority(Thread.MIN_PRIORITY);

        decreaser.setDaemon(true);
        increaser.setDaemon(true);

        increaser.start();
        decreaser.start();

        Thread.sleep(111);

//        ConsoleHelper consoleHelper = new ConsoleHelper();
//        bin.WorkTerminal workTerminal = null;
//        String text;
//        boolean connected = false;
//        while (!connected) {
//            consoleHelper.write("Для создания нового клиента введите команду \"new\"\n" +
//                    "Для входа под существующим клиентом введите его имя");
//            text = consoleHelper.readString();
//            if (text.equals("new")) {
//                workTerminal = new bin.WorkTerminal();
//                connected = workTerminal.getConectedClient() != null;
//            } else {
//                workTerminal = new bin.WorkTerminal(text);
//                connected = workTerminal.getConectedClient() != null;
//            }
//        }
//
//        while (connected) {
//            connected = workTerminal.getConectedClient() != null;
//            consoleHelper.write("===========================================\n" +
//                    "Введите одну из следующих команд\n" +
//                    "Удалить клиента - delClient\n" +
//                    "Создать карту - createCard\n" +
//                    "Удалить карту - delCard\n" +
//                    "Узнать статус счета - status\n" +
//                    "Положить деньги - put\n" +
//                    "Снять деньги - shoot");
//
//            text = consoleHelper.readString();
//
//            switch (text) {
//                case "delClient":
//                    workTerminal.deleteClient();
//                    break;
//                case "createCard":
//                    workTerminal.createCard();
//                    break;
//                case "delCard":
//                    workTerminal.deleteCard();
//                    break;
//                case "status":
//                    workTerminal.statusScore();
//                    break;
//                case "put":
//                    workTerminal.putMoney();
//                    break;
//                case "shoot":
//                    workTerminal.shootMoney();
//                    break;
//            }
//        }
//
//
    }
}