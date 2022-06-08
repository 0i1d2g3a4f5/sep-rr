package gamelogic.game_elements;

public class Checkpoint{
    public static int numberOfCheckpoints;
    int checkpointNumber;
    public Checkpoint(int checkpointNumber){
        this.checkpointNumber=checkpointNumber;
        numberOfCheckpoints++;
    }

}
