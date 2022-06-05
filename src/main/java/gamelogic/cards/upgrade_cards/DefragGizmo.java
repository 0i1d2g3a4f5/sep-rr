package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class DefragGizmo extends UpgradeCard{

    public DefragGizmo() {
        super(CardName.DEFRAGGIZMO);
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
