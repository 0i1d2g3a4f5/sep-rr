package gamelogic.game_elements;

import gamelogic.Direction;

public abstract class GameElement {
    ElementName elementName = ElementName.NO_ELEMENT;

    public GameElement(){

    }

    public ElementName getElementName() {
        return elementName;
    }

    @Override
    public String toString() {
        return "GameElement{" +
                "elementName=" + elementName +
                '}';
    }

    public GameElement(ElementName elementName ){
        this.elementName = elementName;

    }


}
