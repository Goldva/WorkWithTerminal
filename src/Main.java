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

        while (true) {
            consoleHelper.write("===========================================\n" +
                    "������� ���� �� ��������� ������\n" +
                    "������� ������� - delClient\n" +
                    "������� ����� - createCard\n" +
                    "������� ����� - delCard\n" +
                    "������ ������ ����� - status\n" +
                    "�������� ������ - put\n" +
                    "����� ������ - shoot");

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



