package interfaces;

import exceptions.CanNotBeRemovedClientException;
import exceptions.IncorrectPinException;
import exceptions.SuchUserExistsException;

import java.io.IOException;

public interface Terminal {
    int statusScore();
    void putMoney();
    void shootMoney();
    void createClient(String clientName) throws SuchUserExistsException;
    void deleteClient(String clientName) throws CanNotBeRemovedClientException;
    void createCard(String clientName);
    void deleteCard(String clientName) throws IncorrectPinException;
}
