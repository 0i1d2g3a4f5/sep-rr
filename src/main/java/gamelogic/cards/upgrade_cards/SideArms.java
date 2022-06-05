package gamelogic.cards.upgrade_cards;

import gamelogic.cards.CardName;

public class SideArms extends UpgradeCard{

    public SideArms() {super(CardName.SIDEARMS);}

    @Override
    public void discard() {

    }

    @Override
    public void PlayCard() {
        /*
        Cost: 3
        Effect: When you push a robot, you may choose to push it to the left or right instead of the direction you are facing
         */
    }
}
