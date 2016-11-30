package bin;

import exceptions.AccountBlockedException;
import exceptions.IncorrectPinException;

import java.util.HashMap;
import java.util.Map;

public class Client {
    private String clientName;
    private Map<String, Card> myCards;

    public Client(String clientName) {
        this.clientName = clientName;
        this.myCards = new HashMap<>();
    }

    public void createCard(String numberCard, int pin){
        myCards.put(numberCard, new Card(numberCard, pin));
    }

    public void addCard(Card card){
        myCards.put(card.getNumberCard(), card);
    }

    public void deleteCard(String numberCard, int pin) throws IncorrectPinException, AccountBlockedException {
        myCards.get(numberCard).checkPin(pin);
        myCards.remove(numberCard);
    }

    public boolean haveACards(){
        return myCards.size() > 0;
    }

    public String getClientName() {
        return clientName;
    }

    public Map<String, Card> getMyCards() {
        return myCards;
    }

    public Card getMyCard(String numberCard){
        return myCards.get(numberCard);
    }
}
