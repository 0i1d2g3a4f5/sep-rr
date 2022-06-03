package gamelogic.game_elements;

import gamelogic.Color;
import gamelogic.Direction;


import java.io.IOException;



public class ConveyorBelt extends GameElement {
    Color color;
    Direction direction;

    public ConveyorBelt(Color color,Direction direction){
        elementName= ElementName.CONVEYORBELT;
        this.direction = direction;
        if(color ==Color.BLUE ||color == Color.GREEN) {
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
