package client_package.AI;

import gamelogic.Position;
import gamelogic.cards.Card;
import utility.MyMath;

import java.util.ArrayList;
import java.util.Random;

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
