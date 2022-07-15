package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Vivian Kafadar
 */
public class MessageSelectedDamage extends Message {

    //TODO check if correct

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
        //Server.serverLogger.info("Created Selected Damage Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSelectedDamage(JsonObject jsonObject) {
        super(jsonObject);
        damage = content.get("damage").getAsString();
        register = content.get("register").getAsInt();
        //Server.serverLogger.info("Created Selected Damage Message: " + this + " from JSON: " + jsonObject);
    }

    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

        //TODO draw selected dmg cards (Mark)

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
