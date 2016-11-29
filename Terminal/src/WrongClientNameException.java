/**
 * Created by pober on 28.11.2016.
 */
public class WrongClientNameException extends Exception {

    public  WrongClientNameException(){
        super("Клиент с таким именем отсутствует");
    }

    public WrongClientNameException(String s){
        super(s);
    }
}
