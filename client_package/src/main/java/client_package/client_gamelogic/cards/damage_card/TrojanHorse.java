package client_package.client_gamelogic.cards.damage_card;



import static client_package.client_gamelogic.cards.CardName.TROJAN_HORSE;


public class TrojanHorse extends DamageCard {

    public TrojanHorse(){
        super(TROJAN_HORSE);
    }

    @Override
    public void discard() {

    }

    /**
     *
     * if you discard a TrojanHorse, you will immediately get two Spam cards.
     */
    public void activateCard(){
    }
}
