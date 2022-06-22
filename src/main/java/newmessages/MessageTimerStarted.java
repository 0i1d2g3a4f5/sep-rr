package newmessages;

import com.google.gson.JsonObject;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessageTimerStarted extends Message{

    //public ArrayList<int> players;

    public MessageTimerStarted(){
        super();
        type = "TimerStarted";
        JsonObject jsonObject = new JsonObject();
        content = jsonObject;
        System.out.println("Created EndTime Message: " + this);
    }

    public MessageTimerStarted(JsonObject jsonObject){
        super(jsonObject);
        System.out.println("Created EndTime Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param server
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessage(Server server) throws IOException, ClientNotFoundException {

    }
}
