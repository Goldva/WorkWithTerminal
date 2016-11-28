import Utils.ConsoleHelper;
import exceptions.CanNotBeRemovedClientException;
import exceptions.IncorrectPinException;
import exceptions.SuchUserExistsException;
import interfaces.Terminal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkTerminal implements Terminal {
    private ConsoleHelper consoleHelper;
    private List<String> listNumberCards;
    private Map<String, Client> clients;

    public WorkTerminal() {
        consoleHelper = new ConsoleHelper();
        clients = new HashMap<>();
    }

    @Override
    public int statusScore() {
        return 0;
    }

    @Override
    public void putMoney() {

    }

    @Override
    public void shootMoney() {

    }

    @Override
    public void createClient(String clientName) throws SuchUserExistsException {
        if (clients.containsKey(clientName))
            throw new SuchUserExistsException();
        clients.put(clientName, new Client(clientName));
    }

    @Override
    public void deleteClient(String clientName) throws CanNotBeRemovedClientException {
        if (clients.get(clientName).haveACards())
            throw new CanNotBeRemovedClientException();
        clients.remove(clientName);
    }

    @Override
    public void createCard(String clientName) {
        try {
            Client client = clients.get(clientName);
            String numberCard = createNewNumberCard();
            client.createCard(numberCard, createNewPin());
            consoleHelper.write("Создана карта с номером " + numberCard);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCard(String clientName) throws IncorrectPinException {
        try {
            consoleHelper.write("Введите номер карты для удаления");
            String numberCard = consoleHelper.readString();
            consoleHelper.write("Введите пин-код карты состоящий из 4-х цифр");
            int pin = consoleHelper.readInt();
            Client client = clients.get(clientName);
            client.deleteCard(numberCard, pin);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String createNewNumberCard(){
        Random random = new Random();
        while (true) {
            String number = String.format("%05d", random.nextInt(99999));
            if (!listNumberCards.contains(number.getBytes()))
                return number;
        }
    }

    private int createNewPin() throws IOException {
        int newPin;
        while (true) {
            System.out.println("Введите новый пин-код состоящий из 4 цифр");
            newPin = consoleHelper.readInt();
            if (String.valueOf(newPin).length() != 4) {
                try {
                    throw new IncorrectPinException();
                } catch (IncorrectPinException e) {
                    consoleHelper.write(e.getMessage());
                    continue;
                }
            }
            return newPin;
        }
    }
}
