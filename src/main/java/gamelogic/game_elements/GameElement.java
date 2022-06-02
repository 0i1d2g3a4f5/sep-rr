package gamelogic.game_elements;

import gamelogic.Direction;
import gamelogic.Position;

public abstract class GameElement {

    Position position;
    ElementName elementName = ElementName.NO_ELEMENT;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public GameElement(){

    }

    public ElementName getElementName() {
        return elementName;
    }

    @Override
    public String toString() {
        return "GameElement{" +
                "position=" + position +
                ", elementName=" + elementName +
                '}';
    }

    public GameElement(ElementName elementName ){
        this.elementName = elementName;

    }


}
