/**
 * Created by pober on 28.11.2016.
 */
public class CardDuplicateException extends Exception {

    public CardDuplicateException(){
        super("Карта с таким номером уже существует");
    }

    public CardDuplicateException(String s){
        super(s);
    }
}
