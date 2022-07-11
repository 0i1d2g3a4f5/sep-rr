package client_package.AI;

import client_application.ClientApplication;
import client_package.Client;

import java.io.IOException;

public abstract class AIClient extends Client {
    AIController aiController;
    int lastTriedFigure = 1;


    public AIController getAiController() {
        return aiController;
    }
    public AIClient(boolean isBasic, AIController aiController, String name){
        super();
        setIsBasic(isBasic);
        this.aiController = aiController;
        this.setName(name);
    }

    public int getLastTriedFigure() {
        return lastTriedFigure;
    }

    public void setLastTriedFigure(int lastTriedFigure) {
        this.lastTriedFigure = lastTriedFigure;
    }
    public abstract void sendPlayerValues();


    public abstract void createSocket(String ip, int port);
    public abstract void sayHello(String group, String protocol);



}
