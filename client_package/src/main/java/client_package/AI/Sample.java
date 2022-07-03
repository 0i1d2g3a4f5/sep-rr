package client_package.AI;

import server_package.gamelogic.Position;
import server_package.gamelogic.cards.Card;
import server_package.utility.MyMath;

import java.util.ArrayList;

/**
 * @author Ringer
 */
public class Sample {

    int[] cardPositions;
    Position startPosition;
    Position nextCheckpoint;
    ArrayList<Card> handCards;
    double startDistance;

    public Sample(Position startPosition, Position nextCheckpoint, ArrayList<Card> handCards,int[] cardPositions){
        this.startPosition = startPosition;
        this.nextCheckpoint = nextCheckpoint;
        this.handCards = handCards;
        this.cardPositions = cardPositions;
        this.startDistance = MyMath.pythagoras(startPosition,nextCheckpoint);
        for (int i = 0; i < cardPositions.length; i++) {
        }
    }
}
