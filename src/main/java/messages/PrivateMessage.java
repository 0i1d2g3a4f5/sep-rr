package messages;
/**
 * @author Sarp Cagin Erdogan
 * @author Mark Ringer
 */
public class PrivateMessage extends Message {
    public int receiver;
    public PrivateMessage(int ID, String message){
        super(ID,message);
    }
    public PrivateMessage(int ID, String message, int receiver){
        this.ID = ID;
        this.content = message;
        this.receiver = receiver;
    }

}