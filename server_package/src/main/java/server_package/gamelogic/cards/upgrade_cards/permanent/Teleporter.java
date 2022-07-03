package server_package.gamelogic.cards.upgrade_cards.permanent;

import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.upgrade_cards.UpgradeCard;

public class Teleporter extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public Teleporter() {super(CardName.TELEPORTER);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: You may pay one energy to ignore obstacles when moving.
            This includes walls, pits, the priority antenna, and robots;
                however, you may not end your move on a wall, pit, or the priority antenna.
            If you move to a space with another robot, swap places with that robot
         */
    }
}
