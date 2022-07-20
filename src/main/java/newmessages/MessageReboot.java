package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageReboot extends Message{

    public int clientID;

    /**
     * @param clientID
     */
    public MessageReboot(int clientID){
        this.clientID = clientID;
        type = "Reboot";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        content = jsonObject;
        //Server.serverLogger.info("Created Robo Reboot Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageReboot(JsonObject jsonObject){
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        //Server.serverLogger.info("Created Robo Reboot Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        // Roboter wird nach oben / norden ausgerichtet
        // Koordinaten des Rebootfeldes werden per "Movement"-Nachricht verschickt
        // Client quittiert Nachricht mit der Richtung in d. d. Bot ausgerichtet werden soll (top, right, bottom, left)

        // Startet ein Roboter auf Startfeld neu, während anderer auf Neustart Feld
            // Neustartender Roboter schiebt Roboter der das Feld besetzt in Richtung des Pfeils auf dem Neustart-Feld weg
            // Wenn Roboter zwischen Wand & Startfeld steht dann soll Roboter auf einem anderen freien Startfeld rebooten
    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {
        if(clientID ==client.getId())
             client.getClientApplication().addAndExecuteTask(new Task(TaskType.REBOOTDIRECTION, new TaskContent()));
    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
