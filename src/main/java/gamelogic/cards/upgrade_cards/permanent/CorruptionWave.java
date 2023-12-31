package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

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
