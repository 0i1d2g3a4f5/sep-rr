package gamelogic.game_elements;

import gamelogic.Direction;

public class PushPanel extends GameElement{
    Direction direction;
    public PushPanel(Direction direction){
        this.direction=direction;
        elementName=ElementName.PUSHPANEL;
    }
}
