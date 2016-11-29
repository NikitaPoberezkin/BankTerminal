import java.util.ArrayList;

/**
 * Created by pober on 28.11.2016.
 */
public interface Terminal {

    int getBalance(Card card);

    void putMoney(Card card, int amount) throws AmountNotMultiple100Exception;

    void getMoney(Card card, int amount) throws AmountNotMultiple100Exception, NotEnoughMoneyException;

    void createClient(ArrayList<Client> clients) throws ClientDuplicateException;

    void deleteClient(String name, ArrayList<Client> clients) throws WrongClientNameException;

    void createCard(Client client, ArrayList<Card> cards) throws CardDuplicateException;

    void deleteCard(int pin_code, Client client, ArrayList<Card> cards) throws WrongCardPinCodeException;
}
