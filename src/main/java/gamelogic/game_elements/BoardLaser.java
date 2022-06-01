package gamelogic.game_elements;

import gamelogic.Direction;

import java.io.IOException;

public class BoardLaser extends GameElement{
    Direction direction;
    int beamNumber;

    @Override
    public String toString() {
        return "BoardLaser{" +
                "direction=" + direction +
                ", beamNumber=" + beamNumber +
                '}';
    }

    public BoardLaser(int number, Direction direction){
        this.direction = direction;
        if(number>3||number<1){
            try {
                throw new IOException("This Laser doesn't exist");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.beamNumber = number;
        elementName = ElementName.BOARDLASER;

    }
}
