package newmessages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gamelogic.Game;
import gamelogic.Player;
import gamelogic.cards.Card;
import gamelogic.cards.CardName;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageSelectedCard extends Message {

    public String card;
    public int register;

    /**
     * @param card
     * @param register
     */
    public MessageSelectedCard(String card, int register) {
        this.card = card;
        this.register = register;
        type = "SelectedCard";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("card", new JsonPrimitive(card));
        jsonObject.add("register", new JsonPrimitive(register+1));
        content = jsonObject;
        //Server.serverLogger.info("Created Selected Card Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageSelectedCard(JsonObject jsonObject) {
        super(jsonObject);
        card = content.get("card").getAsString();
        register = content.get("register").getAsInt()-1;
        //Server.serverLogger.info("Created Selected Card Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @author Ringer
     *
     * Answers according tho function feedback
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {
        Player player = sClient.getPlayer();
        if (card.equals("Null")){
            if (player.removeCard(register))
                sClient.getServer().getGame().sendToAllPlayers(new MessageCardSelected(sClient.getId(), register, false));
            else sClient.getServer().getGame().sendToAllPlayers(new MessageCardSelected(sClient.getId(), register, true));
        }
        else{

            if(player.getRegister(register) !=null)
                player.getHandCards().add(player.getRegister(register));

            Card cardObject = utility.SearchMethods.searchCard(CardName.parseCardName(card),player.getHandCards());
            player.getAllRegisters()[register] = cardObject;
            sClient.getServer().getGame().sendToAllPlayers(new MessageCardSelected(sClient.getId(), register, true));

            /*
            if (player.removeCard(register)){
                if (player.placeCard(CardName.valueOf(card), register))
                    sClient.getServer().getGame().sendToAllPlayers(new MessageCardSelected(sClient.getId(), register, true));
                else
                    sClient.getServer().getGame().sendToAllPlayers(new MessageCardSelected(sClient.getId(), register, false));
            }else {
                sClient.getServer().getGame().sendToAllPlayers(new MessageCardSelected(sClient.getId(), register, true));

             */


        }
        Server.serverLogger.info("BISHER: ");
        boolean allFull = true;


            for(int i=0; i<5; i++){
                allFull = true;
                if(player.getRegister(i)!=null){
                    Server.serverLogger.info(i + " : " + player.getRegister(i).getCardName().toString());
                }
                else {
                    allFull=false;
                    break;
                }
            }

        if(allFull){
            Server.serverLogger.info("Cards full");
            sClient.getPlayer().isProgramming = false;
            sClient.getPlayer().getGame().setProgrammingPhase(false);
            Server.serverLogger.info("Var programmingPhase: " + sClient.getPlayer().getGame().isProgramingPhase());
        }

    }

    /**
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client) throws IOException, ClientNotFoundException {

    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }

}