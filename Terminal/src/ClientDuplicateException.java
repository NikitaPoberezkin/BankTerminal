/**
 * Created by pober on 28.11.2016.
 */
public class ClientDuplicateException extends Exception {

    ClientDuplicateException(){
        super("Клиент с таким именем уже существует, пожалуйста введите другое имя");
    }

    ClientDuplicateException(String s){
        super(s);
    }
}
