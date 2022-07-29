package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;

/**
 * @author Vivian Kafadar
 */
public class MessageSelectedDamage extends Message {
    public String damage;
    public int register;

    /**
     * @param damage
     * @param register
     */
    public MessageSelectedDamage(String damage, int register) {
        this.damage = damage;
        this.register = register;
        type = "SelectedDamage";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("damage", new JsonPrimitive(damage));
        jsonObject.add("register", new JsonPrimitive(register));
        content = jsonObject;
    }

    /**
     * @param jsonObject
     */
    public MessageSelectedDamage(JsonObject jsonObject) {
        super(jsonObject);
        damage = content.get("damage").getAsString();
        register = content.get("register").getAsInt();
    }

    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {}
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {}
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {}
}
