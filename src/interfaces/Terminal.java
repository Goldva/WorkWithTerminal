package interfaces;

public interface Terminal {
    int statusScore();
    void putMoney();
    void shootMoney();
    void createClient();
    void deleteClient(String clientName);
    void createCard();
    void deleteCard();
}
