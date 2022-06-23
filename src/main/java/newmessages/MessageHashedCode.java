package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.Client;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan
 */
public class MessageHashedCode extends Message{
    public String hashedCode;
    public MessageHashedCode(String string){
        super(string);
        hashedCode=string;
        type="HashedCode";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("HashedCode", new JsonPrimitive(hashedCode));
        content=jsonObject;
    }
    public MessageHashedCode(JsonObject jsonObject){
        super(jsonObject);
        hashedCode=content.get("HashedCode").getAsString();
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
