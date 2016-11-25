package interfaces;

public interface Terminal {
    void putMoney(int money);
    void shootMoney(int money);
    void createClient(String name);
    void deleteClient(String name);
    void createCard();
    void deleteCard();
}
