package gamelogic.game_elements;

public class Gear extends GameElement{
    public Gear(boolean turnRight){
        this.turnRight = turnRight;
        elementName = ElementName.GEAR;
    }
    boolean turnRight = false;
}
