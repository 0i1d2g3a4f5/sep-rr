package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

public class MessageValuesAccepted extends Message{
    public String name;
    public int figure;
    public MessageValuesAccepted(String name, int figure){
        super(name, figure);
        type="ValuesAccepted";
        JsonObject jsonObject=new JsonObject();
        jsonObject.add("Name", new JsonPrimitive(name));
        jsonObject.add("Figure", new JsonPrimitive(figure));
        content=jsonObject;

    }
    public MessageValuesAccepted(JsonObject jsonObject){
        super(jsonObject);
        name=content.get("Name").getAsString();
        figure=content.get("Figure").getAsInt();
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