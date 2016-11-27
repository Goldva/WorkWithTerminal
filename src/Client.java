import exceptions.IncorrectPinException;

import java.util.HashMap;
import java.util.Map;

public class Client {
    private String name;
    private Map<String, Card> myCards;

    public Client(String name) {
        this.name = name;
        this.myCards = new HashMap<>();
    }

    public void createCard(String numberCard, String pin){
        myCards.put(numberCard, new Card(numberCard, pin));
    }

    public void deleteCard(String numberCard, String pin) throws IncorrectPinException {
        myCards.get(numberCard).checkPin(pin);
        myCards.remove(numberCard);
    }

    public boolean haveACards(){
        return myCards.size() > 0;
    }

    public String getName() {
        return name;
    }

    public Map<String, Card> getMyCards() {
        return myCards;
    }

    public Card getCard(String numberCard){
        return myCards.get(numberCard);
    }
}
