package gamelogic.game_elements;

import gamelogic.Position;

public abstract class GameElement {

    Position position;
    ElementName type = ElementName.NO_ELEMENT;

    public String isOnBoard;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public GameElement(){

    }

    public ElementName getType() {
        return type;
    }

    @Override
    public String toString() {
        return "GameElement{" +
                "position=" + position +
                ", elementName=" + type +
                '}';
    }

    public GameElement(ElementName elementName ){
        this.type = elementName;

    }


}
