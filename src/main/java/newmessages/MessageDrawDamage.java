package newmessages;

import gamelogic.cards.CardName;
import server_package.Client;

import java.io.IOException;
import java.util.ArrayList;

public class MessageDrawDamage extends Message {
    public MessageDrawDamage(int id, ArrayList<CardName> damageCards) {
    }

    //TODO implement required Constructors etc!!!
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
