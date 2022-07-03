package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class RailGun extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public RailGun() {
        super(CardName.RAIL_GUN);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 2
        Effect: You may shoot through any number of walls and/or robots.
            Robots in the line of fire take one SPAM damage card.
         */
    }
}
