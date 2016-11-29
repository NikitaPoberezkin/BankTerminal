import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pober on 28.11.2016.
 */
public class BankTerminal implements Terminal {
    private Client currentClient;

    public Client getCurrentClient(){
        return this.currentClient;
    }

    public void setCurrentClient(Client current_client){
        this.currentClient = current_client;
    }

    @Override
    public int getBalance(Card card){
        return card.getBalance();
    }

    @Override
    public void putMoney(Card card, int amount) throws AmountNotMultiple100Exception{
        if (amount%100 == 0) {
            card.setBalance(card.getBalance() + amount);
        }
        else {
            throw new AmountNotMultiple100Exception();
        }
    }

    @Override
    public void getMoney(Card card, int amount) throws AmountNotMultiple100Exception, NotEnoughMoneyException{
        if (amount>card.getBalance()){
            throw new NotEnoughMoneyException();
        }
        if (amount%100 == 0){
            card.setBalance(card.getBalance() - amount);
        }
        else {
            throw new AmountNotMultiple100Exception();
        }
    }

    @Override
    public void createClient (ArrayList<Client> clients) throws ClientDuplicateException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите имя клиента");
        String name = scan.next();
        for (Client element:clients){
            if (element.getName().equals(name)){
                throw new ClientDuplicateException();
            }
        }
        Client client = new Client(name);
        clients.add(client);
    }

    @Override
    public void deleteClient(String name, ArrayList<Client> clients) throws WrongClientNameException{
        int k = 0;
        for (Client element: clients){
            if (element.getName().equals(name)) {
                clients.remove(element);
                k++;
            }
        }
        if (k==0) throw new WrongClientNameException();
    }

    @Override
    public void createCard(Client client, ArrayList<Card> cards) throws CardDuplicateException{
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите номер карты");
        int number = scan.nextInt();
        System.out.println("Введите пин-код карты");
        int pin_code = scan.nextInt();
        for (Card element: cards){
            if (element.getNumber() == number){
                throw new CardDuplicateException();
            }
        }
        Card card = new Card(number,pin_code);
        cards.add(card);
        client.setCard(card);
    }

    @Override
    public void deleteCard(int number, Client client, ArrayList<Card> cards) throws WrongCardPinCodeException{
        int k = 0;
        for (Card element: cards){
            if (element.getNumber() == number) {
                cards.remove(element);
                k++;
            }
        }
        if (k==0) throw new WrongCardPinCodeException();
        client.setCard(null);
    }
}
