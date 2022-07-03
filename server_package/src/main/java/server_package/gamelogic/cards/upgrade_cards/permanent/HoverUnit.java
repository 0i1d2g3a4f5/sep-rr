package server_package.gamelogic.cards.upgrade_cards.permanent;

import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.upgrade_cards.UpgradeCard;

public class HoverUnit extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public HoverUnit() {
        super(CardName.HOVER_UNIT);
    }

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 1
        Effect: Your robot can pass over, but not land on, pits.
            If you end your move on a pit, you fall in.
            You can’t turn off Hover Unit, and you can’t hover above another robot.
         */
    }
}
