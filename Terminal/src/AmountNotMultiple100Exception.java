/**
 * Created by pober on 28.11.2016.
 */
public class AmountNotMultiple100Exception extends Exception{

    AmountNotMultiple100Exception(){
        super("Сумма не кратна 100");
    }

    AmountNotMultiple100Exception(String s){
        super(s);
    }
}
