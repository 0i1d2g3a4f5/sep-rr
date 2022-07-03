package client_package.client_gamelogic.cards.upgrade_cards.permanent;

import client_package.client_gamelogic.cards.CardName;
import client_package.client_gamelogic.cards.upgrade_cards.UpgradeCard;

public class CorruptionWave extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public CorruptionWave() {
        super(CardName.CORRUPTION_WAVE);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 4
        Effect: You may put SPAM damage cards you deal on top of opponents’ decks.
         */
    }
}
