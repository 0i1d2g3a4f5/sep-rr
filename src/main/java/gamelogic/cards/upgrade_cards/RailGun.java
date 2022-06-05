package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class RailGun extends UpgradeCard{

    public RailGun() {
        super(CardName.RAILGUN);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 2
        Effect: You may shoot through any number of walls and/or robots.
            Robots in the line of fire take one SPAM damage card.
         */
    }
}
