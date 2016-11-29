/**
 * Created by pober on 28.11.2016.
 */
public class WrongPinCodeException extends Exception {

    WrongPinCodeException(){
        super ("Неверный пин-код, повторите ввод");
    }

    WrongPinCodeException(String s){
        super(s);
    }
}
