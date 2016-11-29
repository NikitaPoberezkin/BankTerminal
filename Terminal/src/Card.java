import java.io.*;

/**
 * Created by pober on 28.11.2016.
 */
public class Card implements java.io.Serializable {
    private int number;
    private int balance;
    private int pin_code;

    public Card (int number, int pin_code){
        this.number = number;
        this.pin_code = pin_code;
        balance = 0;
    }

    public Card (int number, int balance, int pin_code){
        this.number =number;
        this.balance=balance;
        this.pin_code=pin_code;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int balance){
        this.balance = balance;
    }

    public int getPin_code(){
        return this.pin_code;
    }

    public int getNumber(){
        return this.number;
    }

    public String toString(){
        return "Номер карты: " + this.number + ", баланс: " + this.balance;
    }

    public  void setPin_code(int pin_code){
        this.pin_code = pin_code;
    }

    public static Card fromSymbolStream(Reader reader){
        StreamTokenizer in = new StreamTokenizer(reader);
        try{
            in.nextToken();
            int number = (int) in.nval;
            in.nextToken();
            int balance = (int) in.nval;
            in.nextToken();
            int pin_code = (int) in.nval;
            return new Card(number, balance, pin_code);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void intoSymbolStream (Writer writer){
        PrintWriter out = new PrintWriter(writer);
        out.print(number + " ");
        out.print(balance + " ");
        out.print(pin_code + " ");
    }

    public static Card fromByteStream(InputStream inputStream){
        DataInputStream in = new DataInputStream(inputStream);
        try{
            int number = in.readInt();
            int balance = in.readInt();
            int pin_code = in.readInt();
            return new Card (number, balance, pin_code);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void intoByteStream(OutputStream outputStream) throws IOException{
        DataOutputStream out = new DataOutputStream(outputStream);
        try{
            out.writeInt(number);
            out.writeInt(balance);
            out.writeInt(pin_code);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
