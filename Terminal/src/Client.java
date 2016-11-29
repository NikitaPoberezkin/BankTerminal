import java.io.*;

/**
 * Created by pober on 28.11.2016.
 */
public class Client implements java.io.Serializable {
    private String name;
    private Card card;

    public Client(String name){
        this.name = name;
        this.card = null;
    }

    public Client(String name, Card card){
        this.name=name;
        this.card=card;
    }

    public String getName(){
        return this.name;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Card getCard(){
        return this.card;
    }

    @Override
    public String toString(){
        return "Имя:" + this.name + "\n";
    }

    @Override
    public int hashCode(){
        return this.name != null? this.name.hashCode():0;
    }

    public boolean equals (Client client){
        if (this == client){
            return true;
        }

        if (client == null){
            return false;
        }

        if (!(client instanceof Client)){
            return false;
        }

        return client.name.equals(this.name);
    }

    public static Client fromSymbolStream(Reader reader){
        StreamTokenizer in = new StreamTokenizer(reader);
        try {
            in.nextToken();
            String name = in.sval;
            Card card = Card.fromSymbolStream(reader);
            return new Client(name, card);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void intoSymbolStream (Writer writer){
        PrintWriter out = new PrintWriter(writer);
        out.print(name + " ");
        card.intoSymbolStream(writer);
    }

    public static Client fromByteStream(InputStream inputStream){
        DataInputStream in = new DataInputStream(inputStream);
        try{
            int length = in.readInt();
            char[] str = new char[length];
            for (int i=0; i<length;i++)
                str[i]=in.readChar();
            String name = new String(str);
            return new Client(name, Card.fromByteStream(inputStream));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void intoByteStream(OutputStream outputStream) throws IOException {
        DataOutputStream out = new DataOutputStream(outputStream);
        try {
            out.writeInt(this.name.length());
            out.writeChars(this.name);
            card.intoByteStream(outputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
