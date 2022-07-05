package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageHashedCode extends Message{
    public String hashedCode;

    /**
     * @param string
     */
    public MessageHashedCode(String string){
        hashedCode=string;
        type="HashedCode";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("HashedCode", new JsonPrimitive(hashedCode));
        content=jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageHashedCode(JsonObject jsonObject){
        super(jsonObject);
        hashedCode=content.get("HashedCode").getAsString();
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
