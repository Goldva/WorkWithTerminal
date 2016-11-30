package interfaces;

import exceptions.AccountBlockedException;
import exceptions.CanNotBeRemovedClientException;
import exceptions.IncorrectPinException;
import exceptions.SuchUserExistsException;

import java.io.IOException;

public interface Terminal {
    void statusScore();
    void putMoney();
    void shootMoney();
    void createClient() throws SuchUserExistsException;
    void deleteClient() throws CanNotBeRemovedClientException;
    void createCard();
    void deleteCard() throws AccountBlockedException;
}
