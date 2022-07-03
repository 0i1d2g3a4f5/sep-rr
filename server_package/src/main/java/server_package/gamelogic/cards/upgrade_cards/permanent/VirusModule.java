package server_package.gamelogic.cards.upgrade_cards.permanent;

import server_package.gamelogic.cards.CardName;
import server_package.gamelogic.cards.upgrade_cards.UpgradeCard;

public class VirusModule extends UpgradeCard {

    /**
     * @author Kafadar
     */

    public VirusModule() {super(CardName.VIRUS_MODULE);}

    @Override
    public void discard() {

    }

    @Override
    public void activateCard() {
        /*
        Cost: 2
        Effect: When you push a robot, give that player a virus damage card.
         */
    }
}
