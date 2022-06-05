package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class HoverUnit extends UpgradeCard{

    public HoverUnit() {
        super(CardName.HOVERUNIT);
    }

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 1
        Effect: Your robot can pass over, but not land on, pits.
            If you end your move on a pit, you fall in.
            You can’t turn off Hover Unit, and you can’t hover above another robot.
         */
    }
}
