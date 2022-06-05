package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class CorruptionWave extends UpgradeCard{
    public CorruptionWave() {
        super(CardName.CORRUPTION_WAVE);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 4
        Effect: You may put SPAM damage cards you deal on top of opponents’ decks.
         */
    }
}
