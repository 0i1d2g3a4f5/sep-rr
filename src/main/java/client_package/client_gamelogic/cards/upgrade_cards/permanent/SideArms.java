package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class SideArms extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public SideArms() {super(CardName.SIDE_ARMS);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 3
        Effect: When you push a robot, you may choose to push it to the left or right instead of the direction you are facing
         */
    }
}
