package gamelogic.game_elements;

import gamelogic.Direction;

import java.awt.*;
import java.io.IOException;

import static java.awt.Color.BLUE;
import static java.awt.Color.GREEN;

public class ConveyorBelt extends GameElement {
    Color color;
    Direction direction;

    public ConveyorBelt(Color color,Direction direction){
        elementName= ElementName.CONVEYORBELT;
        this.direction = direction;
        if(color ==BLUE ||color == GREEN) {
                this.color = color;
            }else{
            try {
                throw new IOException("wrong color");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
