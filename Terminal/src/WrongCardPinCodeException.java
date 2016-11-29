/**
 * Created by pober on 28.11.2016.
 */
public class WrongCardPinCodeException extends Exception{
    public  WrongCardPinCodeException(){
        super("Карта с таким пин-кодом отсутствует");
    }

    public WrongCardPinCodeException(String s){
        super(s);
    }
}
