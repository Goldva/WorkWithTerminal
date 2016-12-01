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

        while (connected) {
            connected = workTerminal.getConectedClient() != null;
            consoleHelper.write("===========================================\n" +
                    "Введите одну из следующих команд\n" +
                    "Удалить клиента - delClient\n" +
                    "Создать карту - createCard\n" +
                    "Удалить карту - delCard\n" +
                    "Узнать статус счета - status\n" +
                    "Положить деньги - put\n" +
                    "Снять деньги - shoot");

            text = consoleHelper.readString();

            switch (text) {
                case "delClient":
                    workTerminal.deleteClient();
                    break;
                case "createCard":
                    workTerminal.createCard();
                    break;
                case "delCard":
                    workTerminal.deleteCard();
                    break;
                case "status":
                    workTerminal.statusScore();
                    break;
                case "put":
                    workTerminal.putMoney();
                    break;
                case "shoot":
                    workTerminal.shootMoney();
                    break;
            }
        }


    }
}



