package server_package.gamelogic.game_elements.robot;

public interface RobotMovement {
    public boolean right();
    public boolean left();
    public boolean uTurn();
    public boolean forward(int distance);
    public boolean backward(int distance);

}
