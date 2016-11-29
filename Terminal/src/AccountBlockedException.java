/**
 * Created by pober on 28.11.2016.
 */
public class AccountBlockedException extends Exception {

    AccountBlockedException(){
        super("Ваш аккаунт заблокирован на несколько секунд");
    }

    AccountBlockedException(String s){
        super(s);
    }
}
