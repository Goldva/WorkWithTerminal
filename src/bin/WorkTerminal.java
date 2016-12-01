package bin;

import Utils.ConsoleHelper;
import exceptions.*;
import interfaces.Terminal;
import Utils.FileHelper;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.*;

public class WorkTerminal implements Terminal, Externalizable {
    private ConsoleHelper consoleHelper;
    private FileHelper fileHelper;
    private Client conectedClient;

    public WorkTerminal() {
        consoleHelper = new ConsoleHelper();
        createClient();
    }

    public WorkTerminal(String clientName) {
        consoleHelper = new ConsoleHelper();
        fileHelper = new FileHelper(clientName);
        if (fileHelper.checkClient()) {
            conectedClient = fileHelper.readClientFromFile();
        } else {
            consoleHelper.write("������ ������� �� ����������");
        }
    }


    @Override
    public void statusScore() {
        try {
            Card card = checkinCard();
            consoleHelper.write(String.valueOf(card.getMoney()));
        } catch (AccountBlockedException e) {
            consoleHelper.write(e.getMessage());
        }
    }

    @Override
    public void putMoney() {
        try {
            Card card = checkinCard();
            int money = 1;
            while (money % 100 != 0){
                consoleHelper.write("������� ����� ������� ������� ��������(����� ������ ���� ������ 100)");
                money = consoleHelper.readInt();
                if(money % 100 != 0)
                    consoleHelper.write("�� ������ ��������");
            }
            card.putMoney(money);
            consoleHelper.write(String.format("��������� %d ������", money));
            fileHelper.writeClientToFile(conectedClient);
        } catch (AccountBlockedException e) {
            consoleHelper.write(e.getMessage());
        }
    }

    @Override
    public void shootMoney() {
        try {
            Card card = checkinCard();
            int money = 1;
            while (money % 100 != 0){
                consoleHelper.write("������� ��������� �����(����� ������ ���� ������ 100)");
                money = consoleHelper.readInt();
                if(money % 100 != 0)
                    consoleHelper.write("�� ������ ��������");
            }
            card.shootMoney(money);
            consoleHelper.write(String.format("������ %d ������", money));
            fileHelper.writeClientToFile(conectedClient);
        } catch (AccountBlockedException | InsufficientFundsOnTheCardException e) {
            consoleHelper.write(e.getMessage());
        }
    }

    private Card checkinCard() throws AccountBlockedException {
        String numeberCard;
        Card card = null;
        while (card == null) {
            consoleHelper.write("������� ����� �����(5 ����) �� ������� ����������� �������� ������");
            numeberCard = String.valueOf(consoleHelper.readInt());
            card = conectedClient.getMyCard(numeberCard);
            if (card == null)
                consoleHelper.write("���������� ���� ������ ����� �� ����������");
        }

        boolean pinSuccess = false;
        while (!pinSuccess){
            try {
                if (card.isBlockedCard())
                    throw new AccountBlockedException();
                consoleHelper.write("������� ���-��� �����(4 �����)");
                pinSuccess = card.checkPin(consoleHelper.readInt());
            } catch (IncorrectPinException e) {
                consoleHelper.write(e.getMessage());
            }
        }
        return card;
    }

    @Override
    public void createClient()  {
        while (true) {
            try {
                consoleHelper.write("������� ��� ������ �������");
                String clientName = consoleHelper.readString();
                fileHelper = new FileHelper(clientName);
                if (fileHelper.checkClient()) {
                    throw new SuchUserExistsException();
                }
                conectedClient = new Client(clientName);
                fileHelper.writeClientToFile(conectedClient);
                return;
            } catch (SuchUserExistsException e) {
                consoleHelper.write(e.getMessage());
            }
        }
    }

    @Override
    public void deleteClient() {
        try {
            if (conectedClient.haveACards())
                throw new CanNotBeRemovedClientException();
            fileHelper.deleteClient();
            conectedClient = null;
        } catch (CanNotBeRemovedClientException e) {
            consoleHelper.write(e.getMessage());
        }
    }

    @Override
    public void createCard() {
        String numberCard = createNewNumberCard();
        conectedClient.createCard(numberCard, createNewPin());
        consoleHelper.write("������� ����� � ������� " + numberCard);
        fileHelper.writeClientToFile(conectedClient);
    }

    @Override
    public void deleteCard() {
        consoleHelper.write("������� ����� ����� ��� ��������");
        String numberCard = consoleHelper.readString();
        consoleHelper.write("������� ���-��� ����� ��������� �� 4-� ����");
        int pin = consoleHelper.readInt();
        try {
            conectedClient.deleteCard(numberCard, pin);
            fileHelper.writeClientToFile(conectedClient);
            fileHelper.deleteNumberCards(numberCard);
        } catch (IncorrectPinException | AccountBlockedException e) {
            consoleHelper.write(e.getMessage());
        }
    }

    private String createNewNumberCard(){
        Random random = new Random();
        List<String> listNumberCards = fileHelper.getAllNumberCards();

        while (true) {
            String number = String.format("%05d", random.nextInt(99999));
            assert listNumberCards != null;
            if (!listNumberCards.contains(number)) {
                listNumberCards.add(number);
                fileHelper.addNumberCards(number);
                return number;
            }
        }
    }

    private int createNewPin() {
        int newPin;
        while (true) {
            try {
                consoleHelper.write("������� ����� ���-��� ��������� �� 4 ����");
                newPin = consoleHelper.readInt();
                if (String.valueOf(newPin).length() != 4) {
                    throw new IncorrectPinException();
                }
                return newPin;
            } catch (IncorrectPinException e) {
                consoleHelper.write(e.getMessage());
            }
        }
    }

    public Client getConectedClient() {
        return conectedClient;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(conectedClient);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        conectedClient = (Client)in.readObject();
        consoleHelper = new ConsoleHelper();
        fileHelper = new FileHelper(conectedClient.getClientName());
    }
}
