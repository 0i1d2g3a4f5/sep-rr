package client_package.client_gamelogic.cards.programming_cards;

import newmessages.MessageEnergy;

import static client_package.client_gamelogic.cards.CardName.POWER_UP;

public class PowerUp extends ProgrammingCard {

    /**
     * @author Kafadar
     */

    public PowerUp() {super(POWER_UP);}

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