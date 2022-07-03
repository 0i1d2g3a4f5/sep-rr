package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class DefragGizmo extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public DefragGizmo() {
        super(CardName.DEFRAG_GIZMO);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 5
        Effect: Once during each round, you may permanently discard one damage card from your hand.
            Draw a replacement card from the top of your deck.
         */
    }
}
