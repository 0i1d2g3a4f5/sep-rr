package client_package.client_gamelogic;

import client_package.client_gamelogic.game_elements.game_elements.robot.Robot;
import gamelogic.Color;

public class Player {

    public Player(Color robotColor){

        this.robot = new Robot(robotColor);

    }

    public Robot robot;

}
