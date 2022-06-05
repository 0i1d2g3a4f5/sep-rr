package gamelogic.cards.upgrade_cards.permanent;

import gamelogic.cards.CardName;
import gamelogic.cards.upgrade_cards.UpgradeCard;

public class SideArms extends UpgradeCard {

    public SideArms() {super(CardName.SIDE_ARMS);}

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
