package client_package.AI;

import client_application.ClientApplication;
import client_package.Client;

public abstract class AIClient extends Client {
    AIController aiController;


    public AIController getAiController() {
        return aiController;
    }
    public AIClient(boolean isBasic, AIController aiController){
        super();
        setIsBasic(isBasic);
        this.aiController = aiController;
    }

    public abstract void createSocket();


}
