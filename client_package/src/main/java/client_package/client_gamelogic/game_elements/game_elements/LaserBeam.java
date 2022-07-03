package client_package.client_gamelogic.game_elements.game_elements;

import server_package.gamelogic.Direction;

public class LaserBeam {
    int numberBeams;
    Direction direction;
    public LaserBeam(Direction direction, int numberBeams){
        this.numberBeams = numberBeams;
        this.direction = direction;
    }
    void travelForward(){

    }
}
