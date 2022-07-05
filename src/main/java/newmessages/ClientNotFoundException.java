package newmessages;

/**
 * @author Ringer
 */
public class ClientNotFoundException extends Exception{
    public ClientNotFoundException(){
        super("SClient not found");
    }
    public ClientNotFoundException(String message){
        super(message);
    }

}
