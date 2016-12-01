import Utils.ConsoleHelper;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleHelper consoleHelper = new ConsoleHelper();
        bin.WorkTerminal workTerminal = null;
        String text;
        boolean connected = false;
        while (!connected) {
            consoleHelper.write("��� �������� ������ ������� ������� ������� \"new\"\n" +
                    "��� ����� ��� ������������ �������� ������� ��� ���");
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
                    "������� ���� �� ��������� ������\n" +
                    "������� ������� - delClient\n" +
                    "������� ����� - createCard\n" +
                    "������� ����� - delCard\n" +
                    "������ ������ ����� - status\n" +
                    "�������� ������ - put\n" +
                    "����� ������ - shoot");

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



