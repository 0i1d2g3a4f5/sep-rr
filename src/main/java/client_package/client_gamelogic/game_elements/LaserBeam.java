package client_package.client_gamelogic.game_elements;

import gamelogic.Direction;

public class LaserBeam extends GameElement {
    int numberBeams;
    Direction direction;
    public LaserBeam(Direction direction, int numberBeams){
        super(ElementName.LASERBEAM);
        this.numberBeams = numberBeams;
        this.direction = direction;
        this.orientations.add(direction);
    }

}
