import Utils.ConsoleHelper;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleHelper consoleHelper = new ConsoleHelper();
        bin.WorkTerminal workTerminal = null;
        String text;
        boolean connected = false;
        while (!connected) {
            consoleHelper.write("Для создания нового клиента введите команду \"new\"\n" +
                    "Для входа под существующим клиентом введите его имя");
            text = consoleHelper.readString();
            if (text.equals("new")) {
                workTerminal = new bin.WorkTerminal();
                connected = workTerminal.getConectedClient() != null;
            } else {
                workTerminal = new bin.WorkTerminal(text);
                connected = workTerminal.getConectedClient() != null;
            }
        }

        while (true) {
            consoleHelper.write("===========================================\n" +
                    "Введите одну из следующих команд\n" +
                    "Удалить клиента - delClient\n" +
                    "Создать карту - createCard\n" +
                    "Удалить карту - delCard\n" +
                    "Узнать статус счета - status\n" +
                    "Положить деньги - put\n" +
                    "Снять деньги - shoot");

            text = consoleHelper.readString();

            if (text.equals("delClient")) {
                workTerminal.deleteClient();
            } else if (text.equals("createCard")) {
                workTerminal.createCard();
            } else if (text.equals("delCard")) {
                workTerminal.deleteCard();
            } else if (text.equals("status")) {
                workTerminal.statusScore();
            } else if (text.equals("put")) {
                workTerminal.putMoney();
            } else if (text.equals("shoot")) {
                workTerminal.shootMoney();
            }
        }


    }
}



