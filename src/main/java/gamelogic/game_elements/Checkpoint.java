package gamelogic.game_elements;

public class Checkpoint extends GameElement{
    public static int numberOfCheckpoints;
    int checkpointNumber;
    public Checkpoint(int checkpointNumber){
        elementName = ElementName.CHECKPOINT;
        this.checkpointNumber=checkpointNumber;
        numberOfCheckpoints++;
    }

}
