package newmessages;

import com.google.gson.JsonObject;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageTimerEnded extends Message{

    public MessageTimerEnded(){
        super();
        type = "TimerStarted";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        System.out.println("Created StartTime Message: " + this);
    }

    public MessageTimerEnded(JsonObject jsonObject){
        super(jsonObject);
        System.out.println("Created StartTime Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Client client) throws IOException, ClientNotFoundException {

    }
}
