package server_package.gamelogic.cards.programming_cards;

import server_package.gamelogic.cards.CardName;
import server_package.newmessages.MessageEnergy;

public class PowerUp extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public PowerUp() {super(CardName.POWER_UP);}

    @Override
    public void discard() {

    }

    /**
     * @author Mark Ringer
     */
    @Override
    public void activateCard() {
        // Take one energy cube, and place it on your player mat.
        player.addEnergyCube();
        player.getClient().sendAll(new MessageEnergy(player.getClient().getId(),1,"EnergyCube"));
    }
}