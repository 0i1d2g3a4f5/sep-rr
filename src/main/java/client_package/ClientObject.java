package client_package;

import client_package.client_gamelogic.Game;

public interface ClientObject {
    

    Game getGame();
    int getId();
    int getFigure();
    String getName();

    boolean isReady();

    void setIsReady(boolean ready);
}
