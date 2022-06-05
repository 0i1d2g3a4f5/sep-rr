package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

public class DefragGizmo extends UpgradeCard {

    public DefragGizmo() {
        super(CardName.DEFRAG_GIZMO);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 5
        Effect: Once during each round, you may permanently discard one damage card from your hand.
            Draw a replacement card from the top of your deck.
         */
    }
}
