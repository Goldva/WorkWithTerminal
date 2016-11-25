package interfaces;

/**
 * Created by ermakov on 25.11.2016.
 */
public interface Terminal {
    void putMoney(int money);
    void shootMoney(int money);
    void createClient(String name);
    void deleteClient(String name);
    void deleteCard();
}
