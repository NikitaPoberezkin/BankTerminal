/**
 * Created by pober on 28.11.2016.
 */
public class NotEnoughMoneyException extends Exception{

    NotEnoughMoneyException(){
        super ("Недостаточно средств на карте");
    }

    NotEnoughMoneyException(String s){
        super(s);
    }
}
