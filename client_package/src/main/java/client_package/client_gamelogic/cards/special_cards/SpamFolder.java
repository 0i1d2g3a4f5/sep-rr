package client_package.client_gamelogic.cards.special_cards;

import static client_package.client_gamelogic.cards.CardName.SPAM_FOLDER;


public class SpamFolder extends SpecialCard {
    public SpamFolder(){
        super(SPAM_FOLDER);
    }
    public void discard() {

    }

    /**
     * @author Qinyi
     * Permanently discard an SPAM damage card from your discard pile to the SPAM damage card draw pile.
     */
    @Override
    public void activateCard(){
    }
}
