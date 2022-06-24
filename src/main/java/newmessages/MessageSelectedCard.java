package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Player;
import gamelogic.cards.CardName;
import server_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageSelectedCard extends Message {

    public String card;
    public int register;

    public MessageSelectedCard(String card, int register) {
        this.card = card;
        this.register = register;
        type = "SelectedCard";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("card", new JsonPrimitive(card));
        jsonObject.add("register", new JsonPrimitive(register));
        content = jsonObject;
        System.out.println("Created SelectCard Message: " + this);
    }

    public MessageSelectedCard(JsonObject jsonObject) {
        super(jsonObject);
        card = content.get("card").getAsString();
        register = content.get("register").getAsInt();
        System.out.println("Created SelectCard Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @author Ringer
     *
     * Answers according tho function feedback
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        Player player = client.getPlayer();
        if (card == "Null"){
            if (player.removeCard(register))
                client.getServer().getGame().sendToAllPlayers(new MessageCardSelected(client.getId(), register, false));
            else client.getServer().getGame().sendToAllPlayers(new MessageCardSelected(client.getId(), register, true));
        }
        else{
            if (player.removeCard(register)){
                if (player.placeCard(CardName.valueOf(card), register))
                    client.getServer().getGame().sendToAllPlayers(new MessageCardSelected(client.getId(), register, true));
                else
                    client.getServer().getGame().sendToAllPlayers(new MessageCardSelected(client.getId(), register, false));
            }else {
                client.getServer().getGame().sendToAllPlayers(new MessageCardSelected(client.getId(), register, true));
            }

        }

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

}
