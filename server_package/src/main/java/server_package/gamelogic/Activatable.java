package server_package.gamelogic;

public interface Activatable extends Comparable<Activatable> {


    int getActivationOrder();
    void activate();
}
