package bin;

import decorators.DecoratorCard;
import exceptions.AccountBlockedException;
import exceptions.IncorrectPinException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Client implements Serializable{
    private String clientName;
    private Map<String, DecoratorCard> myCards;

    public Client(String clientName) {
        this.clientName = clientName;
        this.myCards = new HashMap<>();
    }

    public void createCard(String numberCard, int pin){
        myCards.put(numberCard, new DecoratorCard(new Card(numberCard, pin)));
    }

    public void addCard(DecoratorCard card) {
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

    public Map<String, DecoratorCard> getMyCards() {
        return myCards;
    }

    public DecoratorCard getMyCard(String numberCard) {
        return myCards.get(numberCard);
    }
}
