import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pober on 29.11.2016.
 */
public class Bank {
    public static void main(String[] args) {
        BankTerminal bankTerminal = new BankTerminal();
        Scanner scan = new Scanner(System.in);
        ArrayList<Client> clients = new ArrayList<Client>();
        ArrayList<Card> cards = new ArrayList<Card>();
        try {
            FileReader fr = new FileReader("file.txt");
            while (fr.ready()){
                clients.add(Client.fromSymbolStream(fr));
            }
        }
        catch (IOException e){

        }
        /*try {
            FileInputStream fis = new FileInputStream("file.bin");
            while (fis.available()!=0){
                clients.add(Client.fromByteStream(fis));
            }
        }
        catch (IOException e){

        }*/
        while (true) {
            System.out.println("1. Войти в аккаунт клиента\n" + "2. Создать нового клиента\n3. Закончить выполнение операций");
            switch (scan.nextInt()) {
                case 1:
                    if (clients.isEmpty()) {
                        System.out.println("Нет доступных клиентов");
                        break;
                    }
                    System.out.println("Доступные клиенты:");
                    for (Client element : clients) {
                        System.out.println(element.toString() + " Номер карты: " + element.getCard().getNumber());
                    }
                    System.out.println("Выберите клиента (введите его номер): ");
                    bankTerminal.setCurrentClient(clients.get(scan.nextInt() - 1));
                    System.out.println("Добро пожаловать " + bankTerminal.getCurrentClient().getName() + ", введите пин-код вашей карты: ");
                    int number_of_attemts = 0;
                    enter_pin_code:
                    while (true) {
                        try {
                            if (scan.nextInt() != bankTerminal.getCurrentClient().getCard().getPin_code()) {
                                throw new WrongPinCodeException();
                            }
                            if ((number_of_attemts % 3 == 0) && (number_of_attemts != 0)) {
                                throw new AccountBlockedException("Ввод был осуществлен неверно трижды, аккаунт заблокирован на несколько секунд");
                            }
                            break enter_pin_code;
                        } catch (WrongPinCodeException e) {
                            number_of_attemts++;
                            System.out.println(e.getMessage());
                        } catch (AccountBlockedException e) {
                            number_of_attemts++;
                            System.out.println(e.getMessage());
                        }
                    }

                    choose_operation:
                    while (true) {
                        System.out.println("1. Проверить баланс карты\n2. Положить деньги на карту\n3. Снять деньги с карты\n4. Выйти из аккаунта\n5. Удалить клиента");
                        switch (scan.nextInt()) {
                            case 1:
                                System.out.println("Баланс карты: " + bankTerminal.getCurrentClient().getCard().getBalance());
                                break;
                            case 2:
                                System.out.println("Введите сумму: ");
                                put_amount:
                                while (true) {
                                    try {
                                        bankTerminal.putMoney(bankTerminal.getCurrentClient().getCard(), scan.nextInt());
                                        break put_amount;
                                    } catch (AmountNotMultiple100Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                break;
                            case 3:
                                System.out.println("Введите сумму: ");
                                get_amount:
                                while (true) {
                                    try {
                                        bankTerminal.getMoney(bankTerminal.getCurrentClient().getCard(), scan.nextInt());
                                        break get_amount;
                                    } catch (AmountNotMultiple100Exception e) {
                                        System.out.println(e.getMessage());
                                    } catch (NotEnoughMoneyException e) {
                                        System.out.println(e.getMessage());
                                    }
                                }
                                break;
                            case 4:
                                break choose_operation;
                            case 5:
                                for (Client element: clients){
                                    if (element.getName().equals(bankTerminal.getCurrentClient().getName())){
                                        clients.remove(element);
                                    }
                                }
                                break choose_operation;
                        }
                    }
                    break;

                case 2:
                    enter_name:
                    while (true) {
                        try {
                            bankTerminal.createClient(clients);

                            break enter_name;
                        } catch (ClientDuplicateException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    enter_number:
                    while (true) {
                        try {
                            bankTerminal.createCard(clients.get(clients.size() - 1), cards);
                            break enter_number;
                        } catch (CardDuplicateException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 3:
                    try {
                        FileWriter fw = new FileWriter("file.txt");
                        for (Client element : clients) {
                            element.intoSymbolStream(fw);
                        }
                        fw.close();
                    }
                    catch (IOException e){

                    }
                    try {
                        FileOutputStream fos = new FileOutputStream("file.bin");
                        for (Client element: clients){
                            element.intoByteStream(fos);
                        }for (Client element: clients){
                            element.intoByteStream(fos);
                        }
                        fos.close();
                    }
                    catch (IOException e){

                    }
                    try {
                        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("serialize.bin"));
                        for (Client element: clients){
                            ous.writeObject(element);
                        }
                    }
                    catch (IOException e){

                    }
                    return;
            }
        }
    }
}

