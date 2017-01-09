package interfaces;

import exceptions.AccountBlockedException;
import exceptions.CanNotBeRemovedClientException;
import exceptions.SuchUserExistsException;

public interface Terminal {
    void statusScore();

    void putMoney();

    void shootMoney();

    void createClient() throws SuchUserExistsException;

    void deleteClient() throws CanNotBeRemovedClientException;

    void createCard();

    void deleteCard() throws AccountBlockedException;
}
