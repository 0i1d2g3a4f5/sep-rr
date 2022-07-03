package client_package.AI;

public class AIController extends Thread{
    public final String groupName;
    public final String protocolVersion;
    public AIController(String protocolVersion, String groupName){
        this.protocolVersion = protocolVersion;
        this.groupName = groupName;
    }

    AIClient aiClient = new BasicAI(true, this);
    @Override
    public void run(){
        aiClient.createSocket(AIMain.IP, AIMain.PORT);
        aiClient.listen();
    }



}
